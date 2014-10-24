直接使用 Filter 模式
==============================

`jetbrick-template` 可以直接作为 `Filter` 使用。


Maven 依赖
------------------

```xml
<dependency>
    <groupId>com.github.subchen</groupId>
    <artifactId>jetbrick-template-servlet</artifactId>
    <version>{{TEMPLATE.VERSION}}</version>
</dependency>
```


web.xml 配置
----------------------------

```xml
<context-param>
    <param-name>jetbrick-template-config-location</param-name>
    <param-value>/WEB-INF/jetbrick-template.properties</param-value>
</context-param>

<filter>
    <filter-name>jetbrick-template</filter-name>
    <filter-class>jetbrick.template.web.servlet.JetTemplateFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>jetbrick-template</filter-name>
    <url-pattern>*.jetx</url-pattern>
</filter-mapping>
```


范例源码
--------------------------------

具体例子代码参考： https://github.com/subchen/jetbrick-template-webmvc-samples/

