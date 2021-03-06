快速入门 Quick Start
======================================

jetbrick 推荐使用 Eclipse IDE for Java EE Developers 做为开发环境。最新版下载地址：http://www.eclipse.org/downloads/

下面，我们以一个用户登录的例子来简单的学习一下如何使用 jetbrick 来进行快速开发。

创建 Dynamic Web Project
-------------------------------

请用你的 Eclipse IDE 根据下面的向导，快速新建一个动态 Web 项目。

### 新建 Web 工程

![新建 Web 工程](images/new_web_project.png)

### 填写项目信息

![项目信息](images/new_web_project_info.png)

推荐使用 Servlet API 3.0 以上的标准。

### 设置 Build Path

![BuildPath](images/new_web_project_path.png)

推荐使用例子中的 source 文件结构 (Maven 标准) ，以及使用对应的 Output folder 路径。

### 设置 Web Module

![WebModule](images/new_web_project_root.png)

### 新建项目信息汇总

根据上面新建的 Web 项目，将会具有如下的目录结构：

* /src/main/java/
* /src/main/resources/
* /src/main/webapp/WEB-INF/web.xml
* /src/main/webapp/WEB-INF/lib/
* /src/main/webapp/WEB-INF/classes/
* /src/test/java/
* /src/test/resources/

我们假设你的 Java 项目的包都在 `jetbrick.docs.samples` 下面，然后部署在 `http://127.0.0.1:8080/jetbrick_docs_samples/` 下面。


增加 jetbrick 依赖包
----------------------------

**Maven 用户**

使用如下 Maven 坐标信息

```xml
<dependency>
  <groupId>com.github.subchen</groupId>
  <artifactId>jetbrick-all</artifactId>
  <version>{{ALL-VERSION}}</version>
</dependency>
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-simple</artifactId>
  <version>1.7.7</version>
</dependency>
<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>fastjson</artifactId>
  <version>1.1.38</version>
</dependency>
```

**普通用户**

将如下的 jars 复制到工程的 /src/main/webapp/WEB-INF/lib/ 目录下面，然后加入到 Build Path 中去。

* [jetbrick-all-{{ALL-VERSION}}.jar](http://search.maven.org/remotecontent?filepath=com/github/subchen/jetbrick-all/{{ALL-VERSION}}/jetbrick-all-{{ALL-VERSION}}.jar)
* [slf4j-api-1.7.7.jar](http://search.maven.org/remotecontent?filepath=org/slf4j/slf4j-api/1.7.7/slf4j-api-1.7.7.jar)
* [slf4j-simple-1.7.7.jar](http://search.maven.org/remotecontent?filepath=org/slf4j/slf4j-simple/1.7.7/slf4j-simple-1.7.7.jar) (可选包)
* [fastjson-1.1.38.jar](http://search.maven.org/remotecontent?filepath=com/alibaba/fastjson/1.1.38/fastjson-1.1.38.jar) (可选包)

对于 jetbrick 来说，我们只强依赖于 `slf4j-api`，其他的第三方依赖都是可选的。


配置 web.xml
-------------------------

```xml
<?xml version="1.0" encoding="UTF-8"?>

<web-app xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
                             http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0">

    <filter>
        <filter-name>jetbrick-webmvc</filter-name>
        <filter-class>jetbrick.web.mvc.DispatcherFilter</filter-class>
        <init-param>
            <param-name>configLocation</param-name>
            <param-value>/WEB-INF/jetbrick-webmvc.properties</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>jetbrick-webmvc</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

</web-app>
```

配置 /WEB-INF/jetbrick-webmvc.properties
------------------------------------------------

```
web.development = true
web.http.encoding = utf-8
web.scan.packages = jetbrick.docs.samples
web.urls.bypass = jetbrick.web.mvc.router.PrefixSuffixBypassRequestUrls
web.urls.router = jetbrick.web.mvc.router.RestfulRouter
web.view.default = jsp
```

输入你的代码
-------------------------

### 创建一个 Controller


```java
package jetbrick.docs.samples.controllers;

import javax.servlet.http.HttpSession;
import jetbrick.web.mvc.action.*;
import jetbrick.web.mvc.action.annotations.RequestForm;
import jetbrick.web.mvc.ioc.annotations.Inject;
import com.alibaba.fastjson.JSONAware;

@Controller
public class PassportController {
    @Inject
    private PassportService passportService;

    @Action(value = "/passport/login", method = HttpMethod.GET)
    public String login() {
        return "login.jsp";
    }

    @Action(value = "/passport/login", method = HttpMethod.POST)
    public JSONAware login(
            @RequestParam String username,
            @RequestParam String password
    ) {
        boolean pass = passportService.validate(username, password);
        if (pass) {
            return JSON.ok();
        } else {
            return JSON.fail("Username or password is invalid.");
        }
    }

    @Action("/passport/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/passport/login";
    }
}
```

### 创建一个 PassportService

PassportService 用来提供用户名密码认证服务。范例代码中，使用了固定的用户名和密码，实际代码应该是从数据库中进行验证。

```java
package jetbrick.docs.samples.services;

@IocBean
public class PassportService {

    public boolean validate(String username, String password) {
        return "admin".equals(username) && "admin".equals(password);
    }
}
```

### 创建登录页面 /src/main/webapp/passport/login.jsp

```html
<!DOCTYPE html>
<html>
<head><meta charset="utf-8"></head>
<body>
<form action="./login" method="post">
    Username: <br/>
    <input type="text" name="username" size="30" /><br/>
    Password: <br/>
    <input type="password" name="password" size="30" /><br/>
    <input type="submit" value="Login" />
</form>
</body>
</html>
```

放在 Tomcat 里面运行
----------------------------

好了，尝试将你的 Web 项目部署到 Tomcat 中去吧，然后运行。

### 打开 Servers View

![打开 Servers View](images/new_server_0.png)

### 新建一个 Tomcat Server

![新建一个 Tomcat Server](images/new_server_1.png)

### 选择本地 Tomcat 服务器安装路径

![选择本地 Tomcat 服务器安装路径](images/new_server_2.png)

### 选择要部署的 Web 项目

![选择要部署的 Web 项目](images/new_server_3.png)

### 启动 Tomcat

![启动 Tomcat](images/new_server_4.png)

### 打开你的浏览器

输入：http://127.0.0.1:8080/jetbrick_docs_samples/passport/login


完整 demo 下载
--------------------------

你可以在 http://subchen.github.io/jetbrick/download.html 中下载最新的 demo 实例代码。


