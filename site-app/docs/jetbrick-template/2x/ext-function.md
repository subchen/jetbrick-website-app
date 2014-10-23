扩展函数 Functions
================================

`jetbrick-template` 允许将 `java` 中 `public static` 的方法变成一个全局函数来使用，我们称之为`扩展函数 Functions`。

比如：

* `${now()}`
* `${range(1,100)}`
* `${debug("name={}, status={}", name, status)}`


定义扩展函数
---------------------

语法：

```
public static Object function_name ( args ... )
```

* 方法签名必须是 `public` 和 `static`
* 方法参数自定义
* 允许定义相同名字的 function，但是方法参数不一样 （Overload）
* 支持可变参数 (VarArgs)


示例：

```java
package jetbrick.demo;

public class MyFunctions {

    public static String today(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }
}
```


注册扩展函数
--------------------

用户自定义的扩展函数必须注册到 `JetEngine` 中才能使用。注册函数有 3 种途径：

* API 动态注册

    ```java
    JetEngine engine = ...
    GlobalResolver resolver = engine.getGlobalResolver();
    resolver.registerFunctions(MyFunctions.class);
    ```

* 在配置文件中注册 jetbrick-template.properties 

    ```
    jetx.import.functions = jetbrick.demo.MyFunctions
    ```

* 自动扫描注册

    1. 配置扫描路径
    
    ```
    jetx.scan.packages = jetbrick.demo
    ```
    
    2. 为 Class 增加扫描 `@JetAnnotations.Functions`
    
    ```java
    @JetAnnotations.Functions
    public class MyFunctions { ... }
    ```


在模板中使用扩展函数
----------------------

模板：
	
```
${today("yyyy-MM-dd")}
```

输出结果：

```
2014-10-02
```


在扩展方法中获取 `InterpretContext` 上下文
-----------------------------------------------

要获取 `InterpretContext` 对象，可以通过 `InterpretContext.current()` 方法获取当前 Thread local context 关联的 `InterpretContext` 对象。


```java
@JetAnnotations.Functions
public class MyFunctions {

    public static String hello() {
        InterpretContext ctx = InterpretContext.current();
        HttpSession session = (HttpSession) ctx.getValueStack().getValue(JetWebContext.SESSION);
        return "Hello: " + session.getAttribute("username");
    }
}
```

模板范例：

```
${hello()}
```	

模板输出：

```
Hello: jetbrick
```	


模板已经内置的扩展函数 Buildin Functions
---------------------------------------------

所有扩展函数都定义在 `jetbrick.template.runtime.buildin.JetFunctions`

### 常用函数

* `Date now()`
  获取当前时间

* `int random()`
  获取一个随机数

* `UUID uuid()`
  获取一个 UUID


### 循环计数生成器

生成一个用于循环的数组，主要用于 `#for` 的循环迭代。

* `Iterator<Integer> range(int start, int stop)`
* `Iterator<Integer> range(int start, int stop, int step)`

模板范例：

```
#for (int i : range(1,100))
	${i}
#end
```

### 读取纯文本文件内容

* `String fileGet(String relativeName)`
* `String fileGet(String relativeName, String encoding)`

### 读取子模板，并获取输出

嵌入一个子模板。和 `#include` 指令的区别，此函数对子模板的输出进行了缓存，可以处理返回的内容，但是效率没有 `#include` 指令高。

* `String includeGet(String relativeName)`
* `String includeGet(String relativeName, Map<String, Object> parameters)`

### 调用一个 macro，并获取输出

* `String macroGet(String name, Object... arguments)`

### 调试专用函数 debug(...)

* `void debug(String format, Object... args)`

