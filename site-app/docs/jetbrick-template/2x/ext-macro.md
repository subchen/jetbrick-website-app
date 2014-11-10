宏 Macro
====================================

`jetbrick-template` 支持用户在模板中定义可重用的模板片段，我们称之为`宏 Macro`。

比如下面的代码范例：

```
#macro label_input(String label, String name, String help)
  <label class="control-label" for="${name}">${label}:</label>
  <div class="controls">
    <input id="${name}" name="${name}" type="text" class="input-xlarge" />
    <p class="help-block">${help}</p>
  </div>
#end

#call label_input("用户名", "username", "请输入合法的用户名称.")
#call label_input("邮箱", "email", "请输入合法的用户邮箱.")
```

定义宏 Macro
---------------------

语法：

```
#macro name ( [arg_type] arg_name,  ... )
    ...
#end
```

* 参数类型在 `strict` 模式下，必须定义
* 不允许在相同的模板中定义同名 macro


调用宏 Macro
--------------------

有 2 种方法可以调用 macro

* `#call macro_name(args...)` 

    ```
    #call label_input("用户名", "username", "请输入合法的用户名称.")
    #call label_input("邮箱", "email", "请输入合法的用户邮箱.")
    ```

* `${macroGet(macro_name, args...)}`

    ```
    ${macroGet("label_input", "用户名", "username", "请输入合法的用户名称.")}
    ${macroGet("label_input", "邮箱", "email", "请输入合法的用户邮箱.")}
    ```


使用外部的宏定义模板
--------------------------

如果我们有很多公共的宏需要共享，那么我们可以将这些宏定义在一个独立的模板文件中。

比如我们定义了一个包含了多个宏的模板：`/commons/macros.jetx`

```
#macro hello(...)
  ...
#end

#macro hi(...)
  ...
#end
```

然后，对于要使用这些宏的模板，通过 `#options(loadmacro="...")` 的方式将宏文件导入就可。

```
#options(
  loadmacro = "/commons/macros.jetx"
)

#call hello(...)
#call hi(...)
```

> [info] **优先级**：如果导入的宏和模板中定义的宏名称相同，那么将无法使用，而只能使用模板中定义的。(即模板定义优先)


全局导入宏定义
--------------------------

如果我们要把定义好的外部宏模板，默认共享给所有的模板，而不是每次通过 `#options(loadmacro="...")` 的方式导入，那么我们可以这么做：

* API 动态注册

    ```java
    JetEngine engine = ...
    JetTemplate template = engine.getTemplate("/commons/macros.jetx");
    GlobalResolver resolver = engine.getGlobalResolver();
    resolver.registerMacros(template);
    ```

* 在配置文件中注册 jetbrick-template.properties 

    ```
    jetx.import.macros = /commons/macros.jetx
    ```


