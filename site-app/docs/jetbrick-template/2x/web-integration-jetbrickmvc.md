Jetbrick webmvc
=====================

作为 `jetbrick` 系列的一员，`jetbrick-template` 和 `jetbrick-webmvc` 可以非常好的集成在一起。


Maven 依赖
------------------

```xml
<dependency>
    <groupId>com.github.subchen</groupId>
    <artifactId>jetbrick-template-jetbrickmvc</artifactId>
    <version>{{TEMPLATE-VERSION}}</version>
</dependency>
```

web.xml 配置
----------------------------

```xml
<context-param>
    <param-name>jetbrick-template-config-location</param-name>
    <param-value>/WEB-INF/jetbrick-template.properties</param-value>
</context-param>

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
```


Controller 例子
-------------------------------------------

```java
@Controller
public class UsersController {

    @Action("/users")
    public void users(Model model) {
        model.put("userlist", DaoUtils.getUserList());
        return "users.jetx";
    }
}
```

模板例子 users.jetx
--------------------------------

```html
<table border="1" width="600">
  <tr>
    <td>ID</td>
    <td>姓名</td>
    <td>邮箱</td>
    <td>书籍</td>
  </tr>
  #for(UserInfo user: userlist)
  <tr>
    <td>${user.id}</td>
    <td>${user.name}</td>
    <td>${user.email}</td>
    <td><a href="books?author=${user.id}">书籍列表</a></td>
  </tr>
  #end
</table>
```


范例源码
--------------------------------

具体例子代码参考： https://github.com/subchen/jetbrick-webmvc-samples/
