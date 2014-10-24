
这个是 `jetbrick-template` 模板语法参考手册。我们推荐的模板文件扩展名为 `.jetx`。

`jetbrick-template` 的模板语法有下面的 4 部分组成：

* [值 Value](#-value)
* [指令 Directive](#-directive)
* [文本 Text](#-text)
* [注释 Comment](#-comment)


值 Value
============

用来输出一个变量或者表达式的内容。

语法：

* `${expression}`：输出表达式的计算结果。
* `$!{expression}`：输出表达式的计算结果，并 escape 其中的 HTML 标签。

其中 `expression` 为任何合法的 Java 表达式，参考: [表达式 Expression](#-expression)。

示例：

```
${user.name}
${user.book.available()}
$!{user.description}
```

> [info] **注意**：
>
> * 如果 `expression` 的返回值为 `null`，则不会输出任何东西。
> * 如果 `expression` 的返回类型为 `void`，那么也不会做任何输出动作。


指令 Directive
================


模板选项 #options
----------------------------

```
#options (
    loadmacro = "/macors/commons.jetx",
    loadmacro = "/macors/users.jetx",
    import = "java.lang.*",
    import = "java.util.*",
    safecall = true,
    strict = true,
    trimWhitespaces = true
)
```

其中：

* `strict = true` 代表模板中用到的变量必须先进行 `#define`, 否则出错
* `safecall = true` 开启安全调用
* `import` 引入 package
* `loadmacro` 引入外部文件中的 macro 定义
* `trimLeadingWhitespaces` 删除文件头上的空白内容



变量类型声明 #define
----------------------------

为了让模板的可读性更好，以及防止一些未知的错误发生，我们提倡为模板中用到的每个变量声明变量类型。

语法：

* `#define(type name, ...)`

示例：

```
#define(String name)
#define(UserInfo user, List userlist)
```

> [warn] **注意**：
>
> * 在同一个模板中，不允许重复声明变量类型
> * 变量必须先声明，再使用
> * 在 `strict` 模式中，变量必须进行声明类型


赋值语句 #set
----------------

语法：

* `#set([type] name = value, ...)`

示例：

```
#set(int num = 1+2*3)
#set(color1 = "#ff0000", color2 = "#00ff00")
```

> [warn] **注意**：在同一个模板中，不允许重复声明变量类型。



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

> [info] **提示**：
>
> * 对于 `expression` 为非 `Boolean` 值：非零数字，非空字串，非空集合，非 `null` 对象，即为 `true`。
> * 如果 `#else` 后面紧跟着其他文本，比如 `#elseABC`，那么可以通过添加一对空括号来分割，修改成 `#else()ABC`。这样可读性就能加强，模板解析也不会出现问题。所有的无参数指令，比如 `#end`，`#break`，`#stop` 都支持这样的语法。`()` 前面和之间请不要插入任何空格。


循环语句 #for
---------------------

循环重复输出指令所包含的块，如果是空的集合对象，那么输出 `#else` 块。

语法：

* `#for([type] id: expression)` ... `#end`
* `#for([type] id: expression)` ... `#else` ... `#end`

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

### #for 内部对象 for

* `for.index` 可用于内部循环计数，从 `1` 开始计数。
* `for.size`  获取循环总数。
* `for.first` 是否第一个元素。
* `for.last` 是否最后一个元素。
* `for.odd` 是否第奇数个元素。
* `for.even` 是否第偶数个元素。


### for-else

指令 `#else` 可用于循环为空时的内容输出。

```
#for (Book book : user.books)
	...
#else
	No books are found in here.
#end
```

### count-loop

要使用 count-loop 循环, 我们可以借助于 `range()` 函数，比如：

```
#for (int i : range(1, 100))
	...
#end
```


循环中断或继续语句 #break, #continue
---------------------------------------------

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

> [info] **注意**：无参数格式代表 `expression` 永远为 `true`。


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

> [info] **注意**：无参数格式代表 `expression` 永远为 `true`。


嵌套模板语句 #include
---------------------------

嵌入一个子模板，将子模板内容输出到当前位置。

语法：

* `#include(file, [parameters], [returnName])`
 
示例：

```
#include("/include/header.jetx") // 绝对路径
#include("../userinfo.jetx") // 相对路径
#include(file) // 动态路径
#include("/include/header.jetx", {role: "admin"}) // 传递参数
#include("/include/header.jetx", {}, "result") // 获取返回值
```

> [info] **注意**：
>
> * 子模板自动共享父模板 `Context` 变量，同时还可以另外传递一些私有参数 `parameters` 给子模板。
> * 子模板可以用 `#return` 指令返回一个对象给父模板，并存储在父模板中 `returnName` 指定的变量名中。
> * 如果 `returnName` 为 null，那么默认为 `RESULT`


具体用法请移步： [嵌入子模板 #Include](include.html)


宏定义 #macro, #call
------------------------

定义一个代码片段，然后可以重复使用。


宏定义语法：

* `#macro name([type] name, ...)` ... `#end`

> [info] **注意**： 每个宏可以定义 0~N 个参数，并可以选择声明类型。

宏调用语法：

* `#call macro_name(args...)`


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

更多关于宏 Macro 的内容请查看：[宏 Macro](ext-macro.html)


自定义标签 #tag
-----------------------

`jetbrick-template` 支持自定义 Tag，类似于 JSP Taglib，但是要比 JSP Taglib 更简单更好用。

语法：

* `#tag name(args ...)` ... `#end`

> [info] **注意**： 
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

更多关于标签 Tag 的内容请查看：[标签 Tag](ext-tag.html)


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

> [info] **提示**：
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
* `##` ...

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
