JFinal 集成
============================

`jetbrick-template` 可以和 `JFinal` 进行集成。


Maven 依赖
------------------

```xml
<dependency>
    <groupId>com.github.subchen</groupId>
    <artifactId>jetbrick-template-jfinal</artifactId>
    <version>{{TEMPLATE-VERSION}}</version>
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
        // 注意, 这里请不要使用 me.setBaseViewPath("...") 更改 prefix path
        // 需要的话，请使用模板的 `$loader.root` 代替
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

> [info] **好消息**：JFinal 内置的 ActiveRecord 的 `Model` 和 `Record` 现在可以直接通过 `${model.name}` 方式访问拉!


范例源码
--------------------------------


具体例子代码参考： https://github.com/subchen/jetbrick-template-2x-samples/

