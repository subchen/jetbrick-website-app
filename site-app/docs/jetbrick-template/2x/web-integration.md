Web 集成简述
===============

`jetbrick-template` 除了作为普通的模板引擎嵌入在 Application 中外，大部分情况下还会和各种 WebMVC 框架整合作为前端的 View，来代替过时的 `JSP` 或者 `Velocity`。

目前已近集成了几种流行的 Web 框架：

* [Servlet](web-integration-servlet.html)
* [Filter](web-integration-filter.html)
* [Jetbrick webmvc](web-integration-jetbrickmvc.html)
* [Springmvc](web-integration-springmvc.html)
* [JFinal](web-integration-jfinal.html)
* [Jodd 3.5.1+](web-integration-jodd.html)
* [Struts 2.x](web-integration-struts.html)
* [Nutz](web-integration-nutz.html)


[点击这里下载各种集成方式的演示 demo](download.html#samples)


Web 中的默认隐含对象
=========================

当 `jetbrick-template` 被用作 Web 应用中时候，会自动引入下面的对象，这些对象在所有的模板中全局可访问。

隐含对象         | 类 型                | 说 明
-----------------|----------------------|----------------------------------------------
application      | ServletContext       |
session          | HttpSession          |
request          | HttpServletRequest   |
response         | HttpServletResponse  |
applicationScope | Map<String,Object>   | 快捷访问 servletContext.getAttribute(name)
sessionScope     | Map<String,Object>   | 快捷访问 session.getAttribute(name)
requestScope     | Map<String,Object>   | 快捷访问 request.getAttribute(name)
param            | Map<String,String>   | 快捷访问 request.getParameter(name)
paramValues      | Map<String,String[]> | 快捷访问 request.getParameterValues(name)
CONTEXT_PATH     | String               | 快捷访问 request.getContextPath()
WEBROOT_PATH     | String               | 完整的webapp路径: http://127.0.0.1:8080/myapp
BASE_PATH        | String               | 专门用于 &lt;base href="${BASE_PATH}">



**下面的例子演示了如何使用这些隐含变量：**

模板如下：

```
request.requestURI == ${request.requestURI}
request.getParameter("name") == ${param.name}
request.getAttribute("items") == ${requestScope.items}
session.getAttribute("user") == ${sessionScope.user}
```

特别需要说明的一点是：模板中使用或者声明的全局变量不光会从 `context` 中获取，在 Web 应用中，还会从 `requestScope`，`sessionScope`，`applicationScope` 中查找对应的内容。

默认的查顺序如下：

1. context
2. requestScope
3. sessionScope
4. applicationScope

也就是说，如果存在 `request.getAttribute("user")` 的情况下 `${user}` 等价于 `${requestScope.user}`。


Web 下的资源加载
========================


在 Web 集成模式中，采用以下的默认值：

```
template.loader = $loader

$loader = jetbrick.template.loader.ServletResourceLoader
$loader.root = /
$loader.reloadable = false
```

对于 `ServletResourceLoader` 的来说，`root` 的路径相对于 webapp 的根目录。如果把模板放置在 `/WEB-INF/templates` 目录下，可以这么配置：

```
template.loader = $loader

$loader = jetbrick.template.loader.ServletResourceLoader
$loader.root = /WEB-INF/templates
$loader.reloadable = false
```


