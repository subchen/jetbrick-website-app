和 Velocity 的比较
===================

`jetbrick-template` 的指令和老牌的模板引擎 `Velocity` 是非常相似的。


语法差异
----------

* jetbrick-template 指令中的变量不加 `$` 符，只支持 `#if(x == y)`，不支持 `#if($x == $y)`，因为指令中没有加引号的字符串就是变量，和 Java 语法一样，没有加 `$` 的必要。
* jetbrick-template 占位符必需加大括号，只支持 `${foo}`，不支持 `$foo`，因为 `$` 在 JavaScript 中也是合法变量名符号，而 `${}` 不是，避免混淆。
* jetbrick-template 占位符当变量为 `null` 时输出空白串，而不像 Velocity 那样原样输出指令原文，即 `${foo}`，等价于 Velocity 的 `$!{foo}`，以免表达式源码泄漏。
* jetbrick-template 支持在所有使用变量的地方，进行表达式计算，也就是你不需要像 Velocity 那样，先 `#set($j = $i + 1)` 到一个临时变量，再输出临时变量 `${j}`，jetbrick-template 可以直接输出 `${i + 1}`，其它指令也一样，比如：`#if(i + 1 == n)`。
* jetbrick-template 采用扩展 Class 原生方法的方式，如：`${"str".toChar()}`，而不像 Velocity 的 Tool 工具方法那样：`$(StringTool.toChar("a"))`，这样的调用方式更直观，更符合代码书写习惯。
* jetbrick-template 支持属性和方法的安全调用 (`safecall=true`)。如 `${user.name}`，`${user.hasRole("vip")}`。如果 `user` 对象为 `null`，那么返回结果就是 `null`，不会出现烦人的 `NullPointerException`。
* jetbrick-template 还支持静态字段/方法调用，函数扩展，自定义标签等等。

指令差异
------------

<table>
    <tr>
        <th width="140px">velocity</th>
        <th width="140px">jetbrick-template</th>
        <th width="40px">异同</th>
        <th width="100px">功能</th>
        <th>变化</th>
    </tr>
    <tr>
        <td>${foo.bar}<br>$foo.bar</td>
        <td>${foo.bar}</td>
        <td><font color="green">相同</font></td>
        <td>输出占位符</td>
        <td>jetbrick-template 大括号必需</td>
    </tr>
    <tr>
        <td>$!{foo.bar}<br>$!foo.bar</td>
        <td>$!{foo.bar}</td>
        <td><font color="red">不同</font></td>
        <td>空值不显示源码</td>
        <td>velocity 为空值不显示源码<br>jetbrick-template 改为HTML过滤输出</td>
    </tr>
    <tr>
        <td>## ...<br>#* ... *#</td>
        <td>## ...<br>#// ...<br>#-- ... --#</td>
        <td>相似</td>
        <td>注释</td>
        <td>块注释格式不一样</td>
    </tr>
    <tr>
        <td>#[[ ... ]]#</td>
        <td>#[[ ... ]]#</td>
        <td><font color="green">相同</font></td>
        <td>不解析文本块</td>
        <td></td>
    </tr>
    <tr>
        <td>\# \$ \\</td>
        <td>\# \$ \\</td>
        <td><font color="green">相同</font></td>
        <td>特殊字符转义</td>
        <td></td>
    </tr>
    <tr>
        <td>n/a</td>
        <td>#define(Type foo = bar)</td>
        <td><font color="red">新增</font></td>
        <td>给变量声明类型</td>
        <td></td>
    </tr>
    <tr>
        <td>#set($foo = $bar)</td>
        <td>#set([Type] foo = bar)</td>
        <td><font color="green">相同</font></td>
        <td>给变量赋值</td>
        <td>可带类型声明</td>
    </tr>
    <tr>
        <td>n/a</td>
        <td>#return ( obj )</td>
        <td><font color="red">新增</font></td>
        <td>返回子模板变量给父模板</td>
        <td></td>
    </tr>
    <tr>
        <td>#if($foo == $bar)</td>
        <td>#if(foo == bar)</td>
        <td><font color="green">相同</font></td>
        <td>条件判断</td>
        <td></td>
    </tr>
    <tr>
        <td>#elseif($foo == $bar)</td>
        <td>#elseif(foo == bar)</td>
        <td><font color="green">相同</font></td>
        <td>否定条件判断</td>
        <td></td>
    </tr>
    <tr>
        <td>#else</td>
        <td>#else</td>
        <td><font color="green">相同</font></td>
        <td>否定判断</td>
        <td></td>
    </tr>
    <tr>
        <td>#end</td>
        <td>#end</td>
        <td><font color="green">相同</font></td>
        <td>结束指令</td>
        <td></td>
    </tr>
    <tr>
        <td>#foreach($item in $list)</td>
        <td>#for(item : list)<br>#for(Type item : list)</td>
        <td>相似</td>
        <td>循环指令</td>
        <td>改为Java格式，可以带类型声明</td>
    </tr>
    <tr>
        <td>#break</td>
        <td>#break<br>#break(foo == bar)</td>
        <td><font color="green">相同</font></td>
        <td>中断循环</td>
        <td>可以直接带条件</td>
    </tr>
    <tr>
        <td>n/a</td>
        <td>#continue<br>#continue(foo == bar)</td>
        <td><font color="red">新增</font></td>
        <td>继续下一个循环</td>
        <td>可以直接带条件</td>
    </tr>
    <tr>
        <td>#stop</td>
        <td>#stop<br>#stop(foo == bar)</td>
        <td><font color="green">相同</font></td>
        <td>停止模板解析</td>
        <td>可以直接带条件</td>
    </tr>
    <tr>
        <td>#macro($foo)</td>
        <td>#macro foo(...)</td>
        <td>相似</td>
        <td>可复用模板片段宏</td>
        <td></td>
    </tr>
    <tr>
        <td>#macro_name($)</td>
        <td>#call macro_name(...)</td>
        <td>相似</td>
        <td>调用模板片段宏</td>
        <td>采用 #call, 更清晰</td>
    </tr>
    <tr>
        <td>n/a</td>
        <td>#tag foo(...)</td>
        <td><font color="red">新增</font></td>
        <td>自定义标签</td>
        <td>jetbrick-template 允许自定义标签</td>
    </tr>
    <tr>
        <td>#include("foo.txt")</td>
        <td>fileGet("foo.txt")</td>
        <td>不同</td>
        <td>读取文本文件内容</td>
        <td>jetbrick-template 用扩展函数实现</td>
    </tr>
    <tr>
        <td>#parse("foo.vm")</td>
        <td>#include("foo.jetx")<br/>#include("foo.jetx", args)</td>
        <td>相似</td>
        <td>调用子模板</td>
        <td>jetbrick-template 支持私有参数传递</td>
    </tr>
    <tr>
        <td>#evaluate("${1 + 2}")</td>
        <td>n/a</td>
        <td><font color="red">不同</font></td>
        <td>模板求值</td>
        <td>jetbrick-tempate 不支持</td>
    </tr>
    <tr>
        <td>n/a</td>
        <td>#options(...)</td>
        <td><font color="red">新增</font></td>
        <td>模板选项</td>
        <td>jetbrick-tempate 支持独立的模板选项</td>
    </tr>
</table>
