标签 Tag
====================================

`jetbrick-template` 支持用户自定义模板标签 Tag，功能类似于 JSP Taglib，但是要比 JSP Taglib 更简单更好用。


比如下面的代码范例：

```
#tag cache("CACHE-ME-30-MIN", 30*60)
    今天天气: ${WeatherUtils::getAsHtml()}
#end
```

这个例子演示了一个自定义的 cache tag，允许将天气结果缓存上 30 分钟，而不是每次都重新获取天气信息。



定义标签 Tag
---------------------

每一个 Tag 由 Java 中的一个静态方法实现。

语法：

```
public static void tag_name ( JetTagContext ctx, args ... )
```

* 方法签名必须是 `public` `static`
* 方法返回值必须是 `void`
* 方法第一个参数必须是 `JetTagContext`， 其余参数自定义
* 允许 throws 任意的 `Throwable`
* 允许定义相同名字的 Tag，但是方法参数不一样 （Overload）
* 支持可变参数 (VarArgs)


示例：

```java
package jetbrick.demo;

public class MyTags {
    private static final TimedSizeCache cache = new TimedSizeCache(128);

    public static void cache(JetTagContext ctx, String name, int timeout) throws IOException {
        Object value = cache.get(name);
        if (value == null) {
            value = ctx.getBodyContent();
            cache.put(name, value, timeout);
        }
        ctx.getWriter().print(value.toString());
    }
}
```


注册标签 Tag
--------------------

用户自定义的 Tag 必须注册到 `JetEngine` 中才能使用。注册 Tag 有 3 种途径：

* API 动态注册

    ```java
    JetEngine engine = ...
    GlobalResolver resolver = engine.getGlobalResolver();
    resolver.registerTags(MyTags.class);
    ```

* 在配置文件中注册 jetbrick-template.properties 

    ```
    jetx.import.tags = jetbrick.demo.MyTags
    ```

* 自动扫描注册

    - 配置扫描路径

        ```
        jetx.autoscan.packages = jetbrick.demo
        ```

    - 为 Class 增加扫描 `@JetAnnotations.Tags`
    
        ```java
        @JetAnnotations.Tags
        public class MyTags { ... }
        ```


JetTagContext 的使用
------------------------------------

Tag 的实现第一个参数是 `JetTagContext`，它包含了 Tag 运行时的上下文。

主要 API：


* `JetEngine getEngine()`

    获取 JetEngine

* `InterpretContext getInterpretContext()`

    获取 InterpretContext

* `void invoke()`

    在当前位置输出 `#tag ... #end` 之间的内容

* `String getBodyContent()`

    执行并捕获 `#tag ... #end` 之间的内容 (不输出)


模板已经内置的标签 Buildin Tags
---------------------------------------------

所有 Tags 都定义在下面的 2 个 Class 中

* `jetbrick.template.runtime.buildin.JetTags`
* `jetbrick.template.web.buildin.JetTags`


### Layout 布局相关

* `#tag layout_block(String name)` ... `#end`

    将块内容保存到变量名为 name 的 Context 中。

* `#tag layout_block_default(String name)` ... `#end`

    如果不存在指定 name 的 context 变量，那么输出 body 内容，否则输出指定的 context 变量内容。


### Web 缓存相关 (需要 jetbrick-template-web)

* `#tag application_cache(String name, long timeout)` ... `#end`

    将内存缓存到 ServletContext 中，在 timeout 秒之后自动超时。

* `#tag session_cache(String name, long timeout)` ... `#end`

    将内存缓存到 HttpSession 中，在 timeout 秒之后自动超时。



