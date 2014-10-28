值 Value
==================================

用来输出一个变量或者表达式的内容。

语法：

* `${expression}`：输出表达式的计算结果。
* `$!{expression}`：输出表达式的计算结果，并 escape 其中的 HTML 标签 (安全输出，防止 xss 攻击)。

其中 `expression` 为任何合法的 Java 表达式，参考: [表达式 Expression](syntax-expression.html)。

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

