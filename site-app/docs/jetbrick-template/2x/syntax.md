这个是 `jetbrick-template` 模板语法参考手册。我们推荐的模板文件扩展名为 `.jetx`。

`jetbrick-template` 的模板语法有下面的 4 部分组成：

* [值 Value](#value)
* [指令 Directive](#directive)
* [文本 Text](#text)
* [注释 Comment](#comment)

值 Value
=========

用来输出一个变量或者表达式的内容。

语法：

* `${expression}`：输出表达式的计算结果。
* `$!{expression}`：输出表达式的计算结果，并 escape 其中的 HTML 标签。

其中 `expression` 为任何合法的 Java 表达式，参考: [表达式](#expression)。

示例：

```
${user.name}
${user.book.available()}
$!{user.description}
```

> **注意**：
>
> * 如果 `expression` 的返回值为 `null`，则不会输出任何东西。
> * 如果 `expression` 的返回类型为 `void`，那么也不会做任何输出动作。


指令 Directive
================

变量类型声明 #define
-------------------

为了让模板的可读性更好，以及防止一些未知的错误发生，我们提倡为模板中用到的每个变量声明变量类型。

语法：

* `#define(type name, ...)`

示例：

```
#define(String name)
#define(UserInfo user, List userlist)
```

> **注意**：
>
> * 在同一个模板中，不允许重复声明变量类型
> * 变量必须先声明，再使用
> * 在 `strict` 模式中，变量必须进行声明

赋值语句 #set
-------------

语法：

* `#set(type name = value, ...)`
* `#set(name = value, ...)`

示例：

```
#set(int num = 1+2*3)
#set(color1 = "#ff0000", color2 = "#00ff00")
```

> **注意**：
>
> * 在同一个模板中，不允许重复声明变量类型
> * 影响当前模板，以及子模板的 `Context` 变量。
> * 不影响父模板的 `Context` 变量。


条件语句 #if, #elseif, #else
-------------------------------

如果 `#if` 条件表达式计算结果为 `true` 或非空，则输出指令所包含的块, 否则输出 `#else` 指令块。

语法：

* `#if(expression)` ... `#end`
* `#if(expression)` ... `#else` ... `#end`
* `#if(expression)` ... `#elseif(expression)` ... `#else` ... `#end`

示例：

```
#if (user.role == "admin")
	...
#elseif (user.role == "vip")
	...
#elseif (user.role == "guest")
	...
#else
	...
#end
```

> **注意**：
>
> * 对于 `expression` 为非 `Boolean` 值：非零数字，非空字串，非空集合，非 `null` 对象，即为 `true`。
> * `#elseif` 允许出现多次。
> * 如果 `#else` 后面紧跟着其他文本，比如 `#elseABC`，那么可以通过添加一对空括号来分割，修改成 `#else()ABC`。这样可读性就能加强，模板解析也不会出现问题。所有的无参数指令，比如 `#end`，`#break`，`#stop` 都支持这样操作。`()` 前面和之间请不要插入任何空格。

## 循环语句 #for

循环重复输出指令所包含的块，如果是空的集合对象，那么输出 `#else` 块。

语法：

* `#for(id: expression)` ... `#end`
* `#for(id: expression)` ... `#else` ... `#end`
* `#for(type id: expression)` ... `#end`
* `#for(type id: expression)` ... `#else` ... `#end`

`#for` 支持以下类型的 `expression`

* Iterator
* Iterable (Collection, ...)
* Map
* Enumeration
* Array
* Enum
* null (空循环)
* Object (只循环一次)

示例：

```
#for (Book book : user.books)
	${for.index} // 内部循环计数器，从 1 开始计数
	...
#end
```

指令 `#else` 可用于循环为空时的内容输出。

```
#for (Book book : user.books)
	...
#else
	No books are found in here.
#end
```

### for 内部对象

* `for.index` 可用于内部循环计数，从 `1` 开始计数。
* `for.size`  获取循环总数。
* `for.first` 是否第一个元素。
* `for.last` 是否最后一个元素。
* `for.odd` 是否第奇数个元素。
* `for.even` 是否第偶数个元素。

## 循环中断或继续语句 #break, #continue

当 `expression` 为 `true`，`#break` 中断当前循环，而 `#continue` 跳过余下的内容，跳到下一个循环。

语法：

* `#break`
* `#break(expression)`
* `#continue`
* `#continue(expression)`

示例：

```
#for (book : user.books)
	...
	#break(book.price > 100)
	...
#end
```

```
#for (book : user.books)
	...
	#continue(book.price > 100)
	...
#end
```

> **注意**：
>
> * 无参数格式代表 `expression` 永远为 `true`。


停止解析语句 #stop
--------------------

当 `expression` 为真或非空时，停止模板运行，立刻返回。

语法：

* `#stop`
* `#stop(expression)`

示例：

```
#stop(error != null)
```

> **注意**：
>
> * 无参数格式代表 `expression` 永远为 `true`。


嵌套模板语句 #include
---------------------------

嵌入一个子模板，将子模板内容输出到当前位置。

语法：

* `#include(file)`
* `#include(file, parameters)`
* `#include(file, parameters, returnName)`
 
示例：

```
#include("/include/header.jetx") // 绝对路径
#include("../userinfo.jetx") // 相对路径
#include(file) // 动态路径
#include("/include/header.jetx", {role: "admin"}) // 传递参数
```

> **注意**：
>
> * 子模板自动共享父模板 `Context` 变量，同时还可以另外传递一些参数给子模板。
> * 子模板可以用 `#return` 指令返回一些参数给父模板。

具体用法请查考： [在 jetbrick-template 中如何使用 #include？](faq-include.html)

宏定义 #macro
------------------------

定义一个代码片段，然后可以重复使用。

语法：

* `#macro name(type name, ...)` ... `#end`

> **注意**： 每个宏可以定义 0~N 个参数。


示例：

```
#macro header(String name)
    Hello ${name}!
#end

#call header("张三")
#call header("李四")
```

输出结果：

```
    Hello 张三!
    Hello 李四!
```


自定义标签 #tag
-----------------------

jetbrick-template 支持自定义 Tag，类似于 JSP Taglib，但是要比 JSP Taglib 更简单更好用。

语法：

* `#tag name(args ...)` ... `#end`

> **注意**： 
>
> * 需要在 Java 端先定义 Tag 标签的实现，并进行注册。 
> * Tag 调用的参数必须和定义的一致。

示例：

```
#tag layout_block("list")
	<ul>
		<li>Home</li>
		<li>Document</li>
		<li>Download</li>
		<li>About</li>
	</ul>
#end
```

具体用法请查考： [在 jetbrick-template 中如何自定义 Tag？](faq-tag.html)

文本 Text
=============

普通文本内容将会被直接进行输出。


不解析文本块
--------------

原样输出模板内容，用于输出纯文本内容，或批量转义块中的特殊字符。类似于 XML 中的 CDATA。

语法：

* `#[[` ... `]]#`

示例：

```
#[[
	Source code will be displayed in here.
	Hello ${user.name}	
]]#
```

特殊字符转义
--------------

原样输出指令特殊字符，转义字符由 `\` 进行转义。

语法：

* `\\`
* `\#`
* `\$`

示例：

```
\#if
\${user.name}
\\${user.name}
```

> **注意**：
>
> * 如果遇到类似 `#ff0000` 类似于指令的内容，但又不是系统定义的指令，那么也会原样输出，并不需要进行转义。
> * `\` 后面跟的字符不是 `#` 和 `$`，也不需要进行转义，直接输出。


注释 Comment
=================

行注释
----------

隐藏行注释的内容，以换行符结束，用于注解过程，或屏蔽指令内容。

语法：

* `#//` ...

示例：

```
${user.name} #// This is a line comment.
```

块注释
------------

隐藏块注释内容，可包含换行符，用于注解过程，或屏蔽指令内容。

语法：

* `#--` ... `--#`

示例：

```
#--
	This is a block comment.
--#
```

<a name="expression"></a>
表达式 Expression
=======================

支持所有 Java 表达式，并对其进行了一些有用的扩展。

### 与 Java 相同的地方 (运算符优先级和 Java 保存一致)

* 数字常量： `123` `123L` `0.01D` `99.99E-10D`
* 字符串常量： `"abc\r\n"` `'abc\u00A0\r\n'`
* BOOLEAN 常量：`true` `false`
* NULL 常量: `null`
* 算术运行： `+` `-` `*` `/` `%`
* 位运算： `~` `&` `|`
* 移位运算： `>>` `>>>` `<<`
* 比较运算： `==` `!=` `>` `>=` `<` `<=`
* 逻辑运算： `!` `&&` `||`
* 三元表达式： `? :` 
* 实例对象判断： `instanceof`
* NEW 对象：`new Object(...)`
* 强制类型转换：`(java.lang.String)obj`
* 数组存取：`array[i]`
* 字段访问：`obj.field`
* 方法调用：`obj.method(...)`
* 方法调用：支持可变参数方法(Varargs)和重载方法(Overload)
* 支持数组定义：`String[]`
* 简化的三元表达式：`a ?: b`，等价于： `(a==null) ? b : a`
* 静态字段调用：`Long::MAX_VALUE`
* 静态方法调用：`Long::valueOf("123")`

### 与 Java 不同的地方

* 双等号 `==` 会被解析成 `equals()` 方法比较，而不是比内存地址。
* 要比较内存地址，可以使用 `===` 和 `!==`。
* 单双引号都将生成字符串：`'a'` 或 `"a"` 都是`String` 类型。
* Bean 属性会解析成 getter 方法调用，`${user.name}` 等价于 `${user.getName()}`
* 所有实现 `Comparable` 的对象都支持比较运算符，比如：`#if(date1 < date2)`，可以比较日期的先后。
* 所有对象都支持条件测试，并返回 `true` 或者 `false`。对于非 `Boolean` 对象，所有非零数字，非空字串，非空集合，非 `null` 对象，即为 `true`。
* `List` 和 `Map` 可以方括号取值，比如：`list[0]` 等价于 `list.get(0)`，`map["abc"]` 等价于 `map.get("abc")`。
* `Map` 支持 `.` 访问内部的对象（属性调用），如： `map.key`。
* 静态字段/方法调用，使用 `::` 语法，比如： `java.lang.Long::MAX_VALUE`
* 支持简化的三元表达式：`a ?: b`，等价于： `(a==null) ? b : a`
* 不再支持 `++`, `--` 表达式


变量名 Variable
----------------

可以是任意合法的 Java 变量名：

* 其中 `$` 开头的变量为模板内部变量，不允许直接使用。
* 不允许使用 Java 关键字。

如：`user`, `user_name`, `userName`

内置的特殊变量：

* `for`： 用于 `#for` 指令内部状态对象。具体参考 `#for` 指令用法。

List 常量
-----------

语法：

* `[item, ...]`

示例：

```
[] // empty List
[1, 2, 3, 4, 5]
["abc", 123, new Date(), 1+2*3]
```

取值：

```
${list[index]}
${list.get(index)}
```

Map 常量
-------------

语法：

* `{key: value, ...}`

key 可以加单双引号，也可以不加 (和 JSON 一样)

示例：

```
{} // empty Map
{name: "Jason", "statue": 0}
```

取值：

```
${map.key}
${map["key"]}
${map.get("key")}
```

Bean 属性调用 bean.property
-----------------------------

Bean 属性会解析成 getter 方法调用。

属性查找顺序，以 `${obj.foo}` 为例：

1. 查找 obj.getFoo() 方法
2. 查找 obj.isFoo() 方法
3. 查找 obj class 的 foo 字段
4. 查找 map.get(name) 方法 (如果是 `Map`)
5. 自定义的 Getter Resolver


Bean 方法调用 bean.method(...)
-------------------------------

* 支持普通方法调用
* 支持不定长参数方法调用 Varargs
* 支持方法重载 Overload
* 支持扩展方法调用。参考：[扩展方法调用](userguide.html#methods)

示例：

```
方法重载 Overload
${"Hello".substring(2)}
${"Hello".substring(2, 3)}
```


函数调用 function
-----------------

jetbrick-template 还支持函数调用，如 `${now()}`, `${include("tag.jetx")}`。

具体参考：[扩展函数调用](userguide.html#functions)



静态字段调用 Class::Field
------------------------------

语法：

* `Class::Field`

示例：

```
${Long::MAX_VALUE}
${java.lang.Long::MAX_VALUE}
```

静态方法调用 Class::method
------------------------------

语法：

* `Class::method(...)`

示例：

```
${Collections::emptyList()}
${java.lang.Long::valueOf("123")}
```


默认的方法扩展 Methods
========================

默认的自定义标签 Tags
==========================

所有 Tags 定义在 `jetbrick.template.runtime.buildin.JetTags`

* `#tag lyaout_block(String name)` ... `#end`
  将块内容保存到变量名为 name 的 JetContext 中。
  参考：[jetbrick-template 中如何实现 layout 功能？](faq-layout.html)

* `#tag lyaout_block_default(String name)` ... `#end`
  如果不存在指定的 JetContext 变量，那么输出 default_block 块内容，否则输出指定的 JetContext 变量。
  参考：[jetbrick-template 中如何实现 layout 功能？](faq-layout.html)

* `#tag application_cache(String name, long timeout)` ... `#end`
  将内存缓存到 ServletContext 中，在 timeout 秒之后自动超时。(需要 `jetbrick-template-servlet.jar`)

* `#tag session_cache(String name, long timeout)` ... `#end`
  将内存缓存到 HttpSession 中，在 timeout 秒之后自动超时。(需要 `jetbrick-template-servlet.jar`)


<a name="velocity"></a>
和 Velocity 的比较
===================

语法差异
----------

* jetbrick-template 指令中的变量不加 `$` 符，只支持 `#if(x == y)`，不支持 `#if($x == $y)`，因为指令中没有加引号的字符串就是变量，和常规语言的语法一样，加$有点废话，而且容易忘写。
* jetbrick-template 占位符必需加大括号，只支持 `${foo}`，不支持 `$foo`，因为 `$` 在 JavaScript 中也是合法变量名符号，而 `${}` 不是，减少混淆，也防止多人开发时，有人加大括号，有人不加，干脆没得选，都加，保持一致。
* jetbrick-template 占位符当变量为 `null` 时输出空白串，而不像 Velocity 那样原样输出指令原文，即 `${foo}`，等价于 Velocity 的 `$!{foo}`，以免开发人员忘写感叹号，泄漏表达式源码，如需原样输出，可使用转义 `\${foo}`， 在 jetbrick-template 中，`$!{foo}` 表示对内容进行 HTML 过滤，用于原样输出 HTML 片段。
* jetbrick-template 支持在所有使用变量的地方，进行表达式计算，也就是你不需要像 Velocity 那样，先 `#set($j = $i + 1)` 到一个临时变量，再输出临时变量 `${j}`，jetbrick-template 可以直接输出 `${i + 1}`，其它指令也一样，比如：`#if(i + 1 == n)`。
* jetbrick-template 采用扩展 Class 原生方法的方式，如：`${"str".toChar()}`，而不像 Velocity 的 Tool 工具方法那样：`$(StringTool.toChar("a"))`，这样的调用方式更直观，更符合代码书写习惯。
* jetbrick-template 支持属性和方法的安全调用。如 `${user?.name}`，`${user?.hasRole("vip")}`。如果 `user` 对象为 `null`，那么返回结果就是 `null`，不会出现烦人的 `NullPointerException`。
* jetbrick-template 还支持静态字段/方法调用，函数扩展，上下文相关的方法/函数扩展。

指令差异
------------

