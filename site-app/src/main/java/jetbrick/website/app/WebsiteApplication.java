package jetbrick.website.app;

import java.io.File;
import java.util.*;
import jetbrick.website.app.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WebsiteApplication {
    private final Logger log = LoggerFactory.getLogger(WebsiteApplication.class);

    public WebsiteApplication() {
    }

    public void build(Product product) {
        Map<String, Object> ctx = new HashMap<String, Object>();
        ctx.put("products", AppConfig.PRODUCT_LIST);
        ctx.put("product", product);

        for (Menu topMenu: product.getMenuList()) {
            build(topMenu, ctx);
            for (Menu menu: topMenu.getChildren()) {
                build(menu, ctx);
            }
        }
    }
    
    private void build(Menu menu, Map<String, Object> ctx) {
        if (menu.isSeparator()) {
            return;
        }
        if (menu.getLocation() == null) {
            return;
        }
        
        log.info("Processing: {}", menu.getLocation());

        ctx.put("BASE_PATH", menu.getBasePath());
        ctx.put("menu", menu);

        File file = new File(AppConfig.TARGET_HTML_DIR, menu.getLocation());
        TemplateUtils.render(ctx, file);
    }

    public static void main(String[] args) {
        WebsiteApplication app = new WebsiteApplication();
        
        for (Product product: AppConfig.PRODUCT_LIST) {
            app.build(product);
        }
    }

}
