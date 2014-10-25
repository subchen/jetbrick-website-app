核心对象 Core
================

在 `jetbrick-template` 中，有几个核心对象：

* `JetEngine` - 代表一个模板引擎
* `JetTemplate` - 代表一个模板文件
* `InterpretContext` - 模板运行期上下文对象


JetEngine
-------------

整个模板引擎的由 `JetEngine` 驱动，不同的 `JetEngine` 对象可以使用不同的配置。

### 如何创建 JetEngine？

1. `JetEngine.create()`
	
    在 classpath 根目录下面自动查找 `jetbrick-template.properties` 文件。如果文件不存在，则使用默认配置。

2. `JetEngine.create(File)`
	
    从用户指定的 `File` 文件中加载系统配置，该文件必须是一个 `.properties` 文件。

3. `JetEngine.create(Properties)`
	
    从用户指定的 `Properties` 对象中加载系统配置。

4. `JetEngine.create(Properties, File)`
	
    先加载 `Properties` 对象, 然后在加载 `File` 文件中的配置。`File` 中的配置会覆盖 `Properties` 中的配置。 


具体配置信息请移步：[配置信息 Config](config.html)

> [info] **最佳实践**：一般在一个 Application 或者 Webapp 中，我们只需要一个 `JetEngine` 对象就可以了，我们推荐使用单例模式创建 `JetEngine`。


### 获取配置信息

可以通过 `JetEngine.getConfig()` 来获取配置文件中的配置信息。


### 获取 JetTemplate 对象


* 获取 `JetTemplate` 对象：

    ```java
    public JetTemplate getTemplate(String name) throws ResourceNotFoundException;
    ```

    > [info] **提示**：`getTemplate()` 内部有 cache 机制，不会重复创建相同的模板，如果对应的 `ResourceLoader` 开启了 `reloadable=true`，那么会自动加载被修改过的模板。


* 检查模板是否存在：

    ```java
    public boolean checkTemplate(String name);
    ```

    > [warn] **注意**：
    >
    > * 对于一个 template 的 `name`，应该以 `/` 开头，并且以 `/` 作为分隔符 (Unix 风格)。比如：`/templates/index.jetx`
    > * 不管你的模板位于哪里，相对于一个模板的 root 目录来说，都应该使用标准化的路径名称。
    > * 对于 #include 等指令，则可以使用相对路径，如： `#include("../commons/header.jetx")`。



### 以源代码方式直接创建模板对象

```java
public JetTemplate createTemplate(String source);
```

范例：

```java
JetTemplate template = engine.createTemplate("${1+2*3}");
StringWriter out = new StringWriter();
template.render(null, out);
Assert.assertEquals("7", out.toString());
```

> [warn] **注意**： `createTemplate()` 每次都会创建新的 `JetTemplate` 对象，如果需要缓存，请自行维护。



JetTemplate
-----------------

对应于一个模板文件，我们通过 `JetEngine.getTemplate(name)` 获取。

如果模板不存在，则抛出 `ResourceNotFoundException`。

在得到了模板对象之后，我们可以通过下面几个方法可以对模板进行渲染：

```java
public void render(Map<String, Object> context, Writer out);
public void render(Map<String, Object> context, OutputStream out);
```

通常，我们使用 `Map<String, Object>` 存储我们的 `context` 对象。

> [info] **提示**： `context` 对象在模板运行期间，并不会受到模板污染，即数据不会被改变（保证数据的无侵入性）。


InterpretContext
-----------------------

在模板运行期间，`InterpretContext` 保存了当前的运行时环境。

`InterpretContext` 对象主要用于在扩展方法，扩展函数，Tag 的实现过程中，动态获取/设置上下文信息。


在模板的运行期间，`InterpretContext` 对象会绑定在当前线程的 `Thread Local context` 中，这样我们可以在任意的地方，通过下面的 API 来获取 `InterpretContext` 对象：

```java
InterpretContext ctx = InterpretContext.current();
```

* 获取当前模板的 context

    ```java
    ctx.getValueStack().getValue(name);
    ```

* 设置当前模板的 context

    ```java
    ctx.getValueStack().setLocal(name, value);
    ```

* 获取 JetEngine

    ```java
    ctx.getEngine();
    ```

