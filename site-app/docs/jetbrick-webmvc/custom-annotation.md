自定义 Action 参数注入 Annotation
=====================================

在 [Action 参数注入](action-inject.html)章节中，我们看到 `@Inject`, `@Config` 等用于 Controller 注入的 Annotation，还看到了 `@PathVariable`, `@RequestParam` 等专门用于 Action 方法的参数注入的 Request/Session 相关的 Annotation。

同样的，我们在 IoC 相关的章节中也介绍了[IoC 自定义注入 Annotation](../jetbrick-ioc/custom-annotation.html)。但是在 IoC 容器中，只能使用 Request/Session 无关的自定义 Annotation。而本章节将介绍的是专门用于 Action 方法的参数注入的 Request 相关的自定义 Annotation。


看代码学习
------------------------

先创建自己的 Annotation。

```java
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@ManagedWith(RequestHeaderArgumentGetter.class)
public @interface RequestHeader {
    String value();
}
```

注意，我们在这个 Annotation 上面标注了一个 `@ManagedWith(RequestHeaderArgumentGetter.class)`，这个是关键点。告诉 jetbrick，这个 Annotation 将会由 `RequestHeaderArgumentGetter` 来处理。

下面我们在创建 RequestHeaderArgumentGetter.java

```java
@Managed
public class RequestHeaderArgumentGetter implements AnnotatedArgumentGetter<RequestHeader, String> {
    private String name;

    @Override
    public void initialize(ArgumentContext<RequestHeader> ctx)
        this.name = ctx.getAnnotation().value();        
    }

    @Override
    public String get(RequestContext ctx) {
        return ctx.getRequest().getHeader(name);
    }
}
```

作为一个准备注入 Action 参数的自定义的 Annotation，我们的 Class 必须实现 `jetbrick.web.mvc.action.annotations.AnnotatedArgumentGetter<A, T>` 接口。

其中，泛型参数 `A` 代表你要处理的自定义 Annotation，`T` 代表返回值类型。


自定义注入类型
-------------------------

```java
@ManagedWith(SessionUserArgumentGetter.class)
public class SessionUser {
    private int id;
    private String username;
    ...
}
```

其中 `@ManagedWith(SessionUserArgumentGetter.class)` 描述了 `SessionUser` 对象的注入将会由 `SessionUserArgumentGetter` 来实现。

我们再来看 SessionUserArgumentGetter 参数注入处理器：

```java
@Managed
public class SessionUserArgumentGetter implements TypedArgumentGetter<SessionUser> {
    @Override
    public SessionUser get(RequestContext ctx) {
        return (SessionUser) ctx.getSession().getAttribute("SESSION_USER");
    }
}
```

其中，按类型注入必须实现 `jetbrick.web.mvc.action.annotations.TypedArgumentGetter<T>` 接口。

再来看一下如何使用这个 `SessionUser`。

```java
@Controller
public class PassportController {
    @Inject UserService userService;

    @Action("/passport/changepwd")
    public String changePassword(SessionUser me, @RequestParam("pwd") String pwd) {
        userService.changePassword(me.getId(), pwd);
        return "ok.jsp";
    }
}
```

如果我们要注入的类型是系统 Class 或者第三方提供的，无法为其添加 `@ManagedWith(XXXArgumentGetter.class)` Annotation，那么也没关系。只要有 XXXArgumentGetter.class 就可以了，比如我们想要注入 `java.util.Locale`，我们只要实现一个对应的 `LocaleArgumentGetter` 即可。如下：

```java
@Managed(Locale.class)
public class LocaleArgumentGetter implements TypedArgumentGetter<Locale> {
    @Override
    public Locale get(RequestContext ctx) {
        return ctx.getSession().getLocale();
    }
}
```

其中 `@Managed(Locale.class)` 会将 `Locale` 对象和 `LocaleArgumentGetter` 绑定起来。如果省略 `@Managed` 的参数，那么 jetbrick 会自动从 `TypedArgumentGetter<Locale>` 定义中获取泛型参数。

