Nutz 集成
============================

`jetbrick-template` 可以和 `Nutz` 进行集成。


Maven 依赖
------------------

```xml
<dependency>
    <groupId>com.github.subchen</groupId>
    <artifactId>jetbrick-template-nutz</artifactId>
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
```


将视图工厂整合进应用中
-----------------------------

在主模块上，加：`@Views({JetTemplateViewMaker.class})` 注解

```java
@Views({JetTemplateViewMaker.class})
public class MainModule {

}
```

Action 中使用 jetx 视图
----------------------------------

```java
@At
@Ok("jetx:/WEB-INF/html/user_info.jetx")
public String list(@Param("name") String name, HttpServletRequest request){
    if (name != null) {
        return name;
    }
    return "测试";
}
```

模板范例
--------------------------------

```
#define(String obj)
${obj}
```

获得输出
-----------------------

```
测试
```

范例源码
--------------------------------

具体例子代码参考： https://github.com/subchen/jetbrick-template-webmvc-samples/

