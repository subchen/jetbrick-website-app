package jetbrick.website.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import jetbrick.website.app.model.Menu;
import jetbrick.website.app.model.Product;

public final class AppConfig {

    public static final String WEBROOT_PATH = "http://subchen.github.io";

    public static final String COMMONS_VERSION = "2.0.4";
    public static final String IOC_VERSION = "2.0.1";
    public static final String ORM_VERSION = "2.0";
    public static final String WEBMVC_VERSION = "2.1";
    public static final String TEMPLATE_1X_VERSION = "1.2.12";
    public static final String TEMPLATE_VERSION = "2.0.5";
    public static final String ALL_VERSION = "14.10.24";

    public static final File JETX_DOCS_DIR = new File(System.getProperty("jetx.docs.dir")).getAbsoluteFile();
    public static final File MARKDOWN_HTML_DIR = new File(System.getProperty("markdown.html.dir")).getAbsoluteFile();
    public static final File SITE_HTML_DIR = new File(System.getProperty("site.html.dir")).getAbsoluteFile();


    public static List<Product> PRODUCT_LIST;

    static {
        PRODUCT_LIST = new ArrayList<Product>();
        Menu menu = null;

        // jetbrick-all --------------------------------------------------------------
        Product all = new Product("jetbrick", ALL_VERSION);
        all.setBasedir("");
        all.setWelcome("index.html");
        all.setHidden(true);
        PRODUCT_LIST.add(all);

        all.add("index.jetx");
        //all.add("jetbrick-cases.jetx");
        all.add("jetbrick-code-standards.md");
        all.add("about.md");

        // jetbrick-commons --------------------------------------------------------------
        Product commons = new Product("jetbrick-commons", COMMONS_VERSION);
        commons.setWelcome("jetbrick-commons/overview.html");
        PRODUCT_LIST.add(commons);

        commons.add("jetbrick-commons/overview.md");
        commons.add("jetbrick-commons/download.md");
        commons.add("jetbrick-commons/index.html");

        // jetbrick-ioc --------------------------------------------------------------
        Product ioc = new Product("jetbrick-ioc", IOC_VERSION);
        ioc.setWelcome("jetbrick-ioc/overview.html");
        PRODUCT_LIST.add(ioc);

        menu = ioc.addMenu("开发指南");
        menu.addChild("jetbrick-ioc/overview.md");
        menu.addChild("jetbrick-ioc/helloworld.md");
        menu.addDivider();
        menu.addChild("jetbrick-ioc/anno-iocbean.md");
        menu.addChild("jetbrick-ioc/anno-inject.md");
        menu.addChild("jetbrick-ioc/lifecycle.md");
        menu.addChild("jetbrick-ioc/anno-config.md");
        menu.addChild("jetbrick-ioc/anno-springbean.md");
        menu.addDivider();
        menu.addChild("jetbrick-ioc/custom-annotation.md");
        menu.addDivider();
        menu.addChild("jetbrick-ioc/annotation-list.md");

        ioc.add("jetbrick-ioc/download.md");
        ioc.add("jetbrick-ioc/index.html");

        // jetbrick-webmvc --------------------------------------------------------------
        Product webmvc = new Product("jetbrick-webmvc", WEBMVC_VERSION);
        webmvc.setWelcome("jetbrick-webmvc/overview.html");
        PRODUCT_LIST.add(webmvc);

        menu = webmvc.addMenu("开发指南");
        menu.addChild("jetbrick-webmvc/overview.md");
        menu.addChild("jetbrick-webmvc/helloworld.md");
        menu.addChild("jetbrick-webmvc/webxml.md");
        menu.addChild("jetbrick-webmvc/config.md");
        menu.addChild("jetbrick-webmvc/bypass-urls.md");
        menu.addDivider();
        menu.addChild("jetbrick-webmvc/controller.md");
        menu.addChild("jetbrick-webmvc/action-inject.md");
        menu.addDivider();
        menu.addChild("jetbrick-webmvc/result.md");
        menu.addChild("jetbrick-webmvc/view.md");
        menu.addDivider();
        menu.addChild("jetbrick-webmvc/fileupload.md");
        menu.addChild("jetbrick-webmvc/request-context.md");
        menu.addDivider();
        menu.addChild("jetbrick-webmvc/annotation-list.md");

        menu = webmvc.addMenu("高级用法");
        menu.addChild("jetbrick-webmvc/exception-handler.md");
        menu.addDivider();
        menu.addChild("jetbrick-webmvc/interceptor.md");
        menu.addChild("jetbrick-webmvc/plugin.md");
        menu.addDivider();
        menu.addChild("jetbrick-webmvc/custom-annotation.md");

        webmvc.add("jetbrick-webmvc/download.md");
        webmvc.add("jetbrick-webmvc/index.html");

        // jetbrick-orm --------------------------------------------------------------
        Product orm = new Product("jetbrick-orm", ORM_VERSION);
        orm.setWelcome("jetbrick-orm/overview.html");
        PRODUCT_LIST.add(orm);

        ioc.add("jetbrick-orm/overview.md");
        ioc.add("jetbrick-orm/download.md");
        ioc.add("jetbrick-orm/index.html");

        // jetbrick-template 1x ---------------------------------------------------------
        Product template1x = new Product("jetbrick-template-1x", TEMPLATE_1X_VERSION);
        template1x.setBasedir("jetbrick-template/1x");
        template1x.setWelcome("jetbrick-template/index.html");
        template1x.setScm("http://github.com/subchen/jetbrick-template-1x");
        template1x.setAnnouncement("jetbrick-template-2.x 已经发布，新版文档请看这里：<a href=\"http://subchen.github.io/jetbrick-template/2x/\">http://subchen.github.io/jetbrick-template/2x/</a>");
        template1x.setHidden(true);
        PRODUCT_LIST.add(template1x);

        template1x.addMenu("jetbrick-template/1x/overview.md", "概述");
        template1x.addMenu("jetbrick-template/1x/userguide.md", "开发");
        template1x.addMenu("jetbrick-template/1x/config.md", "配置");
        template1x.addMenu("jetbrick-template/1x/syntax.md", "语法");
        template1x.addMenu("jetbrick-template/1x/integration.md", "Web集成");

        menu = template1x.addMenu("FAQ");
        menu.addChild("jetbrick-template/1x/faq-compile.md", "将模板编译成 Java Class 有什么好处");
        menu.addChild("jetbrick-template/1x/faq-define.md", "为什么需要 #define 声明变量类型？");
        menu.addChild("jetbrick-template/1x/faq-error.md", "常见错误分析");
        menu.addChild("jetbrick-template/1x/faq-autoscan.md", "如何让自动扫描发现用户自定义的扩展方法/函数/标签 Class");
        menu.addChild("jetbrick-template/1x/faq-include.md", "如何嵌入子模板？（父子间参数传递）");
        menu.addChild("jetbrick-template/1x/faq-layout.md", "如何实现 layout 功能？");
        menu.addChild("jetbrick-template/1x/faq-tag.md", "如何自定义 Tag？");
        menu.addChild("jetbrick-template/1x/faq-spring.md", "在 Spring 中的集成方法");

        template1x.add("jetbrick-template/1x/download.md");
        template1x.add("jetbrick-template/1x/release-notes.md");
        template1x.add("jetbrick-template/1x/index.html");

        // jetbrick-template 2x ---------------------------------------------------------
        Product template2x = new Product("jetbrick-template", TEMPLATE_VERSION);
        template2x.setBasedir("jetbrick-template/2x");
        template2x.setWelcome("jetbrick-template/index.html");
        template2x.setScm("http://github.com/subchen/jetbrick-template-2x");
        template2x.setAnnouncement("jetbrick-template-1.x 旧版文档请看这里：<a href=\"http://subchen.github.io/jetbrick-template/1x/\">http://subchen.github.io/jetbrick-template/1x/</a>");
        PRODUCT_LIST.add(template2x);

        menu = template2x.addMenu("开发");
        menu.addChild("jetbrick-template/2x/overview.md", "概述 Overview");
        menu.addChild("jetbrick-template/2x/quickstart.md", "快速开始 Quick Start");
        menu.addChild("jetbrick-template/2x/core-object.md", "核心对象 Core");
        menu.addDivider();
        menu.addChild("jetbrick-template/2x/global-context.md", "全局变量 Global Context");
        menu.addChild("jetbrick-template/2x/handle-error.md", "模板详细错误");
        menu.addChild("jetbrick-template/2x/debug.md", "调试模板 Debug");

        menu = template2x.addMenu("进阶");
        menu.addChild("jetbrick-template/2x/ext-method.md", "扩展方法 Method");
        menu.addChild("jetbrick-template/2x/ext-function.md", "扩展函数 Function");
        menu.addChild("jetbrick-template/2x/ext-tag.md", "标签 Tag");
        menu.addChild("jetbrick-template/2x/ext-macro.md", "宏 Macro");
        menu.addDivider();
        menu.addChild("jetbrick-template/2x/include.md", "嵌入子模板 Include");
        menu.addChild("jetbrick-template/2x/layout.md", "布局 Layout");
        menu.addChild("jetbrick-template/2x/security-manager.md", "安全管理器 SecurityManager");

        menu = template2x.addMenu("jetbrick-template/2x/config.md", "配置");

        menu = template2x.addMenu("语法");
        menu.addChild("jetbrick-template/2x/syntax-text.md", "文本 Text");
        menu.addChild("jetbrick-template/2x/syntax-comment.md", "注释 Comment");
        menu.addChild("jetbrick-template/2x/syntax-value.md", "值 Value");
        menu.addChild("jetbrick-template/2x/syntax-directive.md", "指令 Directive");
        menu.addChild("jetbrick-template/2x/syntax-expression.md", "表达式 Expression");
        menu.addDivider();
        menu.addChild("jetbrick-template/2x/velocity-comparison.md", "和 Velocity 的比较");

        menu = template2x.addMenu("Web集成");
        menu.addChild("jetbrick-template/2x/web-integration.md", "Web 框架集成");
        menu.addDivider();
        menu.addChild("jetbrick-template/2x/web-integration-servlet.md", "直接使用 HttpServlet");
        menu.addChild("jetbrick-template/2x/web-integration-filter.md", "直接使用 Filter");
        menu.addChild("jetbrick-template/2x/web-integration-jetbrickmvc.md", "与 Jetbrick webmvc 集成");
        menu.addChild("jetbrick-template/2x/web-integration-springmvc.md", "与 Spring webmvc 集成");
        menu.addChild("jetbrick-template/2x/web-integration-jfinal.md", "与 JFinal 集成");
        menu.addChild("jetbrick-template/2x/web-integration-jodd.md", "与 Jodd madvoc 集成");
        menu.addChild("jetbrick-template/2x/web-integration-struts.md", "与 Struts 2.x 集成");
        menu.addChild("jetbrick-template/2x/web-integration-nutz.md", "与 Nutz 集成");

        template2x.add("jetbrick-template/2x/download.md");
        template2x.add("jetbrick-template/2x/index.html");
        template2x.add("jetbrick-template/index.jetx");
    }
}
