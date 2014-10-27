全局配置选项
===================

名称                                      | 说明                       | 默认值
------------------------------------------|----------------------------|----------------
[jetx.import.classes][1]                  | 默认导入的 java 类         |
[jetx.import.defines][2]                  | 默认声明的 java 变量类型   |
[jetx.import.methods][3]                  | 默认导入的扩展方法         |
[jetx.import.functions][4]                | 默认导入的扩展函数         |
[jetx.import.tags][5]                     | 默认导入的 tags            |
[jetx.import.macros][6]                   | 默认导入的 macros          |
[jetx.autoscan.packages][7]               | 在指定的包中进行自动扫描   |
[jetx.autoscan.skiperrors][8]             | 自动扫描的时候跳过遇到错误 | false
[jetx.template.loaders][11]               | 模板资源载入Class          | jetbrick.template.loader.ClasspathResourceLoader
[jetx.template.suffix][12]                | 默认模板文件扩展名         | .jetx
[jetx.input.encoding][13]                 | 模板源文件的编码格式       | utf-8
[jetx.output.encoding][14]                | 模板输出编码格式           | utf-8
[jetx.syntax.strict][21]                  | 是否默认启用严格语法模式   | false
[jetx.syntax.safecall][22]                | 是否默认启用全局的安全调用 | false
[jetx.trim.leading.whitespaces][23]       | 是否要删除模板开头处的空白 | true
[jetx.trim.directive.whitespaces][24]     | 是否要删除指令行两边的空白 | true
[jetx.trim.directive.comments][25]        | 是否支持指令两边增加注释对 | false
[jetx.trim.directive.comments.prefix][26] | 指令注释的开始部分         | &lt;!--
[jetx.trim.directive.comments.suffix][27] | 指令注释的结束部分         | --&gt;

[1]: #jetx-import-classes
[2]: #jetx-import-defines
[3]: #jetx-import-methods
[4]: #jetx-import-functions
[5]: #jetx-import-tags
[6]: #jetx-import-macros
[7]: #jetx-autoscan-packages
[8]: #jetx-autoscan-skiperrors
[11]: #jetx-template-loaders
[12]: #jetx-template-suffix
[13]: #jetx-input-encoding
[14]: #jetx-output-encoding
[21]: #jetx-syntax-strict
[22]: #jetx-syntax-safecall
[23]: #jetx-trim-leading-whitespaces
[24]: #jetx-trim-directive-whitespaces
[25]: #jetx-trim-directive-comments
[26]: #jetx-trim-directive-comments-prefix
[27]: #jetx-trim-directive-comments-suffix

全局定义(包/类/变量)
-----------------------

在模板中，如果要用到一些其他的 Class, 那么可以先 import 进来，这样就可以在模板中使用短名字，比如 `Date` 而不是 `java.util.Date`。


### **jetx.import.classes**

用来配置默认导入的 class，多个 import，用逗号分隔。

支持三种方式，如下：

1. `jetbrick.schema.app.model.UserInfo`
2. `jetbrick.schema.app.methods.*`
3. `jetbrick.schema.**`

其中 `1` 和 `2` 是和 `java` 中的 `import` 指令含义是一样的。而方式 `3` 会自动将子包下面的 Class 也一起导入进来。

`jetbrick-template` 会自动引入 `java.lang.*` 和 `java.util.*`。

> [info] 优先级：方式 `1` 优先级最高，如果出现多个 Class 的名称是一样的，那么以配置文件中定义的顺序为准。

示例如下：

```
jetx.import.classes = jetbrick.app.model.*, \
                      jetbrick.app.methods.*, \
                      jetbrick.app.support.**, \
                      jetbrick.app.model.UserInfo
```

当然，我们也可以在每个模板中用 `#option(import="xxx")` 方式引入每个模板是私有的 Class。


### jetx.import.defines

在大部分情况下，我们希望每个模板都能自动引入一些全局变量，那么我们在这里可以为这些全局变量声明变量类型，比如 `HttpServletRequest request`

允许配置多个变量定义，用逗号分隔。示例如下：

```
jetx.import.variables = HttpServletRequest request, \
                        jetbrick.dao.orm.Pagelist pagelist, \
                        List entites
```

变量的类型如果没有使用包名，那么将会自动在 `jetx.import.classes` 里面查找 Class。

> [warn] **注意**：全局定义的变量类型，如果在模板中被 `#define` 或者 `#set` 指令重新定义成其他类型，则以模板定义优先。


扩展方法/函数/标签/宏
-----------------------

`jetbrick-template` 支持如下的扩展：

* 方法扩展 Methods
* 全局函数 Functions
* 全局标签 Tags
* 全局宏 Macros

为了让模板能找到对应的方法扩展，我们需要在配置文件中对这些实现了扩展的 Class 进行注册。


### jetx.import.methods

允许配置多个 Method Class 定义，用逗号分隔。示例如下：

	jetx.import.methods = StringMethods, app.project.methods.UserAuthMethods

定义的类名会自动在 `jetx.import.classes` 里面查找 实际的Class。

`JetEngine` 默认会注册 `jetbrick.template.runtime.buildin.JetMethods`

具体参考：[默认的方法扩展 Methods](ext-method.html#default-methods)。


### jetx.import.functions

允许配置多个 Function Class 定义，用逗号分隔。示例如下：

	jetx.import.functions = app.project.methods.UserAuthFunctions

`JetEngine` 默认会注册 `jetbrick.template.runtime.buildin.JetFunctions`

具体参考：[默认的函数扩展 Functions](ext-function.html#default-functions)。


### jetx.import.tags

允许配置多个 Tag Class 定义，用逗号分隔。示例如下：

	jetx.import.tags = app.project.tags.UserTags

`JetEngine` 默认会注册 `jetbrick.template.runtime.buildin.JetTags`

具体参考：[默认的自定义标签 Tags](ext-tag.html#default-tags)。


### jetx.import.macros

允许配置多个 Macro File 定义，用逗号分隔。示例如下：

	jetx.import.macros = /macros/commons.jetx, /macros/auth.jetx


### jetx.autoscan.packages

如果我们实现了大量的扩展 methods, functions 或者 tags, 那么我们就要进行大量的配置，并且每次增加新的扩展 Class，我们就要修改配置文件，不是很方便。

为了解决这个问题，我们提供了指定扫描用户自定义的扩展 Class，扫描的内容是： 

* 方法扩展 Methods
* 全局函数 Functions
* 全局标签 Tags

我们需要在这里配置要扫描的包名，这样 `JetEngine` 就会在初始化的时候，到指定的包下面进行自动扫描(也会扫描子包中的 class)，自动将扫描到的扩展 Class 注册到 `JetEngine`。

支持定义多个扫描的包。

	jetx.autoscan.packages = app.methods, app.functions, app.tags


要让 `JetEngine` 知道某个 Class 是扩展 Class，那么就需要在这个 Class 上面加入下面某个 Annotation

* @JetAnnotations.Methods
* @JetAnnotations.Functions
* @JetAnnotations.Tags

更多详细内容请参考： [如何让自动扫描发现用户自定义的扩展方法/函数/标签 Class](faq-autoscan.html)


### jetx.autoscan.skiperrors

如果扫描到的 Class 无法存在对应的 Annotation，那么需要 load 到 JVM 进行分析，如果 load 失败，那么默认会抛出错误，如果想要忽略这种错误，那么可以配置：

```
jetx.autoscan.skiperrors = true
```


模板路径和编码格式
------------------------------------

### jetx.template.loaders

如何找到我们自己的模板文件呢？这里就是定义我们要使用的模板查找类。

我们支持下面几种模板查找类：

* jetbrick.template.loader.ClasspathResourceLoader
* jetbrick.template.loader.FileSystemResourceLoader
* jetbrick.template.loader.ServletResourceLoader

默认为 `jetbrick.template.loader.ClasspathResourceLoader`。

> [warn] **注意**：如果是 webapp 环境下，会将默认值修改为 `jetbrick.template.loader.ServletResourceLoader`


#### 从 Classpath 中加载

```
jetx.template.loaders = $loader

$loader = jetbrick.template.loader.ClasspathResourceLoader
$loader.root = /META-INF/templates/
$loader.reloadable = false
```

#### 从 FileSystem 中加载

```
jetx.template.loaders = $loader

$loader = jetbrick.template.loader.FileSystemResourceLoader
$loader.root = /opt/templates/
$loader.reloadable = true
```

#### 从 webapp 目录中加载 (需要 jetbrick-template-servlet-x.x.x.jar)

```
jetx.template.loaders = $loader

$loader = jetbrick.template.loader.ServletResourceLoader
$loader.root = /WEB-INF/templates/
$loader.reloadable = true
```

#### 从多个目录中加载

```
jetx.template.loaders = $classpathLoader, $webLoader

$classpathLoader = jetbrick.template.loader.ClasspathResourceLoader
$classpathLoader.root = /META-INF/templates/
$classpathLoader.reloadable = false

$webLoader = jetbrick.template.loader.ServletResourceLoader
$webLoader.root = /WEB-INF/templates/
$webLoader.reloadable = true
```


### jetx.template.suffix

默认的模板文件扩展名 `.jetx`，主要用于 Web 框架集成中，用于查找和过滤模板用。


### jetx.input.encoding

模板源文件的编码格式，默认为 `utf-8`。


### jetx.output.encoding

模板输出内容的编码格式，默认为 `utf-8`。

> [info] **最佳实践**：一般在 webapp 中，`jetx.output.encoding` 应该和 html 页面的 `contentType` 中的编码，以及 `response` 的 `characterEncoding` 完全保持一致。


语法选项
------------------------

### jetx.syntax.strict

为了让每个模板中使用到的变量都有严格的类型定义，同时增加代码的可读性，我们可以将模板语法设置为 `strict` 模式。

在 `strict` 模式中，每个模板中使用的变量都必须使用 `#define` 或者 `#set` 进行变量类型声明 (或者使用 `jetx.import.defines` 定义的全局变量类型声明)

默认为 `false`，即允许可以不是有 `#define` 进行变量声明定义。


> [info] **最佳实践**：从团队开发管理的角度来说，我们推荐使用 `strict` 模式，这样有利于代码的维护和沟通。


### jetx.syntax.safecall

`jetbrick-template` 支持下面的安全调用（类似于 Groovy），以避免出现 `NullPointerException`

* 属性调用 `bean.property`
* 方法调用 `bean.method(...)`
* 数组/List访问 `array[index]`
* Map访问 `map[key]` 

默认为 `false`，不启用。



模板格式
------------

### jetx.trim.leading.whitespaces

类似于 JSP 的 `<%@ page trimDirectiveWhitespaces="true" %>`, 可以让模板的开始位置处跳过不必要的空白内容。

默认：`false`，不启用


### jetx.trim.directive.whitespaces

由于指令之间存在很多的空白内容，而空白内容也会被作为原始文本原封不动的输出，这样会造成很多输出的内容参差不齐。这个就是用来优化输出格式的，对于用模板来进行代码生成时候特别有用。不建议关闭。

模板示例：

```
#for (int n: [1,2,3])
${n}
#end
```

禁用后效果：`false`

```

1

2

3

```

启用后的效果：`true` (默认启用)

```
1
2
3
```


注释指令
-------------------------

由于目前的指令一般直接嵌入在 HTML，对于一些使用可视化编辑器的用户来说，可能会造成一些干扰。
模板增加对指令注释支持，如：`<!-- #if (...) -->`; 增强对可视化编辑器的友好度。


### jetx.trim.directive.comments

是否开启对注释指令的支持，默认为 `false`，表示不启用。


### jetx.trim.directive.comments.prefix

设置注释开始格式，默认为 `<!--`


### jetx.trim.directive.comments.suffix

设置注释开始格式，默认为 `-->`

> [warn] **注意**： 如果开启注释指令的支持，系统并没有强制要求 `jetx.trim.directive.comments.prefix` 和 `jetx.trim.directive.comments.suffix` 必须配对出现。也就是说如果使用 `<!-- #end` 也是可以的。当然我们还是建议你配对使用。

范例：

```html
<table>
<!-- #for (User user: userlist) -->
  <tr>
	<td>${user.name}</td>
	<td>${user.email}</td>
  </tr>
<!-- #end -->
</table>
```

