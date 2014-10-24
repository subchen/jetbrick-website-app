Jodd 3.5.1+ 集成
================================

注意，由于 Jodd 3.5 更改了 Result 的集成接口，所以 `jetbrick-template` 从 `1.2.5` 版本开始，只支持 `Jodd 3.5.1+`。

如果需要 `Jodd 3.5` 之前的版本，请使用 `jetbrick-template 1.2.4`。 


Maven 依赖
------------------

```xml
<dependency>
    <groupId>com.github.subchen</groupId>
    <artifactId>jetbrick-template-jodd</artifactId>
    <version>{{TEMPLATE.VERSION}}</version>
</dependency>
```

web.xml 配置
----------------------------------------------

```xml
<context-param>
    <param-name>jetbrick-template-config-location</param-name>
    <param-value>/WEB-INF/jetbrick-template.properties</param-value>
</context-param>
```


修改 Jodd 的配置文件：madvoc.props
-------------------------------------------

```
[jetbrick.template.web.jodd.JetTemplateResult]
contentType=text/html; charset=UTF-8

[automagicMadvocConfigurator]
includedEntries=jetbrick.template.web.jodd.*
```

Action 例子
-------------------------------------------

```java
@MadvocAction
public class UsersAction {
    @Out
    Collection<UserInfo> userlist;

    @Action(extension = Action.NONE)
    public Object view() {
        userlist = DaoUtils.getUserList();
        return "jetx:/users.jetx";
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

具体例子代码参考： https://github.com/subchen/jetbrick-template-webmvc-samples/
