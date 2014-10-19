概述 Overview
==================

jetbrick-template 是一个新一代 Java 模板引擎，具有高性能和高扩展性。 适合于动态 HTML 页面输出或者代码生成，可替代 JSP 页面或者 Velocity 等模板。 指令和 Velocity 相似，表达式和 Java 保持一致，易学易用。

* 支持类似于 Velocity 的多种指令
* 支持热加载
* 支持类型推导
* 支持可变参数方法调用
* 支持方法重载
* 支持方法扩展
* 支持函数扩展
* 支持自定义标签 #tag
* 支持宏定义 #macro
* 支持布局 Layout

简单易用的指令
=============================

jetbrick-template 指令集和老牌的模板引擎 Velocity 非常相似，易学易用。

```html
#define(List userlist)
<table>
  <tr>
    <td>序号</td>
    <td>姓名</td>
    <td>邮箱</td>
  </tr>
  #for (UserInfo user : userlist)
  <tr>
    <td>${for.index}</td>
    <td>${user.name}</td>
    <td>${user.email}</td>
  </tr>
  #end
</table>
```

详细指令语法，请参考：[语法指南](syntax.html)。或者[和 Velocity 的比较](syntax.html#velocity)。


易于集成 Integrate
=============================

可以和市面上常见的 Web MVC framework 进行集成。

* [HttpServlet](integrate.html#HttpServlet)
* [Filter](integrate.html#Filter)
* [Jetbrick MVC](integrate.html#JetbrickMVC)
* [Spring MVC](integrate.html#SpringMVC)
* [JFinal](integrate.html#JFinal)
* [Jodd](integrate.html#Jodd)
* [Struts 2.x](integrate.html#Struts)
* [Nutz](integrate.html#Nutz)

具体集成方法，请参考： [Web 框架集成](integrate.html)


友好的错误提示
=============================

具有详细的模板解析和运行时错误提示，出错提示可以定位到原始模板所在的行号。

```
Exception in thread "main" jetbrick.template.runtime.InterpretException: cannot resolve property: jetbrick.template.test.Model#coding

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
	at jetbrick.template.test.ErrorTest.eval(ErrorTest.java:54)
	at jetbrick.template.test.ErrorTest.main(ErrorTest.java:25)
```

* 出错模板：/jetbrick/template/test/template.jetx
* 出错位置：line 51, column 18
* 错误原因：cannot resolve property: jetbrick.template.test.Model#coding

点击这里查看：[jetbrick-template 常见错误分析](faq-error.html)
