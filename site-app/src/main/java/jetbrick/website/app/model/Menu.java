package jetbrick.website.app.model;

import java.util.*;
import jetbrick.website.app.AppFunctions;

public final class Menu {
    private static final Menu DIVIDER = new Menu(null, null);

    private final String url;
    private final String title;
    private List<Menu> children;

    public Menu(String url, String title) {
        if (title == null && url != null && url.endsWith(".md")) {
            String filePath = url.replace(".jetx", ".html");
            title = AppFunctions.getMarkdownTitle(filePath);
        }

        this.url = url;
        this.title = title;
        this.children = null;
    }

    public String getUrl() {
        return url;
    }
    
    public String getHtmlUrl() {
        return url == null ? null : url.replace(".md", ".html").replace(".jetx", ".html");
    }

    public String getTitle() {
        return title;
    }
    
    public boolean isDivider() {
        return this == DIVIDER;
    }

    public List<Menu> getChildren() {
        return (children == null) ? Collections.<Menu>emptyList() : children;
    }

    public void addChild(String url) {
        if (children == null) {
            children = new ArrayList<Menu>();
        }
        children.add(new Menu(url, null));
    }
    
    public void addChild(String url, String title) {
        if (children == null) {
            children = new ArrayList<Menu>();
        }
        children.add(new Menu(url, title));
    }

    public void addDivider() {
        children.add(DIVIDER);
    }
}
