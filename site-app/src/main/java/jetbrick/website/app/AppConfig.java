package jetbrick.website.app;

import java.io.File;
import java.util.*;
import jetbrick.website.app.model.*;

public final class AppConfig {

    public static final String WEBROOT_PATH = "http://subchen.github.io";

    public static final String COMMONS_VERSION = "2.0";
    public static final String IOC_VERSION = "2.0";
    public static final String ORM_VERSION = "2.0";
    public static final String WEBMVC_VERSION = "2.0";
    public static final String TEMPLATE_VERSION = "2.0";
    public static final String ALL_VERSION = "14.10.24";

    public static final File MARKDOWN_HTML_DIR = new File(System.getProperty("markdown.html.dir")).getAbsoluteFile();
    public static final File TARGET_HTML_DIR = new File(System.getProperty("target.html.dir")).getAbsoluteFile();


    public static List<Product> PRODUCT_LIST;

    static {
        PRODUCT_LIST = new ArrayList<Product>();
        Menu menu = null;

        // jetbrick-commons --------------------------------------------------------------



        // jetbrick-webmvc --------------------------------------------------------------
        //Product webmvc = new Product("jetbrick-webmvc", "2.0");
        //PRODUCT_LIST.add(webmvc);


        // jetbrick-ioc --------------------------------------------------------------



        // jetbrick-orm --------------------------------------------------------------



        // jetbrick-template 1x ---------------------------------------------------------
        //Product template1x = new Product("jetbrick-template", "1.2.11");
        //PRODUCT_LIST.add(template1x);


        // jetbrick-template 2x ---------------------------------------------------------
        Product template2x = new Product("jetbrick-template", "2.0", "jetbrick-template/2x/dwonload.html", "http://github.com/subchen/jetbrick-template-2x");
        PRODUCT_LIST.add(template2x);

        menu = template2x.add(new Menu("开发指南"));
        menu.add(new Menu("jetbrick-template/2x/overview.html", "概述 Overview"));
        menu.add(new Menu("jetbrick-template/2x/quickstart.html", "快速开发指南 Quick Start"));
        menu.add(new Menu("jetbrick-template/2x/core-object.html", "核心对象 Core"));
        menu.addSeparator();
        menu.add(new Menu("jetbrick-template/2x/global-context.html", "全局变量 Global Context"));
        menu.add(new Menu("jetbrick-template/2x/handle-error.html", "模板详细错误"));
        menu.add(new Menu("jetbrick-template/2x/debug.html", "调试模板 Debug"));

        menu = template2x.add(new Menu("进阶使用"));
        menu.add(new Menu("jetbrick-template/2x/ext-method.html", "扩展方法 Method"));
        menu.add(new Menu("jetbrick-template/2x/ext-function.html", "扩展函数 Function"));
        menu.add(new Menu("jetbrick-template/2x/ext-tag.html", "标签 Tag"));
        menu.add(new Menu("jetbrick-template/2x/ext-macro.html", "宏 Macro"));
        menu.addSeparator();
        menu.add(new Menu("jetbrick-template/2x/include.html", "嵌入子模板 Include"));
        menu.add(new Menu("jetbrick-template/2x/layout.html", "布局 Layout"));

        menu = template2x.add(new Menu("jetbrick-template/2x/config.html", "配置大全"));

        menu = template2x.add(new Menu("语法参考"));
        menu.add(new Menu("jetbrick-template/2x/syntax-core.html", "基本指令"));
        menu.add(new Menu("jetbrick-template/2x/syntax-expression.html", "表达式"));
        menu.add(new Menu("jetbrick-template/2x/ext-macro.html", "宏 Macro"));
        menu.addSeparator();
        menu.add(new Menu("jetbrick-template/2x/velocity-comparison.html", "和 Velocity 的比较"));

        menu = template2x.add(new Menu("Web 集成"));
        menu.add(new Menu("jetbrick-template/2x/web-integration.html", "Web 框架集成"));
        menu.addSeparator();
        menu.add(new Menu("jetbrick-template/2x/web-integration-servlet.html", "直接使用 HttpServlet"));
        menu.add(new Menu("jetbrick-template/2x/web-integration-filter.html", "直接使用 Filter"));
        menu.add(new Menu("jetbrick-template/2x/web-integration-jetbrickmvc.html", "与 Jetbrick webmvc 集成"));
        menu.add(new Menu("jetbrick-template/2x/web-integration-springmvc.html", "与 Spring webmvc 集成"));
        menu.add(new Menu("jetbrick-template/2x/web-integration-jfinal.html", "与 JFinal 集成"));
        menu.add(new Menu("jetbrick-template/2x/web-integration-jodd.html", "与 Jodd madvoc 集成"));
        menu.add(new Menu("jetbrick-template/2x/web-integration-struts.html", "与 Struts 2.x 集成"));
        menu.add(new Menu("jetbrick-template/2x/web-integration-nutz.html", "与 Nutz 集成"));
        
        template2x.addHidden("jetbrick-template/2x/download.html");
    }
}
