下载 Downloads
=================


Maven 依赖 pom.xml
---------------------------------------

Release 版本已发布到 Maven 中央库： http://central.maven.org/maven2/com/github/subchen/

```xml
<dependency>
    <groupId>com.github.subchen</groupId>
    <artifactId>jetbrick-template-core</artifactId>
    <version>{{TEMPLATE.VERSION}}</version>
</dependency>
```

如果你需要使用 Snapshot 版本，那么需要你可以选择：

1. 下载： https://oss.sonatype.org/content/repositories/snapshots/com/github/subchen/
2. pom.xml 中加入下面的代码

```xml
<repositories>
    <repository>
        <id>oss-snapshots</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
        <releases>
            <enabled>false</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```


最新版本 Latest Version
---------------------------------------

* [jetbrick-template-core-{{TEMPLATE.VERSION}}.jar](http://search.maven.org/remotecontent?filepath=com/github/subchen/jetbrick-template-core/{{TEMPLATE.VERSION}}/jetbrick-template-core-{{TEMPLATE.VERSION}}.jar)
* [jetbrick-template-serlvet-{{TEMPLATE.VERSION}}.jar](http://search.maven.org/remotecontent?filepath=com/github/subchen/jetbrick-template-serlvet/{{TEMPLATE.VERSION}}/jetbrick-template-serlvet-{{TEMPLATE.VERSION}}.jar)
* [jetbrick-template-jetbrickmvc-{{TEMPLATE.VERSION}}.jar](http://search.maven.org/remotecontent?filepath=com/github/subchen/jetbrick-template-jetbrickmvc/{{TEMPLATE.VERSION}}/jetbrick-template-jetbrickmvc-{{TEMPLATE.VERSION}}.jar)
* [jetbrick-template-springmvc-{{TEMPLATE.VERSION}}.jar](http://search.maven.org/remotecontent?filepath=com/github/subchen/jetbrick-template-springmvc/{{TEMPLATE.VERSION}}/jetbrick-template-springmvc-{{TEMPLATE.VERSION}}.jar)
* [jetbrick-template-jfinal-{{TEMPLATE.VERSION}}.jar](http://search.maven.org/remotecontent?filepath=com/github/subchen/jetbrick-template-jfinal/{{TEMPLATE.VERSION}}/jetbrick-template-jfinal-{{TEMPLATE.VERSION}}.jar)
* [jetbrick-template-jodd-{{TEMPLATE.VERSION}}.jar](http://search.maven.org/remotecontent?filepath=com/github/subchen/jetbrick-template-jodd/{{TEMPLATE.VERSION}}/jetbrick-template-jodd-{{TEMPLATE.VERSION}}.jar)
* [jetbrick-template-struts-{{TEMPLATE.VERSION}}.jar](http://search.maven.org/remotecontent?filepath=com/github/subchen/jetbrick-template-struts/{{TEMPLATE.VERSION}}/jetbrick-template-struts-{{TEMPLATE.VERSION}}.jar)
* [jetbrick-template-nutz-{{TEMPLATE.VERSION}}.jar](http://search.maven.org/remotecontent?filepath=com/github/subchen/jetbrick-template-nutz/{{TEMPLATE.VERSION}}/jetbrick-template-nutz-{{TEMPLATE.VERSION}}.jar)


第三方依赖包 Dependences
---------------------------------------

* [antlr-runtime-4.3.jar](http://search.maven.org/remotecontent?filepath=org/antlr/antlr-runtime/4.3/antlr-runtime-4.3.jar)
* [slf4j-api-1.7.7](http://search.maven.org/remotecontent?filepath=org/slf4j/slf4j-api/1.7.7/slf4j-api-1.7.7.jar)
* [jetbrick-commons-{{COMMONS.VERSION}}.jar](http://search.maven.org/remotecontent?filepath=com/github/subchen/jetbrick-commons/{{COMMONS.VERSION}}/jetbrick-commons-{{COMMONS.VERSION}}.jar)


范例下载 Samples
---------------------------------------

* [jetx-samples-servlet.zip](demo/jetx-samples-servlet.zip)
* [jetx-samples-jfinal.zip](demo/jetx-samples-jfinal.zip)
* [jetx-samples-springmvc.zip](demo/jetx-samples-springmvc.zip)
* [jetx-samples-struts.zip](demo/jetx-samples-struts.zip)
* [jetx-samples-jodd.zip](demo/jetx-samples-jodd.zip)

下载的 zip 包中包含完整的源代码和可直接运行的 war 包。
更多代码可以前往： https://github.com/subchen/jetbrick-template-webmvc-samples/


更新历史 Release Notes
---------------------------------------

[完整历史版本，请看这里](history.html)


开源许可 License
---------------------------------------

```
Copyright 2013-2014 Guoqiang Chen, Shanghai, China. All rights reserved.

  Author: Guoqiang Chen
   Email: subchen@gmail.com
  WebURL: https://github.com/subchen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
