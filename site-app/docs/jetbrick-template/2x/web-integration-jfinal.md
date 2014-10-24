JFinal 集成
============================

`jetbrick-template` 可以和 `JFinal` 进行集成。


Maven 依赖
------------------

```xml
<dependency>
    <groupId>com.github.subchen</groupId>
    <artifactId>jetbrick-template-jfinal</artifactId>
    <version>{{TEMPLATE.VERSION}}</version>
</dependency>
```


web.xml 配置
----------------------------------------------

```xml
<context-param>
    <param-name>jetbrick-template-config-location</param-name>
    <param-value>/WEB-INF/jetbrick-template.properties</param-value>
</context-param>
```


修改 JFinal 主配置文件
--------------------------

```java
public class JetxConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        me.setMainRenderFactory(new JetTemplateRenderFactory());
        ...
    }

}
```

新建一个 Controller 测试
--------------------------

```java
public class UsersController extends Controller {
    public void index() {
        setAttr("userlist", DaoUtils.getUserList());
        render("/users.jetx");
    }
}
```


范例源码
--------------------------------


具体例子代码参考： https://github.com/subchen/jetbrick-template-webmvc-samples/

