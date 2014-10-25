扩展方法 Methods
================================

我们知道一个 Java Class 的 所有 methods 都是定义在同一个 class 文件中的，不能在其他地方进行动态扩展。但是一些其他动态语言却支持在 Class 外部为这个 Class 增加一些方法。比如：

* JavaScript 的 prototype 机制
* Groovy 的 metaClass 机制

`jetbrick-template` 也在这里带给大家强大的动态方法扩展机制。如：

* `${"123".asInt()}`
* `${new Date().format("yyyy-MM-dd")}`
* `${[1, false, null].asJson()}`


> [warn] **注意**：如果 `Class` 已经定义了同名方法，则优先使用 `Class` 定义的方法。但是扩展方法支持方法重载 (Overrload)。


定义扩展方法
---------------------

语法：

```
public static Object method_name ( Object object, args ... )
```

* 方法签名必须是 `public` 和 `static`
* 方法的第一个参数类型必须是要扩展的 Object, 其余参数自定义
* 允许定义相同名字的 method，但是方法参数不一样 （Overload）
* 支持可变参数 (VarArgs)


示例：对 `String.class` 进行扩展

```java
package jetbrick.demo;

public class StringMethods {

    public static String link(String text, String url) {
        return "<a href=\"" + url + "\">" + text + "</a>";
    }
}
```
 

注册扩展方法
--------------------

用户自定义的扩展方法必须注册到 `JetEngine` 中才能使用。注册方法有 3 种途径：

* API 动态注册

    ```java
    JetEngine engine = ...
    GlobalResolver resolver = engine.getGlobalResolver();
    resolver.registerMethods(StringMethods.class);
    ```

* 在配置文件中注册 jetbrick-template.properties 

    ```
    jetx.import.methods = jetbrick.demo.StringMethods
    ```

* 自动扫描注册

    1. 配置扫描路径
    
        ```
        jetx.scan.packages = jetbrick.demo
        ```
    
    2. 为 Class 增加扫描 `@JetAnnotations.Methods`
    
        ```java
        @JetAnnotations.Methods
        public class StringMethods { ... }
        ```


在模板中使用扩展方法
--------------------

模板：

	${"BAIDU".link("http://www.baidu.com/")}

输出结果：

	<a href="http://www.baidu.com/">BAIDU</a>


在扩展方法中获取 `InterpretContext` 上下文
-----------------------------------------------

要获取 `InterpretContext` 对象，可以通过 `InterpretContext.current()` 方法获取当前 Thread local context 关联的 `InterpretContext` 对象。


```java
@JetAnnotations.Methods
public class UserInfoMethods {

    public static String isOnline(UserInfo user) {
        InterpretContext ctx = InterpretContext.current();
        HttpSession session = (HttpSession) ctx.getValueStack().getValue(JetWebContext.SESSION);
        return session.getAttribute("user_" + user.getId()) != null;
    }
}
```

模板范例：

```
#define(UserInfo user)
${user.isOnline()}
```	


模板已经内置的扩展方法 Buildin Methods
--------------------------------------------

所有内置的扩展方法都定义在 `jetbrick.template.runtime.buildin.JetMethods`

#### 基本数据类型转换 Cast

* String.asBoolean()
* String.asInt()
* String.asLong()
* String.asFloat()
* String.asDouble()
* String.asDate()
* String.asDate(String format)
* Collection.asList()
* Object[].asList()
* Object.asString()

#### 数据格式化 Format

* Number.format()
* Number.format(String format)
* Date.format()
* Date.format(String format)

#### 数据 Escape/Unescape

* String.escapeJava()
* String.unescapeJava()
* String.escapeJavaScript()
* String.unescapeJavaScript()
* String.escapeXml()
* String.unescapeXml()
* String.escapeUrl()
* String.escapeUrl(String encoding)
* String.unescapeUrl()
* String.unescapeUrl(String encoding)

#### JSON 输出

* Object.asJson()

#### 字符串转换

* String.capitalize()
* String.toUnderlineName()
* String.toCamelCase()
* String.toCapitalizeCamelCase()

