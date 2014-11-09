下载 Downloads
=================


Maven 依赖 pom.xml
---------------------------------------

Release 版本已发布到 Maven 中央库： http://central.maven.org/maven2/com/github/subchen/

```xml
<dependency>
    <groupId>com.github.subchen</groupId>
    <artifactId>jetbrick-ioc</artifactId>
    <version>{{IOC-VERSION}}</version>
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

* [jetbrick-ioc-{{IOC-VERSION}}.jar](http://search.maven.org/remotecontent?filepath=com/github/subchen/jetbrick-ioc/{{IOC-VERSION}}/jetbrick-ioc-{{IOC-VERSION}}.jar)
* [jetbrick-ioc-sources-{{IOC-VERSION}}.jar](http://search.maven.org/remotecontent?filepath=com/github/subchen/jetbrick-ioc/{{IOC-VERSION}}/jetbrick-ioc-sources-{{IOC-VERSION}}.jar)
* [jetbrick-ioc-javadoc-{{IOC-VERSION}}.jar](http://search.maven.org/remotecontent?filepath=com/github/subchen/jetbrick-ioc/{{IOC-VERSION}}/jetbrick-ioc-javadoc-{{IOC-VERSION}}.jar)


第三方依赖包 Dependences
---------------------------------------

* [jetbrick-commons-{{COMMONS-VERSION}}.jar](http://search.maven.org/remotecontent?filepath=com/github/subchen/jetbrick-commons/{{COMMONS-VERSION}}/jetbrick-commons-{{COMMONS-VERSION}}.jar)
* [slf4j-api-1.7.7](http://search.maven.org/remotecontent?filepath=org/slf4j/slf4j-api/1.7.7/slf4j-api-1.7.7.jar)

更新历史 Release Notes
---------------------------------------

[完整历史更新记录，请看这里](https://github.com/subchen/jetbrick-ioc/releases)


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
