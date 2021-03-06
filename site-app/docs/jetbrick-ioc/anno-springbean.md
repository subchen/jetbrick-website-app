@SpringBean
===============================

IoC 容器支持 Spring IoC 容器作为外部容器，允许将 Spring IoC 容器中管理的 Bean 注入到对象中。

首先加入依赖
-------------

```xml
<dependency>
    <groupId>com.github.subchen</groupId>
    <artifactId>jetbrick-ioc-spring</artifactId>
    <version>{{IOC-VERSION}}</version>
</dependency>
```


看一个简单的例子
---------------

**spring.xml**

```xml
<bean id="dataSource" class="..." />
```

**Java 代码**

```java
@IocBean
public class JdbcUtils {
	@Inject
    private Foo foo;

	@SpringBean("dataSource")
    private DataSource dataSource;

}
```

如果你的 Spring 在 `web.xml` 中进行了初始化（可以通过 `WebApplicationContextUtils.getWebApplicationContext(ServletContext)` 获取），那么 JdbcUtils.dataSource 将会成功的被注入。

如果你的 Spring 没有在 `web.xml` 中进行初始化，那么需要将 Spring 的 `ApplicationContext` 对象手动加入到 IoC 容器中。如下：

```java
ApplicationContext appctx = ...
MutableIoc ioc = ...
ioc.addBean(ApplicationContext.class, appctx);
```

