@Inject
================================

上一章节我们介绍了 `@IocBean` 可以将 Java 对象自动加入到 IoC 容器中进行管理。这一章我们将介绍 `@Inject` 注解，来实现对象的注入。


简单的例子
-------------------

```java
package jetbrick.docs.demo;

@IocBean
public class User {
    public String getName() {
        return "jetbrick";
    }
}

@IocBean
public class Hello {
    @Inject User user;

    public void says() {
        System.out.println("Hello " + user.getName());
    }
}

// 调用
public static void main(String args) {
    Ioc ioc = ...
    Hello hello = ioc.getBean(Hello.class);
    hello.says();
}
```

看清楚了吗？我们通过 `@Inject` 注解，我们已经将 User 对象成功的注入到了 Hello 对象中。


@Inject 可配置的属性
------------------------

1. **value**

    要注入对象的名称 name，缺省情况下，将根据对应类型的完整类名作为 name。
    
    ```java
    @Inject
    java.sql.DataSource dataSource

    @Inject("mysql")
    java.sql.DataSource mysqlDataSource
    ```
    
    第一个 dataSource 将获取 `ioc.getBean("java.sql.DataSource")` 这样的对象。

2. **required**

    表示如果注入的对象不存在，是否要抛出 NullPointerExpcetion。

    默认情况下，`required=true`


字段注入
---------------

就像我们上面看到的例子一样，我们用 `@Inject` 注入的是字段。

只要不是被声明成 `final` 的字段，IoC 都可以实现注入。不管是 `public` 的还是 `private` 的，亦或者是 `static` 的字段都没问题。

```java
@Inject("log4j")
private Logger logger;
```

构造函数注入
---------------

除了最常见的字段注入之外，我们还提供了构造函数注入方式。


### 指定构造函数

```java
@IocBean
public class Hello {
    private User user;

    @Inject
    public Hello(User user) {
        this.user = user;
    }

    public void says() {
        System.out.println("Hello " + user.getName());
    }
}
```

通过将 `@Inject` 声明在构造函数上面，我们就可以使用指定的构造函数来初始化对象。


### 默认构造函数

如果没有 `@Inject` 声明的构造函数，那么必须存在一个空的构造函数，并且是 public 的。


### 构造函数参数注入

默认情况下，每个参数都缺省使用了 `@Inject`，如果我们需要自定义，那么我们可以给每个参数手动指定 `@Inject`。如下：

```java
@IocBean
public class Test {
    private Foo foo;
    private Bar bar;

    @Inject
    public Hello(
        @Inject("foo") Foo foo,
        @Inject(required=false) Bar bar
    ) {
        this.foo = foo;
        this.bar = bar;
    }
}
```

