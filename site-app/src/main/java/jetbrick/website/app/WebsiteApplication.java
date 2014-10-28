package jetbrick.website.app;

import java.io.File;
import java.util.*;
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
    }

    private void process(Menu menu) {
        if (menu.isDivider() || menu.getUrl() == null) {
            return;
        }

        ctx.put("MENU", menu);
        ctx.put("BASE_PATH", menu.getBasePath());

        log.info("Processing: {}", menu.getUrl());

        File file = new File(AppConfig.TARGET_HTML_DIR, menu.getUrl());
        TemplateUtils.render(ctx, file);
    }

    public static void main(String[] args) {
        WebsiteApplication app = new WebsiteApplication();
        app.execute();
    }

}
