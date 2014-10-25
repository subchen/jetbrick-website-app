package jetbrick.website.app;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import jetbrick.website.app.model.Menu;

public final class AppConfig {

    public static final String WEBROOT_PATH = "http://subchen.github.io";
    public static final String BASE_PATH = "../../../../../";

    public static final String COMMONS_VERSION = "2.0";
    public static final String IOC_VERSION = "2.0";
    public static final String ORM_VERSION = "2.0";
    public static final String WEBMVC_VERSION = "2.0";
    public static final String TEMPLATE_VERSION = "2.0";
    public static final String ALL_VERSION = "14.10.24";

    public static final File MARKDOWN_HTML_DIR = new File(System.getProperty("markdown.html.dir")).getAbsoluteFile();
    public static final File TARGET_HTML_DIR = new File(System.getProperty("target.html.dir")).getAbsoluteFile();

    //@formatter:off
    public static final List<Menu> TEMPLATE_DOC_LIST = Arrays.asList(
        new Menu("jetbrick-template/2x/overview.md.html", "概述 Overview"),
        new Menu("jetbrick-template/2x/quickstart.md.html", "快速开发指南 Quick Start"),
        new Menu("jetbrick-template/2x/core-object.md.html", "核心对象 Core"),
        new Menu("jetbrick-template/2x/config.md.html", "全局配置选项 Config"),
        new Menu("jetbrick-template/2x/global-context.md.html", "全局变量 Global Context"),
        new Menu("jetbrick-template/2x/handle-error.md.html", "模板详细错误"),
        new Menu("jetbrick-template/2x/debug.md.html", "调试模板 Debug"),
        Menu.SEPARATOR,
        new Menu("jetbrick-template/2x/ext-method.md.html", "扩展－方法 Method"),
        new Menu("jetbrick-template/2x/ext-function.md.html", "扩展－函数 Function"),
        new Menu("jetbrick-template/2x/ext-macro.md.html", "扩展－宏 Macro"),
        new Menu("jetbrick-template/2x/ext-tag.md.html", "扩展－标签 Tag"),
        new Menu("jetbrick-template/2x/include.md.html", "嵌入子模板 Include"),
        new Menu("jetbrick-template/2x/layout.md.html", "布局 Layout"),
        Menu.SEPARATOR,
        new Menu("jetbrick-template/2x/syntax-core.md.html", "语法－基本指令"),
        new Menu("jetbrick-template/2x/syntax-expression.md.html", "语法－表达式"),
        new Menu("jetbrick-template/2x/velocity-comparison.md.html", "语法－和 Velocity 的比较"),
        Menu.SEPARATOR,
        new Menu("jetbrick-template/2x/web-integration.md.html", "Web 框架集成"),
        new Menu("jetbrick-template/2x/web-integration-servlet.md.html", "直接使用 HttpServlet"),
        new Menu("jetbrick-template/2x/web-integration-filter.md.html", "直接使用 Filter"),
        new Menu("jetbrick-template/2x/web-integration-jetbrickmvc.md.html", "与 Jetbrick webmvc 集成"),
        new Menu("jetbrick-template/2x/web-integration-springmvc.md.html", "与 Spring webmvc 集成"),
        new Menu("jetbrick-template/2x/web-integration-jfinal.md.html", "与 JFinal 集成"),
        new Menu("jetbrick-template/2x/web-integration-jodd.md.html", "与 Jodd madvoc 集成"),
        new Menu("jetbrick-template/2x/web-integration-struts.md.html", "与 Struts 2.x 集成"),
        new Menu("jetbrick-template/2x/web-integration-nutz.md.html", "与 Nutz 集成"),
        Menu.SEPARATOR,
        new Menu("jetbrick-template/2x/download.md.html", "下载 Downloads"),
        //new Menu("jetbrick-template/2x/release-notes.md.html", "变更历史 Release Notes"),
        Menu.SEPARATOR,
        new Menu("jetbrick-template/2x/about.md.html", "关于 About")
    );
    //@formatter:on

}
