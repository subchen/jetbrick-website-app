直接使用 HttpServlet
==============================

`jetbrick-template` 可以直接作为 `HttpServlet` 使用，就像 JSP 一样。


Maven 依赖
------------------

```xml
<dependency>
    <groupId>com.github.subchen</groupId>
    <artifactId>jetbrick-template-servlet</artifactId>
    <version>{{TEMPLATE-VERSION}}</version>
</dependency>
```


web.xml 配置
----------------------------

```xml
<context-param>
    <param-name>jetbrick-template-config-location</param-name>
    <param-value>/WEB-INF/jetbrick-template.properties</param-value>
</context-param>

<servlet>
    <servlet-name>jetbrick-template</servlet-name>
    <servlet-class>jetbrick.template.web.servlet.JetTemplateServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>jetbrick-template</servlet-name>
    <url-pattern>*.jetx</url-pattern>
</servlet-mapping>
```


范例源码
--------------------------------

具体例子代码参考： https://github.com/subchen/jetbrick-template-2x-samples/

