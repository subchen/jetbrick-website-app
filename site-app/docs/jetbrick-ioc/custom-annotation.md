自定义注入 Annotation
===============================

学习了前面的 `@Inject`, `@Config` 和 `@SpringBean` 注入 Annotation，这回 jetbrick 带来了更加强大的自定义 Annotation 注入功能。

啥意思？ 看看下面的代码就明白了。


看代码学习
--------------------------

```java
@IocBean
public class Hello {

    @InjectMe
    private String me;

}
```

`@InjectMe` 是什么？没见过啊？也能实现注入？ YES！

其实这个 `@InjectMe` 是由用户自定义的，如下的代码：

```java
import java.lang.annotation.*

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@InjectFieldWith(InjectMeInjector.class)
public @interface InjectMe {
	...
}
```

在这里，`@InjectMe` 只是一个普通的 Annotation。唯一不同的是，这个 Annotation 本身存在一个 `@InjectFieldWith(...)` 的 Annotation。

正是这个 `@InjectFieldWith(InjectMeInjector.class)` 用来表示 `@InjectMe` 将可以被用来注入字段（需要配合 `@Target({ElementType.FIELD})` 来使用）。

那么如何来注入呢？就是由 `@InjectFieldWith(...)` Annotation 的参数类来实现的，在本例子中就是 `InjectMeInjector` 这个 Class 来实现的。

其中 `InjectMeInjector` 实现如下：（字段注入需要实现 `FieldInjector` 接口）

```java
public class InjectMeInjector implements FieldInjector {
    private Field field;

    @Override
    public void initialize(FieldContext ctx) {
        this.field = ctx.getField();
    }

    @Override
    public void set(Object object) throws Exception {
        field.set(object, "AAAAA");
    }
}
```

怎么样，学会了吗？

介绍完了自定义注入字段 Annotation，采用类似的方法，我们还可以定义注入参数 Annotation。


自定义字段注入
------------------------

```java
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@InjectFieldWith(XXXFieldInjector.class)
public @interface XXX {
    ...
}

public class XXXFieldInjector implements FieldInjector {
    ...
}
```


自定义参数注入 (构造函数参数)
---------------------------------

```java
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@InjectParameterWith(XXXParameterInjector.class)
public @interface XXX {
    ...
}

public class XXXParameterInjector implements ParameterInjector {
    ...
}
```

> [info] **提示**：我们也可以让一个注解既支持字段注入，也支持参数注入。怎么做？我们可以参考 `@Config` 或者 `@SpringBean` Annotation 的代码实现。

