快速入门 Hello World
===================================

编程方式
---------------------------

显式的将对象加入到 IoC 容器中，并通过 `@Inject` 进行对象注入。

```java
package jetbrick.docs.demo;

public class User {
    public String getName() {
        return "jetbrick";
    }
}

public class Hello {
    @Inject
    private User user;

    public void says() {
        System.out.println("Hello " + user.getName());
    }
}

// 调用代码
public class Test {
    public static void main(String args[]) {
        MutableIoc ioc = new MutableIoc();
        ioc.addBean(User.class);
        ioc.addBean(Hello.class);
        Hello hello = ioc.getBean(Hello.class);
        hello.says(); // got "Hello jetbrick"
    }
}
```


通过 annotation 自动发现
------------------------------------

通过 `@IocBean` 注解来自动将对象加入到 IoC 容器中，并通过 `@Inject` 进行对象注入。

```java
package jetbrick.docs.demo;

@IocBean
public class User {
    private String name = "jetbrick";

    public String getName() {
        return name;
    }
}

@IocBean
public class Hello {
    @Inject
    private User user;

    public void says() {
        System.out.println("Hello " + user.getName());
    }
}

// 调用代码
public class Test {
    public static void main(String args[]) {
        MutableIoc ioc = new MutableIoc();
        ioc.load(new IocAnnotationLoader("jetbrick.docs.demo"));
        Hello hello = ioc.getBean(Hello.class);
        hello.says();
    }
}
```


.properties 配置方式
--------------------------------

在配置文件中配置，所有 `$` 开头的对象，将自动加入到 IoC 容器中。

**ioc.properties**

```
$user = jetbrick.docs.demo.User
$user.name = WORLD

$hello = jetbrick.docs.demo.Hello
$hello.user = $user
```

**Java 代码**

```java
package jetbrick.docs.demo;

public class User {
    private String name;

    public String getName() {
        return name;
    }
}

public class Hello {
    private User user;

    public void says() {
        System.out.println("Hello " + user.getName());
    }
}

// 调用代码
public class Test {
    public static void main(String args[]) {
        MutableIoc ioc = new MutableIoc();
        ioc.load(new PropertiesIocLoader("classpath:app.properties"));
        Hello hello = (Hello) ioc.getBean("hello");
        hello.says(); // got "Hello WORLD"
    }
}
```


自定义配置文件？
--------------------------------

既然我们可以支持 `.properties` 配置文件，那么我们也可以支持 XML，JSON 等格式的配置文件。

其实我们只要实现一个 `jetbrick.ioc.loader.IocLoader` 接口的自定义加载器就可以了。

```java
public interface IocLoader {
    public void load(MutableIoc ioc);
}
```

具体怎么实现，可以参考：`jetbrick.ioc.loader.IocAnnotationLoader` 或者 `jetbrick.ioc.loader.IocPropertiesLoader`。

同时，如果大家实现自己的 IocLoader，欢迎 share 相应的实现。

