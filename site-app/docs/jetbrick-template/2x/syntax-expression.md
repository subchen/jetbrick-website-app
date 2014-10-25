表达式 Expression
=======================

支持几乎所有 Java 表达式 (based on Java 6.0)，并对其进行了一些有用的扩展。


与 Java 相同的地方 (运算符优先级和 Java 保存一致)
------------------------------------------------------------

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
* 数组存取：`array[i]`
* 字段访问：`obj.field`
* 方法调用：`obj.method(...)`
* 支持数组定义：`String[]`


> [info] **提示**： 方法调用支持可变参数方法(Varargs)和重载方法(Overload)


与 Java 不同的地方 (扩展语法/语义)：
------------------------------------------------------------

**扩展语法：**

* 单引号字符串：`'abc\u00A0\r\n'`
* 内存地址比较：`===` `!==`
* 简化的三元表达式：`a ?: b`，等价于： `(a==null) ? b : a`
* 静态字段调用：`Long::MAX_VALUE`
* 静态方法调用：`Long::valueOf("123")`
* List 常量：`[1,2,3]`
* Map 常量：`{name:"jetbrick", version: 2.0}`


**扩展语义：**

* 双等号 `==` 会被解析成 `equals()` 方法比较，而不是比内存地址。
* Bean 属性默认会解析成 getter 方法调用，`${user.name}` 等价于 `${user.getName()}`
* 所有实现 `Comparable` 的对象都支持比较运算符，比如：`#if(date1 < date2)`，可以比较日期的先后。
* 所有对象都支持条件测试，并返回 `true` 或者 `false`。对于非 `Boolean` 对象，所有非零数字，非空字串，非空集合，非 `null` 对象，即为 `true`。
* `Map` 支持 `.` 访问内部的对象（属性调用），如： `map.key`。
* `List` 和 `Map` 支持方括号取值：`list[0]` 等价于 `list.get(0)`，`map["abc"]` 等价于 `map.get("abc")`


不支持的 Java 语法：
------------------------------------------------------------

* 不支持 `++`, `--` 表达式
* 不支持泛型
* 不支持强制类型转换


变量名 Variable
----------------

可以是任意合法的 Java 变量名：

* 其中 `$` 开头的变量为模板内部变量，不允许直接使用。
* 不允许使用 Java 关键字和保留字。

合法的变量名范例：`user`, `user_name`, `userName`


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

Bean 属性默认会被解析成 getter 方法调用。


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
* 支持扩展方法调用。参考：[扩展方法调用](ext-method.html)

示例：

```
${"Hello".substring(2)}
${"Hello".substring(2, 3)}
```


函数调用 function
-----------------

`jetbrick-template` 还支持函数调用，如 `${now()}`, `${fileGet("/test.txt")}`。

具体参考：[扩展函数调用](ext-function.html)



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
