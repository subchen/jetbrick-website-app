@Config
=======================

在前面的章节中，我们使用 `@Inject` 注入 IoC 容器中管理的对象。在这个章节中，我们将介绍另外一种配置文件属性的注入方式。

假设我们有这样一个配置文件：

**app.properties**

```
app.encoding = utf-8
app.debug = true
app.users = admin, test
```

**AppConfig.java**

```java
@IocBean
public class AppConfig {

    @Config("app.encoding")
    private String encoding;

    @Config(value="app.debug", defaultValue="false")
    private boolean debug;

    @Config("app.users")
    private List<String> users;

    ...
}
```

然后这样下面的代码将自动将 `app.properties` 中的属性注入到 `AppConfig` 类中。

```java
MutableIoc ioc = new MutableIoc();
ioc.load(new IocPropertiesLoader("classpath:app.properties");
ioc.addBean(AppConfig.class);

AppConfig config = ioc.getBean(AppConfig.class);
```

心动了吗？我们的 `AppConfig` 竟然还能直接注入 `app.properties` 配置文件中配置的属性。

并且注入的时候，还会自动进行类型转换，以匹配目标类型。

和 `@Inject` 类似，`@Config` 可以被用在字段注入和参数注入。

再来一个构造函数参数注入的例子:

```java
@IocBean
public class Hello {
    private final Foo foo;
    private final String encoding;

    @Inject
    public Hello(
        @Inject("foo") Foo foo,
        @Config("app.encoding") String encoding
    ) {
        this.foo = foo;
        this.encoding = encoding;
    }
}
```

怎么样？有了 `@Config`，我们的注入更方便了。

