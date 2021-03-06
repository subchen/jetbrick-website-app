Controller/Action 注入
====================================

jetbrick 支持 IoC，所以，我们的 Controller 自然也可以注入依赖的 Bean 对象。

Controller 字段注入
-------------------------

我们可以使用如下的 Annotation 来实现字段注入：

* `@Inject` - IoC 容器对象
* `@Config` - 配置文件配置项
* `@SpringBean` - Spring IoC 对象
* `@XXX` - 用户自定义标注

基本上，Controller 字段注入的大部分都是全局对象。不支持直接注入 Request/Session 上下文相关的对象。

Action 参数注入
---------------------------

在 Action 方法中，我们不仅可以注入 IoC 容器中的对象，我们也可以注入 Request/Session 上下文相关的对象。

对于 Action 方法参数的注入，我们支持 2 种方式：

### **根据 Annotation 注入**

* `@Inject` - IoC 容器中的对象
* `@Config` - 配置文件中的配置项
* `@SpringBean` - Spring IoC 对象
* `@PathVariable` - Path URL 参数
* `@RequestParam` - Request.getParameter(...)，也支持文件上传对象 FilePart
* `@RequestForm` - 将多个 Request 参数，注入到一个 Form 对象中
* `@RequestBody` - Request.getInputStream()
* `@RequestHeader` - Request.getHeader(...)
* `@RequestCookie` - Request.getCookies()
* `@RequestAttribute` - Request.getAttribute(...)
* `@SessionAttribute` - Session.getAttribute(...)
* `@ServletContextAttribute` - ServletContext.getAttribute(...)
* `@InitParameter` - ServletContext.getInitParameter(...)
* `@XXX` - 用户自定义标注

### **根据参数的类型注入：**

* `RequestContext` - Request 上下文对象
* `Model` - Action 要返回的 Model 对象
* `HttpServletRequest` - Request  对象
* `HttpServletResponse` - Response  对象
* `HttpSession` - Session  对象
* `ServletContext` - ServletContext 对象
* `FilePart` - 单个上传文件对象
* `FilePart[]` - 所有上传文件对象
* `RequestParameterMap` - a map of request parameter.
* `RequestParameterValuesMap` - a map of request parameter values.
* `RequestHeaderMap` - a map of request header.
* `RequestHeaderValuesMap` - a map of request header values.
* `RequestCookieMap` - a map of request cookie.
* `RequestAttributeMap` - a map of request attributes.
* `SessionAttributeMap` - a map of session attributes.
* `ServetContextAttributeMap` - a map of servlet context attributes.
* `ServetContextInitParameterMap` - a map of servlet context init parameters.
* `XXX` - 用户自定义类型


下面我们将对每一个 Annotation 和参数类型来说明
----------------------------------------------------

### 根据 Annotation 注入

#### @Inject/@Config/@SpringBean

这几个 Annotation 是 IoC 容器本身提供的，具体说明可以参考 IoC 中的相关内容。

* [@Inject](../jetbrick-ioc/anno-inject.html)
* [@Config](../jetbrick-ioc/anno-config.html)

#### @PathVariable

专门用于获取 Request URL 中 `{}` 参数变量。支持变量类型的自动转换。

例如，我们的 Action URL 定义如下 `/users/{id}`，而我们的 Request URL 如下 `/users/123`。

这样你就可以用 `@PathVariable("id")` 来获取 URL 中参数 `{id}` 对应的变量 `123`.

> [info] **提示**:
> 
> JDK 8 开始支持方法参数名称(编译选项 `-parameters`)。
> 
> 我们如果省略 `@PathVariable` 参数名称，那么我们将会从参数的名称中获取名字。
> 
> 对于使用 JDK 8 之前的版本，只要 Javac 编译的时候使用了 `-g` 编译选项（debug 编译），那么我们也可以在字节码源文件中自动获取参数名称。
> 
> ```java
> @Action("/users/{id}")
> public void action(@PathVariable int id) {
>     System.out.println(id);
> }
> ```
>
> 是不是这样的代码更优雅！记住其他的类似的 Annotation 都可以这么干哦！

#### @RequestParam

用于从 Request 中获取参数，等价于 `request.getParameter(...)`，支持自动类型转换。

```java
@Action
public void action(
        @RequestParam("id") String id, 
        @RequestParam("file") FilePart file
    ) {
    ...
}
```

`@RequestParam` 目前支持注入常见的类型：
* JAVA 基本类型/包装类型
* 数组类型
* FilePart
* JSONAware/JSONObject/JSONArray
* org.w3c.dom.Document
* JAXBElement

也可以实现自定义类型的 `@RequestParam` 注入，只要实现下面的接口，并加入 `@Managed` annotation 即可：

```java
public interface RequestParamGetter<T> {

    public T get(RequestContext ctx, ParameterInfo parameter, String name) throws Exception;

}
```

#### @RequestForm

将多个 Request 参数，注入到一个 Form 对象中

```java
public class LoginInfo {
    private String username;
    private String password;
    private boolean rememberMe;

    // 省略 getter, setter
}

@Action
public void action(@RequestForm LoginInfo form) {
    System.out.println(form.getUsername());
    System.out.println(form.getPassword());
    System.out.println(form.isRememberMe());
}
```


#### @RequestBody

如果你的 Request 使用 Payload 方式传递内容，比如 JSON 或者 XML 格式，那么我们可以使用 `@RequestBody` 来获取。

```java
@Action
public void action(@RequestBody Document xmlDocument) {
    ...
}
```

目前，`RequestBody` 支持下面几种类型：
* JSONAware/JSONObject/JSONArray
* org.w3c.dom.Document
* JAXBElement

也可以实现自定义类型的 `@RequestBody` 注入，只要实现下面的接口，并加入 `@Managed` annotation 即可：

```java
public interface RequestBodyGetter<T> {

    public T get(RequestContext ctx, ParameterInfo parameter) throws Exception;

}
```

#### @RequestHeader

等价于 `Request.getHeader(...)`，支持自动类型转换。

#### @RequestCookie

获取 Cookie 信息，支持自动类型转换。

#### @RequestAttribute

等价于 `Request.getAttribute(...)`

#### @SessionAttribute

等价于 `Session.getAttribute(...)`

#### @ServletContextAttribute

等价于 `ServletContext.getAttribute(...)`

#### @InitParameter

等价于 `ServletContext.getInitParameter(...)`，支持自动类型转换。


#### 根据参数的类型注入

#### RequestContext

Request 上下文对象

#### Model

Action 要返回的 Model 对象

#### HttpServletRequest

Request  对象

#### HttpServletResponse

Response  对象

#### HttpSession

Session  对象

#### ServletContext

ServletContext 对象

#### FilePart

单个上传文件对象

#### FilePart[]

所有上传文件对象

#### RequestParameterMap

a map of request parameter.

#### RequestParameterValuesMap

a map of request parameter values.

#### RequestHeaderMap

a map of request header.

#### RequestHeaderValuesMap

a map of request header values.

#### RequestCookieMap

a map of request cookie.

#### RequestAttributeMap

a map of request attributes.

#### SessionAttributeMap

a map of session attributes.

#### ServetContextAttributeMap

a map of servlet context attributes.

#### ServetContextInitParameterMap

a map of servlet context init parameters.


进阶（自定义 Annotation 或者类型）
------------------------------------

jetbrick-webmvc 已经内置了大量的参数注入方式，怎么用都可以，并且我们也提供了扩展机制。

如果用户想自定义注入的对象或者 Annotation，请看下面的章节：[自定义注入 Annotation
](custom-annotation.html)



