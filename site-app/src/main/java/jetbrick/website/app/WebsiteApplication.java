package jetbrick.website.app;

import java.io.File;
import java.util.*;
import jetbrick.io.file.FileCopyUtils;
import jetbrick.util.StringUtils;
import jetbrick.website.app.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WebsiteApplication {
    private final Logger log = LoggerFactory.getLogger(WebsiteApplication.class);

    private final Map<String, Object> ctx = new HashMap<String, Object>();

    public WebsiteApplication() {
        ctx.put("WEBROOT_PATH", AppConfig.WEBROOT_PATH);
        ctx.put("PRODUCT_LIST", AppConfig.PRODUCT_LIST);
    }

    public void execute() {
        for (Product product: AppConfig.PRODUCT_LIST) {
            process(product);
        }
    }

    private void process(Product product) {
        ctx.put("PRODUCT", product);

        for (Menu menubar: product.getMenuList()) {
            process(menubar);

            for (Menu menu: menubar.getChildren()) {
                process(menu);
            }
        }

        for (String url: product.getPageList()) {
             process(url);
        }
    }

    private void process(Menu menu) {
        if (menu.isDivider() || menu.getUrl() == null) {
            return;
        }

        ctx.put("MENU", menu);
        ctx.put("BASE_PATH", getBasePath(menu.getUrl()));
        ctx.put("FILE_PATH", menu.getUrl());

        log.info("Processing: {}", menu.getUrl());

        File file = new File(AppConfig.SITE_HTML_DIR, menu.getUrl());
        TemplateUtils.render(ctx, "/include/markdown.jetx", file);
    }

    private void process(String url) {
        log.info("Processing: {}", url);

        if (url.endsWith(".jetx")) {
            ctx.put("MENU", null);
            ctx.put("BASE_PATH", getBasePath(url));

            String filePath = url.replace(".jetx", ".html");
            ctx.put("FILE_PATH", getBasePath(url));

            File file = new File(AppConfig.SITE_HTML_DIR, filePath);
            TemplateUtils.render(ctx, url, file);
        } else {
            try {
                File src = new File(AppConfig.JETX_DOCS_DIR, url);
                File dest = new File(AppConfig.SITE_HTML_DIR, url);
                FileCopyUtils.copyFile(src, dest);
            } catch(Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private static String getBasePath(String url) {
        int count = StringUtils.count(url, '/');
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<count; i++) {
            if (sb.length() > 0) {
                sb.append('/');
            }
            sb.append("..");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        WebsiteApplication app = new WebsiteApplication();
        app.execute();
    }

}
