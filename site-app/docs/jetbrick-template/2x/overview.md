概述 Overview
==================

`jetbrick-template` 是一个新一代 Java 模板引擎，具有高性能和高扩展性。 适合于动态 HTML 页面输出或者代码生成，可替代 `JSP` 页面或者 `Velocity` 等模板。 指令和 `Velocity` 相似，表达式和 `Java` 保持一致，易学易用。

* 支持类似于 Velocity 的多种指令
* 支持模板热加载
* 支持强类型/弱类型切换
* 支持静态方法/字段
* 支持可变参数方法调用
* 支持方法重载
* 支持扩展方法
* 支持扩展函数
* 支持自定义标签 #tag
* 支持宏定义 #macro
* 支持布局 layout
* 支持安全管理器


简单易用的指令
=============================

`jetbrick-template` 指令集和老牌的模板引擎 `Velocity` 非常相似，易学易用。

```html
#define(List users)
<table>
  <tr>
    <td>序号</td>
    <td>姓名</td>
    <td>邮箱</td>
  </tr>
  #for (User user : users)
  <tr>
    <td>${for.index}</td>
    <td>${user.name}</td>
    <td>${user.email}</td>
  </tr>
  #end
</table>
```

详细指令语法，请参考：[语法指南](syntax-core.html)。或者[和 Velocity 的比较](velocity-comparison.html)。


易于集成 Web Integration
=============================

`jetbrick-template` 可以和当前流行的 Web mvc framework 进行集成。

* [HttpServlet](web-integration-servlet.html)
* [Filter](web-integration-filter.html)
* [Jetbrick webmvc](web-integration-jetbrickmvc.html)
* [Springmvc](web-integration-springmvc.html)
* [JFinal](web-integration-jfinal.html)
* [Jodd 3.5.1+](web-integration-jodd.html)
* [Struts 2.x](web-integration-struts.html)
* [Nutz](web-integration-nutz.html)

具体 Web 集成方法，请参考： [Web 框架集成](web-integration.html)


友好的错误提示
=============================

`jetbrick-template` 具有详细的模板解析和运行时错误提示，出错提示可以精确定位到原始模板所在的行和列。

```
jetbrick.template.runtime.InterpretException: cannot resolve property: jetbrick.template.test.Model#coding

template: /jetbrick/template/test/template.jetx: 51,18
-------------------------------------------------------------------------------
  49:         <td>${for.index}</td>
  50:         <td>${item.id}</td>
  51:         <td>${item.coding}</td>
                        ^ -- here
  52:         <td style="text-align: left;">${item.name}</td>
  53:         <td>${item.price}</td>
-------------------------------------------------------------------------------

	at jetbrick.template.parser.ast.AstInvokeField.doInvokeGetter(AstInvokeField.java:63)
	at jetbrick.template.parser.ast.AstInvokeField.execute(AstInvokeField.java:51)
	at jetbrick.template.parser.ast.AstValue.execute(AstValue.java:36)
	at jetbrick.template.parser.ast.AstStatementList.execute(AstStatementList.java:182)
	at jetbrick.template.parser.ast.AstDirectiveFor.execute(AstDirectiveFor.java:59)
	at jetbrick.template.parser.ast.AstStatementList.execute(AstStatementList.java:182)
	at jetbrick.template.parser.ast.AstTemplate.execute(AstTemplate.java:37)
	at jetbrick.template.JetTemplateImpl.doInterpret(JetTemplateImpl.java:153)
	at jetbrick.template.JetTemplateImpl.render(JetTemplateImpl.java:138)
```

* 出错模板：/jetbrick/template/test/template.jetx
* 出错位置：line 51, column 18
* 错误原因：cannot resolve property: jetbrick.template.test.Model#coding

点击这里查看：[模板错误提示](handle-error.html)


