package jetbrick.website.app;

import java.io.File;
import java.util.*;
import jetbrick.website.app.model.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WebsiteApplication {
    private final Logger log = LoggerFactory.getLogger(WebsiteApplication.class);

    public WebsiteApplication() {
    }

    public void build(Menu productMenu) {
        Map<String, Object> ctx = new HashMap<String, Object>();
        ctx.put("productMenu", productMenu);

        for (Menu topMenu: productMenu) {
            for (Menu menu: topMenu) {
                if (menu.isSeparator()) {
                    continue;
                }
                log.info("Processing: {}", menu.getLocation());
    
                ctx.put("menu", menu);
    
                File file = new File(AppConfig.TARGET_HTML_DIR, menu.getLocation());
                TemplateUtils.render(ctx, file);
            }
        }

    }

    public static void main(String[] args) {
        WebsiteApplication app = new WebsiteApplication();
        
        for (Menu menu: AppConfig.PRODUCT_MENU_LIST) {
            app.build(menu);
        }
    }

}
