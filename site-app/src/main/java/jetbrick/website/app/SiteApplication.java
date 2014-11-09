package jetbrick.website.app;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import jetbrick.io.file.FileCopyUtils;
import jetbrick.website.app.model.Menu;
import jetbrick.website.app.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SiteApplication {
    private final Logger log = LoggerFactory.getLogger(SiteApplication.class);

    private final Map<String, Object> ctx = new HashMap<String, Object>();

    public SiteApplication() {
        ctx.put("WEBROOT_PATH", AppConfig.WEBROOT_PATH);
        ctx.put("PRODUCT_LIST", AppConfig.PRODUCT_LIST);

        ctx.put("COMMONS_VERSION", AppConfig.COMMONS_VERSION);
        ctx.put("IOC_VERSION", AppConfig.IOC_VERSION);
        ctx.put("ORM_VERSION", AppConfig.ORM_VERSION);
        ctx.put("WEBMVC_VERSION", AppConfig.WEBMVC_VERSION);
        ctx.put("TEMPLATE_1X_VERSION", AppConfig.TEMPLATE_1X_VERSION);
        ctx.put("TEMPLATE_VERSION", AppConfig.TEMPLATE_VERSION);
        ctx.put("ALL_VERSION", AppConfig.ALL_VERSION);
    }

    public void execute() {
        for (Product product : AppConfig.PRODUCT_LIST) {
            process(product);
        }
    }

    private void process(Product product) {
        ctx.put("PRODUCT", product);

        for (Menu menubar : product.getMenuList()) {
            process(menubar);

            for (Menu menu : menubar.getChildren()) {
                process(menu);
            }
        }

        for (String url : product.getFileList()) {
            process(url, null);
        }
    }

    private void process(Menu menu) {
        if (menu.isDivider() || menu.getUrl() == null) {
            return;
        }

        process(menu.getUrl(), menu.getTitle());
    }

    private void process(String url, String title) {
        log.info("Processing: {}", url);

        if (url.endsWith(".md")) {
            String filePath = url.replace(".md", ".html");

            if (title == null) {
                title = AppFunctions.getMarkdownTitle(filePath);
            }

            ctx.put("TITLE", title);
            ctx.put("BASE_PATH", AppFunctions.getBasePath(filePath));
            ctx.put("FILE_PATH", filePath);

            File file = new File(AppConfig.SITE_HTML_DIR, filePath);
            TemplateUtils.render(ctx, "/include/markdown.jetx", file);
        } else if (url.endsWith(".jetx")) {
            String filePath = url.replace(".jetx", ".html");

            ctx.put("TITLE", null);
            ctx.put("BASE_PATH", AppFunctions.getBasePath(filePath));
            ctx.put("FILE_PATH", filePath);

            File file = new File(AppConfig.SITE_HTML_DIR, filePath);
            TemplateUtils.render(ctx, url, file);
        } else {
            try {
                File src = new File(AppConfig.JETX_DOCS_DIR, url);
                File dest = new File(AppConfig.SITE_HTML_DIR, url);
                FileCopyUtils.copyFile(src, dest);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public static void main(String[] args) {
        SiteApplication app = new SiteApplication();
        app.execute();
    }

}
