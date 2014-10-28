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
    public static final String TEMPLATE_1X_VERSION = "1.2.11";
    public static final String TEMPLATE_VERSION = "2.0";
    public static final String ALL_VERSION = "14.10.24";

    public static final File JETX_DOCS_DIR = new File(System.getProperty("jetx.docs.dir")).getAbsoluteFile();
    public static final File MARKDOWN_HTML_DIR = new File(System.getProperty("markdown.html.dir")).getAbsoluteFile();
    public static final File SITE_HTML_DIR = new File(System.getProperty("site.html.dir")).getAbsoluteFile();


    public static List<Product> PRODUCT_LIST;

    static {
        PRODUCT_LIST = new ArrayList<Product>();
        Menu menu = null;

        // jetbrick-commons --------------------------------------------------------------
        Product commons = new Product("jetbrick-commons", COMMONS_VERSION);
        PRODUCT_LIST.add(commons);


        // jetbrick-webmvc --------------------------------------------------------------
        Product webmvc = new Product("jetbrick-webmvc", WEBMVC_VERSION);
        PRODUCT_LIST.add(webmvc);


        // jetbrick-ioc --------------------------------------------------------------



        // jetbrick-orm --------------------------------------------------------------



        // jetbrick-template 1x ---------------------------------------------------------
        Product template1x = new Product("jetbrick-template", TEMPLATE_1X_VERSION);
        template1x.setDir("jetbrick-template/1x");
        template1x.setScm("http://github.com/subchen/jetbrick-template-1x");
        template1x.setAnnouncement("jetbrick-template-2.x 已经发布，新版文档请看这里：<a href=\"http://subchen.github.io/jetbrick-template/2x/\">http://subchen.github.io/jetbrick-template/2x/</a>");
        template1x.setHidden(true);
        PRODUCT_LIST.add(template1x);


        // jetbrick-template 2x ---------------------------------------------------------
        Product template2x = new Product("jetbrick-template", TEMPLATE_VERSION);
        template2x.setDir("jetbrick-template/2x");
        template2x.setScm("http://github.com/subchen/jetbrick-template-2x");
        template2x.setAnnouncement("jetbrick-template-1.x 旧版文档请看这里：<a href=\"http://subchen.github.io/jetbrick-template/1x/\">http://subchen.github.io/jetbrick-template/1x/</a>");
        PRODUCT_LIST.add(template2x);

        menu = template2x.addMenu("开发");
        menu.addChild("jetbrick-template/2x/overview.html", "概述 Overview");
        menu.addChild("jetbrick-template/2x/quickstart.html", "快速开始 Quick Start");
        menu.addChild("jetbrick-template/2x/core-object.html", "核心对象 Core");
        menu.addDivider();
        menu.addChild("jetbrick-template/2x/global-context.html", "全局变量 Global Context");
        menu.addChild("jetbrick-template/2x/handle-error.html", "模板详细错误");
        menu.addChild("jetbrick-template/2x/debug.html", "调试模板 Debug");

        menu = template2x.addMenu("进阶");
        menu.addChild("jetbrick-template/2x/ext-method.html", "扩展方法 Method");
        menu.addChild("jetbrick-template/2x/ext-function.html", "扩展函数 Function");
        menu.addChild("jetbrick-template/2x/ext-tag.html", "标签 Tag");
        menu.addChild("jetbrick-template/2x/ext-macro.html", "宏 Macro");
        menu.addDivider();
        menu.addChild("jetbrick-template/2x/include.html", "嵌入子模板 Include");
        menu.addChild("jetbrick-template/2x/layout.html", "布局 Layout");

        menu = template2x.addMenu("jetbrick-template/2x/config.html", "配置");

        menu = template2x.addMenu("语法");
        menu.addChild("jetbrick-template/2x/syntax-text.html", "文本 Text");
        menu.addChild("jetbrick-template/2x/syntax-comment.html", "注释 Comment");
        menu.addChild("jetbrick-template/2x/syntax-value.html", "值 Value");
        menu.addChild("jetbrick-template/2x/syntax-directive.html", "指令 Directive");
        menu.addChild("jetbrick-template/2x/syntax-expression.html", "表达式 Expression");
        menu.addDivider();
        menu.addChild("jetbrick-template/2x/velocity-comparison.html", "和 Velocity 的比较");

        menu = template2x.addMenu("Web集成");
        menu.addChild("jetbrick-template/2x/web-integration.html", "Web 框架集成");
        menu.addDivider();
        menu.addChild("jetbrick-template/2x/web-integration-servlet.html", "直接使用 HttpServlet");
        menu.addChild("jetbrick-template/2x/web-integration-filter.html", "直接使用 Filter");
        menu.addChild("jetbrick-template/2x/web-integration-jetbrickmvc.html", "与 Jetbrick webmvc 集成");
        menu.addChild("jetbrick-template/2x/web-integration-springmvc.html", "与 Spring webmvc 集成");
        menu.addChild("jetbrick-template/2x/web-integration-jfinal.html", "与 JFinal 集成");
        menu.addChild("jetbrick-template/2x/web-integration-jodd.html", "与 Jodd madvoc 集成");
        menu.addChild("jetbrick-template/2x/web-integration-struts.html", "与 Struts 2.x 集成");
        menu.addChild("jetbrick-template/2x/web-integration-nutz.html", "与 Nutz 集成");
        
        template2x.addHiddenMenu("jetbrick-template/2x/download.html");
        
        template2x.addPage("jetbrick-template/2x/index.html");
        template2x.addPage("jetbrick-template/index.jetx");
    }
}
