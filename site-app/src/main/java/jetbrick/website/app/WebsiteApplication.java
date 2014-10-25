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

    public void build(List<Menu> menus) {
        Map<String, Object> ctx = new HashMap<String, Object>();
        ctx.put("menus", menus);

        for (Menu menu: menus) {
            if (menu.isSeparator()) {
                continue;
            }
            log.info("Processing: {}", menu.getLocation());

            ctx.put("menu", menu);

            File file = new File(AppConfig.TARGET_HTML_DIR, menu.getLocation());
            TemplateUtils.render(ctx, file);
        }

    }

    public static void main(String[] args) {
        WebsiteApplication app = new WebsiteApplication();
        app.build(AppConfig.TEMPLATE_DOC_LIST);
    }

}
