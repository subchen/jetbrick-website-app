嵌入子模板 #include
===================================================

在 `jetbrick-template` 中，我们经常需要在一个模板中嵌入另一个模板。这时候，我们可以使用下面的指令：

```
#include(file, [parameters], [returnName])
```

常规的做法
---------------------

* include 静态文件名

    ```
    #include("/include/header.jetx")
    ```

* include 动态文件名

    ```
    #define(String url)
    #include(url)
    ```


父模板传递参数给子模板
---------------------------------

* **隐式传递**

    使用 `#include` 指令嵌入子模板，默认会把当前模板(父模板)的所有 context 数据传递给子模板。也就是说，子模板能获取所有父模板的变量。
    
    比如：
    
    父模板：/parent.jetx
    
    ```
    #set(String name = "JAVA")
    #include("/sub.jetx")
    ```

    子模板：/sub.jetx

    ```
    ${name}
    ```

    结果：
    
    ```
    JAVA
    ```

* **显示传递**

    除了隐式传递，我们还能通过 `#include` 的第二个参数(Map 对象)，显示传递更多的参数。
    
    比如：
    
    父模板：/parent.jetx
    
    ```
    #include("/sub.jetx", {name: "JAVA"})
    ```

    子模板：/sub.jetx

    ```
    ${name}
    ```

    结果：
    
    ```
    JAVA
    ```


子模板返回对象给父模板
---------------------------------

除了父模板可以传递参数给子模板，子模板也能返回对象给父模板。

这里就需要使用 `#return` 指令。

看例子：

父模板：/parent.jetx

```
#define(Object RESULT)

#include("/sub.jetx", {}, "RESULT")
${RESULT}
```

子模板：/sub.jetx

```
This is a sub template.
#return( [2,4,6,8] )
```

结果：

```
[2,4,6,8]
```

> [info] **提示**：
> 
> * `#return` 指令可以返回任意对象，默认存储在父模板 context 中，变量名由 `#include` 指令的第三个参数 `returnName` 所指定。
> * `#return` 指令后面的语句不会被执行，所以应该把 `#return` 放在子模板的最后面
> * 如果 `#include` 第三个参数 `returnName` 没有指定，那么默认为 `RESULT`。

