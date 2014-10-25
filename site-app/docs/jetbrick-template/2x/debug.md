如何调试模板 debug？
==============================


使用 `debug(format, args...)` 函数
--------------------------------------

模板范例：

```
${debug("id = {}, users.size = {}.", id, users.size()}
```

控制台日志输出：

```
2014-10-06 12:07:29 [INFO] jetx.debug - id = 129, users.size = 20.
```

> [info] 注意：
> 
> * 要使用 debug 函数，需要 Slf4j 配合。
> * 具体的 format 参数格式请查看 [Slf4j Logger](http://www.slf4j.org/apidocs/org/slf4j/Logger.html)。


开启 debug 日志 (默认应该已经开启，如果没有开启，那么按照下面的方法开启)：

* Log4j 1.x
    
    ```
    log4j.logger.jetx.debug = INFO
    ```

* Logback

    ```xml
    <logger name="jetx.debug" level="INFO" />
    ```


