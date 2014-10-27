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


    public static List<Menu> PRODUCT_MENU_LIST;

    static {
        PRODUCT_MENU_LIST = new ArrayList<Menu>();
        Menu menu = null;

        // jetbrick-commons --------------------------------------------------------------



        // jetbrick-webmvc --------------------------------------------------------------
        Menu webmvcMenu = new Menu("jetbrick-webmvc", "Webmvc");
        PRODUCT_MENU_LIST.add(webmvcMenu);


        // jetbrick-ioc --------------------------------------------------------------



        // jetbrick-orm --------------------------------------------------------------



        // jetbrick-template 1x ---------------------------------------------------------
        Menu template1xMenu = new Menu("模板引擎");
        PRODUCT_MENU_LIST.add(template2xMenu);


        // jetbrick-template 2x ---------------------------------------------------------
        Menu template2xMenu = new Menu("模板引擎");
        PRODUCT_MENU_LIST.add(template2xMenu);

        menu = template2xMenu.add(new Menu("开发指南"));
        menu.add(new Menu("jetbrick-template/2x/overview.md.html", "概述 Overview"));
        menu.add(new Menu("jetbrick-template/2x/quickstart.md.html", "快速开发指南 Quick Start");
        menu.add(new Menu("jetbrick-template/2x/core-object.md.html", "核心对象 Core"));
        menu.addSeparator();
        menu.add(new Menu("jetbrick-template/2x/global-context.md.html", "全局变量 Global Context"));
        menu.add(new Menu("jetbrick-template/2x/handle-error.md.html", "模板详细错误"));
        menu.add(new Menu("jetbrick-template/2x/debug.md.html", "调试模板 Debug"));

        menu = template2xMenu.add(new Menu("进阶使用"));
        menu.addnew Menu("ext-method.md.html", "扩展方法 Method"));
        menu.addnew Menu("ext-function.md.html", "扩展函数 Function"));
        menu.addnew Menu("ext-tag.md.html", "标签 Tag"));
        menu.addnew Menu("ext-macro.md.html", "宏 Macro"));
        menu.addSeparator();
        menu.addnew Menu("include.md.html", "嵌入子模板 Include"));
        menu.addnew Menu("layout.md.html", "布局 Layout"));

        menu = template2xMenu.add(new Menu("配置大全"));

        menu = template2xMenu.add(new Menu("语法参考"));
        menu.add(new Menu("jetbrick-template/2x/syntax-core.md.html", "基本指令"));
        menu.add(new Menu("jetbrick-template/2x/syntax-expression.md.html", "表达式"));
        menu.addnew Menu("ext-macro.md.html", "宏 Macro"));
        menu.addSeparator();
        menu.add(new Menu("jetbrick-template/2x/velocity-comparison.md.html", "和 Velocity 的比较"));

        menu = template2xMenu.add(new Menu("Web 集成"));
        menu.add(new Menu("jetbrick-template/2x/web-integration.md.html", "Web 框架集成"));
        menu.addSeparator();
        menu.add(new Menu("jetbrick-template/2x/web-integration-servlet.md.html", "直接使用 HttpServlet"));
        menu.add(new Menu("jetbrick-template/2x/web-integration-filter.md.html", "直接使用 Filter"));
        menu.add(new Menu("jetbrick-template/2x/web-integration-jetbrickmvc.md.html", "与 Jetbrick webmvc 集成"));
        menu.add(new Menu("jetbrick-template/2x/web-integration-springmvc.md.html", "与 Spring webmvc 集成"));
        menu.add(new Menu("jetbrick-template/2x/web-integration-jfinal.md.html", "与 JFinal 集成"));
        menu.add(new Menu("jetbrick-template/2x/web-integration-jodd.md.html", "与 Jodd madvoc 集成"));
        menu.add(new Menu("jetbrick-template/2x/web-integration-struts.md.html", "与 Struts 2.x 集成"));
        menu.add(new Menu("jetbrick-template/2x/web-integration-nutz.md.html", "与 Nutz 集成"));
    }
}
