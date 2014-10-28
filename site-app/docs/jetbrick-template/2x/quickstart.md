从这里开始 Start
===================

本章节将快速的带领你领略一下 `jetbrick-template` 的工作方式。


> [info] **推荐**：模板文件扩展名为 `.jetx`。


首先，`jetbrick-template` 有 2 种工作模式：

* [普通的 Application 应用](#-application-)
* [Webapp 应用](#-webapp-)

2 种模式的区别在于：在 webapp 模式中，我们针对 web 应用，提供的大量的 Web 专用的内置变量(如 request 对象)，以及一些常用的 web 扩展。


普通的 Application 环境
---------------------------------------

1. 新建一个 Maven 工程，并加入下面的 `jetbrick-template` 依赖

    ```xml
    <dependency>
        <groupId>com.github.subchen</groupId>
        <artifactId>jetbrick-template-core</artifactId>
        <version>{{TEMPLATE.VERSION}}</version>
    </dependency>
    ```

2. 新建一个 User.java 作为 Model

    ```java
    public class User {
        private String name;
        private String email;
    
        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
    }
```

2. 新建一个 users.jetx 模板，并放在默认的 classpath 根目录 (`src/main/resources`)

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

3. 新建一个 JUnit Test 测试类 JetxTest.java

    ```java
    public class JetxTest {
    
        @Test
        public void test() {
            // 0. 准备一些 Model 数据作为测试
            List<User> users = Arrays.asList(
                new User("张三", "zhangsan@qq.com"),
                new User("李四", "lisi@qq.com"),
                new User("王五", "wangwu@qq.com"),
            );
    
            // 1. 创建一个默认的 JetEngine
            JetEngine engine = JetEngine.create();
    
            // 2. 获取一个模板对象 (从默认的 classpath 下面)
            JetTemplate template = engine.getTemplate("/users.jetx");
    
            // 3. 创建 context 对象
            Map<String, Object> context = new HashMap<String, Object>();
            context.put("users", users);
    
            // 4. 渲染模板到自定义的 Writer
            StringWriter writer = new StringWriter();
            template.render(context, writer);
    
            // 5. 打印结果
            System.out.println(writer.toString());
        }
    }
    ```

5. 运行 JUnit 测试，得到的模板结果如下：

    ```html
    <table>
      <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>邮箱</td>
      </tr>
      <tr>
        <td>1</td>
        <td>张三</td>
        <td>zhangsan@qq.com</td>
      </tr>
      <tr>
        <td>2</td>
        <td>李四</td>
        <td>lisi@qq.com</td>
      </tr>
      <tr>
        <td>3</td>
        <td>王五</td>
        <td>wangwu@qq.com</td>
      </tr>
    </table>
    ```

完整的 demo 请参考： https://github.com/subchen/jetbrick-template-samples/tree/master/quickstart-app/


Webapp 环境
---------------------------------------


1. 新建一个 Maven Webapp 工程，并加入下面的 `jetbrick-template` 依赖

    ```xml
    <dependency>
        <groupId>com.github.subchen</groupId>
        <artifactId>jetbrick-template-servlet</artifactId>
        <version>{{TEMPLATE.VERSION}}</version>
    </dependency>
    ```

2. 配置 web.xml

    ```xml
    <servlet>
      <servlet-name>jetbrick-template</servlet-name>
      <servlet-class>jetbrick.template.web.servlet.JetTemplateServlet</servlet-class>
      <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
      <servlet-name>jetbrick-template</servlet-name>
      <url-pattern>*.jetx</url-pattern>
    </servlet-mapping>
    ```

3. 新建一个 request.jetx 模板，并放在默认的 webroot 根目录 (`src/main/webapp`)

    ```html
    <div>request headers:</div>
    <ul>
        #for(String name: request.headerNames)
        <li>${name}: ${request.getHeader(name)}</li>
        #end
    </ul>
    ```

4. 部署你的 webapp，并打开浏览器访问 http://127.0.0.1:8080/request.jetx，将会看到下面类似的结果：

    ```html
    request headers:
    * Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
    * Accept-Encoding:gzip,deflate,sdch
    * Accept-Language:en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2
    * Cache-Control:max-age=0
    * Connection:keep-alive
    * Host:127.0.0.1
    * Referer:http://127.0.0.1:8080/request.jetx
    * User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36
    ```

完整的 demo 请参考： https://github.com/subchen/jetbrick-template-samples/tree/master/quickstart-webapp/

