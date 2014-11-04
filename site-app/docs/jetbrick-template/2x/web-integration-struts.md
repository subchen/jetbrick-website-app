Struts 2.x 集成
============================

`jetbrick-template` 可以和 `Struts 2.x` 进行集成。


Maven 依赖
------------------

```xml
<dependency>
    <groupId>com.github.subchen</groupId>
    <artifactId>jetbrick-template-struts</artifactId>
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
```


struts.xml 配置
----------------------------

这个配置是告诉 `struts` 使用 `jetbrick.template.web.struts.JetTemplateResult` 这个类来处理采用 `jetbrick-template` 格式的模板：

```xml
<result-types>
    <result-type name="jetx" class="jetbrick.template.web.struts.JetTemplateResult" />
</result-types>
```

接下来配置你的 action 的 result 如下：

```xml
<action name="index" class="jetbrick.template.samples.struts.action.IndexAction">
    <result type="jetx">/index.jetx</result>
</action>
```


范例源码
--------------------------------

具体例子代码参考： https://github.com/subchen/jetbrick-template-2x-samples/

