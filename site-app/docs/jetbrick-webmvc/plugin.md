自定义插件 Plugin
============================

Plugin 是 jetbrick webmvc 用来进行扩展自身功能的。Plugin 会随着 jetbrick webmvc 的启动而启动。

Plugin 例子
---------------------

用户自定义的 Plugin 需要实现 `jetbrick.web.mvc.plugin.Plugin` 接口。

```java
package jetbrick.docs.samples;

import jetbrick.dao.schema.upgrade.DbUpgradeApp;
import jetbrick.web.mvc.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 支持数据库自动升级插件
 */
public class DbUpgradePlugin implements Plugin {
    final Logger log = LoggerFactory.getLogger(DbUpgradePlugin.class);

    @Override
    public void initialize() {
        try {
            DbUpgradeApp.execute();
        } catch (Throwable e) {
            log.error("DB Upgrade Exception.", e);
            log.error("***********************************");
            log.error("**          JVM exit!!!          **");
            log.error("***********************************");
            System.exit(1);
        }
    }

    @Override
    public void destory() {
    }
}
```

Plugin 配置 
---------------------

```
$DbUpgradePlugin = jetbrick.docs.samples.DbUpgradePlugin

web.plugins = $DbUpgradePlugin, ...
```



