全局变量 Global Context
================================

通过 `JetEngine.getGlobalContext()`，可以得到一个 `JetGlobalContext` 对象，我们可以通过这个对象，来动态设置全局变量的。


JetGlobalContext API
-------------------------

* define(Class<?> type, String name)

    为全局变量定义类型(在 strict 模式下，必须定义类型)

* set(String name, Object value)

    设置全局变量

* set(Class<?> class, String name, Object value)

    define + set 快捷版本


<div class="info">
**最佳实践**：使用 `set(Class<?> class, String name, Object value)` 为每个全局变量设置变量类型。
<div>


范例 Samples
-------------------------

```java
JetEngine engine = JetEngine.create();

JetGlobalContext globalContext = engine.getGlobalContext();

// 定义一个全局变量类型(自定义类型，不设置值)
globalContext.define(AuthService.class, "authService");

// 加入一个全局变量值
String tmpdir = System.getProperty("java.io.tmpdir");
globalContext.set(String.class, "tmpdir", tmpdir);

```