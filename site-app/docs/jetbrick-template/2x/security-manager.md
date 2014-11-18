安全管理器 SecurityManager
=============================

由于 `jetbrick-template` 支持在模板中直接调用 Java 类和方法，在一些特殊的项目中(如CMS)，如果允许用户上传自己的模板，那样就会涉及到安全性问题。

考虑到这种情况，`jetbrick-template` 引入了自己的安全管理器。


安全管理器能做什么
-----------------------

* 拦截 Class 的引用
* 拦截 Class 构造函数调用
* 拦截 Class 的方法调用(静态和动态方法)
* 拦截 Class 的字段调用(静态和动态字段)


如何启用安全管理器
----------------------

安全管理器和 ResourceLoader 绑定在一起，也就是说，每个 ResourceLoader 可以有独立的安全管理器。

安全管理器默认通过一个黑白名单列表来实现。黑白名单可以放在一个单独的文本文件中，也可以直接放在配置文件中。用户也可以实现自定义的安全管理器。

```
$securityManager = jetbrick.template.JetSecurityManagerImpl
# 
#$securityManager.configFile = ${web.root}/WEB-INF/jetx-black_white_list.txt
#
$securityManager.nameList = \
                            -java.lang.System.exit, \
                            -java.lang.reflect, \
                            -java.sql, \
                            -javax.tools, \
                            -java.io, \
                            +java.io.File.getName, \
                            +java.io.File.getPath, \
                            -sun

$loader = jetbrick.template.loader.FileSystemResourceLoader
$loader.root = /opt/templates/
$loader.reloadable = true
$loader.securityManager = $securityManager

jetx.template.loaders = $loader
```

### JetSecurityManagerImpl 属性说明

* `configFile` 用外部文件配置黑白名单。（每行一个名单）
* `nameList` 以逗号分隔的黑白名单。

### 黑白名单格式说明

**前缀符号：**

* `+` 开头代表白名单
* `-` 开头代表黑名单
* 没有开始符号，则默认为白名单

**名单格式：**

* 包名： `pkg`
* 类名名： `pkg.class`
* 方法名： `pkg.class.method`
* 字段名： `pkg.class.field`

**实例：**

```
-java.sql                           // 禁止访问 java.sql 下面的任何 Class，包括所有孙子包下面的 Class
-java.lang.System.exit              // 禁止调用 System.exit() 方法
+java.util.Collections.EMPTY_LIST   // 允许访问 Collections.EMPTY_LIST 字段
```


为 `JetEngine.createTemplate()` 创建的模板，设置安全管理器
--------------------------------------------------------------------------------

由于 `JetEngine.createTemplate()` 以源码方式创建的模板，并没有使用 `ResourceLoader` 来加载，所有必须使用其他的方式指定 `JetSecurityManager`

配置如下：

```
jetx.template.source.securityManager = $securityManager
```


自定义安全管理器
--------------------

虽然 `jetbrick-template` 已经内置了一个默认的安全管理器实现，但是用户也可以实现自己的安全管理器。

需要实现接口： `jetbrick.template.JetSecurityManager`



