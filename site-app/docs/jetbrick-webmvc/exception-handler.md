全局异常处理器 Exception Handler
===================================

jetbrick 提供了一个全局的 Exception Handler 来处理所有的 Action/Result/View/Interpector 相关的错误。

要实现全局的异常处理器，主要实现 `ExceptionHandler` 接口即可。

新建一个 GlobalExceptionHandler.java 文件
------------------------------------------------

```java
package jetbrick.docs.samples;

import jetbrick.web.mvc.ExceptionHandler;

public class GlobalExceptionHandler implements ExceptionHandler {

  @Override
  public void handleError(RequestContext ctx, Throwable e) throws Throwable {
    if (ServletUtils.isAjaxRequest(ctx.getRequest()) {
      JSONObject json = new JSONObject();
      json.put("succ", false);
      json.put("message", e.getMessage());
      ctx.handleResult(JSONAware.class, json);
    } else {
      if (e instanceof WebNotFoundException) {
        ctx.handleResult("/error/404.jsp");
      } else if (e instanceof WebForbiddenException) {
        ctx.handleResult("/error/403.jsp");
      } else {
        ctx.handleResult("/error/500.jsp");
      }
    }
  }
}
```


配置 jetbrick-webmvc.properties
------------------------------------------------

```
web.exception.handler = jetbrick.docs.samples.GlobalExceptionHandler
```

新建 /error/500.jsp 等页面
------------------------------------------------

```jsp
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
    Throwable e = (Throwable) request.getAttribute(jetbrick.web.mvc.ExceptionHandler.KEY_IN_REQUEST);
    
    String message = "";
    java.io.ByteArrayOutputStream ostr = new java.io.ByteArrayOutputStream();
    
    if (e != null) {
        message = e.getMessage();
        e.printStackTrace(new java.io.PrintStream(ostr));
    }

    // dump request info and exception into log file
    // send mail to admin
    // ...
%>

<pre><xmp><%=ostr%></xmp></pre>
```

> [info] **经验**：
> 
> 我们可以通过全局的异常处理器来处理一些服务端的未知异常，并保持现场出错信息到日志文件中，以方便分析，同时对于线上系统能及时的发送错误信息邮件给相关的管理员。
> 
> 我们也可以在这里处理一些用户鉴权相关的异常，以及时通知用户未授权错误。


