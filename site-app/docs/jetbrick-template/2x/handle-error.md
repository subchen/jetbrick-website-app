错误处理 Finding Issue
=========================

`jetbrick-template` 提供了强大的错误定位功能，你再也不用担心找不到错误原因了。


语法错误 Syntax Error
-----------------------

错误的模板示例：

```html
#for (user: userlist)
<tr>
    <td>${for.index}</td>
    <td>${user.name}</td>
    <td>${user.vip ? "vip", "normal"}</td>
</tr>
#end
```

错误提示包括了：错误所在的行号和列号，错误模板路径，错误原因等

```
jetbrick.template.parser.SyntaxException: mismatched input ',' expecting {'[', '?', ':', '.', '===', '!==', '==', '!=', '>', '<', '>=', '<=', '&&', '||', '+', '-', '*', '/', '%', '&', '|', '^', '<<', '>>', '>>>', 'instanceof'}

template: /samples/syntax-error-test.jetx: 5,28
-------------------------------------------------------------------------------
   3:     <td>${for.index}</td>
   4:     <td>${user.name}</td>
   5:     <td>${user.vip ? "vip", "normal"}</td>
                                ^ -- here
   6: </tr>
   7: #end
-------------------------------------------------------------------------------

	at jetbrick.template.parser.AstBuilder$AstErrorListener.syntaxError(AstBuilder.java:77)
	at org.antlr.v4.runtime.ProxyErrorListener.syntaxError(ProxyErrorListener.java:67)
	at org.antlr.v4.runtime.Parser.notifyErrorListeners(Parser.java:561)
	at org.antlr.v4.runtime.DefaultErrorStrategy.reportInputMismatch(DefaultErrorStrategy.java:327)
	at org.antlr.v4.runtime.DefaultErrorStrategy.reportError(DefaultErrorStrategy.java:150)
	at jetbrick.template.runtime.parser.grammer.JetTemplateParser.expression(JetTemplateParser.java:2309)
	at jetbrick.template.runtime.parser.grammer.JetTemplateParser.expression(JetTemplateParser.java:2235)
	at jetbrick.template.runtime.parser.grammer.JetTemplateParser.value(JetTemplateParser.java:313)
	at jetbrick.template.runtime.parser.grammer.JetTemplateParser.block(JetTemplateParser.java:188)
	at jetbrick.template.runtime.parser.grammer.JetTemplateParser.directive_for(JetTemplateParser.java:1027)
	at jetbrick.template.runtime.parser.grammer.JetTemplateParser.directive(JetTemplateParser.java:427)
	at jetbrick.template.runtime.parser.grammer.JetTemplateParser.block(JetTemplateParser.java:219)
	at jetbrick.template.runtime.parser.grammer.JetTemplateParser.template(JetTemplateParser.java:117)
	at jetbrick.template.parser.AstBuilder.create(AstBuilder.java:49)
	at jetbrick.template.JetTemplateImpl.rebuildAstNodeAndConfig(JetTemplateImpl.java:111)
	at jetbrick.template.JetTemplateImpl.reload(JetTemplateImpl.java:82)
	at jetbrick.template.JetEngineImpl.getTemplate(JetEngineImpl.java:91)

```


运行期错误 Runtime Error
---------------------------

如果在模板运行期间发生错误，那么就可以得到类似下面的错误 Java Exception Stack。


模板示例：

```html
#for (user: userlist)
<tr>
    <td>${for.index}</td>
    <td>${user.name}</td>
    <td>${user.vip ? "vip" : "normal"}</td>
</tr>
#end
```

错误提示包括了：错误所在的行号和列号，错误模板路径，错误原因等

```
jetbrick.template.runtime.InterpretException: property get error: vip

template: /samples/runtime-error-test.jetx: 5, 16
-------------------------------------------------------------------------------
   3:     <td>${for.index}</td>
   4:     <td>${user.name}</td>
   5:     <td>${user.vip ? "vip" : "normal"}</td>
                    ^ -- here
   6: </tr>
   7: #end
-------------------------------------------------------------------------------

	at jetbrick.template.parser.ast.AstInvokeField.doInvokeGetter(AstInvokeField.java:76)
	at jetbrick.template.parser.ast.AstInvokeField.execute(AstInvokeField.java:51)
	at jetbrick.template.parser.ast.AstValue.execute(AstValue.java:36)
	at jetbrick.template.parser.ast.AstStatementList.execute(AstStatementList.java:182)
	at jetbrick.template.parser.ast.AstDirectiveFor.execute(AstDirectiveFor.java:59)
	at jetbrick.template.parser.ast.AstStatementList.execute(AstStatementList.java:182)
	at jetbrick.template.parser.ast.AstTemplate.execute(AstTemplate.java:37)
	at jetbrick.template.JetTemplateImpl.doInterpret(JetTemplateImpl.java:154)
	at jetbrick.template.JetTemplateImpl.render(JetTemplateImpl.java:139)
Caused by: java.lang.RuntimeException: java.lang.UnsupportedOperationException
	at jetbrick.util.ExceptionUtils.unchecked(ExceptionUtils.java:33)
	at jetbrick.util.ExceptionUtils.unchecked(ExceptionUtils.java:31)
	at jetbrick.bean.MethodInfo.invoke(MethodInfo.java:194)
	at jetbrick.bean.PropertyInfo.get(PropertyInfo.java:111)
	at jetbrick.template.parser.ast.AstInvokeField.doInvokeGetter(AstInvokeField.java:70)
	... 10 more
Caused by: java.lang.UnsupportedOperationException
	at jetbrick.demo.Model.getVip(Model.java:38)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:483)
	at jetbrick.bean.MethodInfo.invoke(MethodInfo.java:192)
	... 12 more
```

根据 `root cause`，我们可以看到是 `user.vip` 这个属性调用，抛出了 `UnsupportedOperationException`。

