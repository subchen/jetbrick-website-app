package jetbrick.website.app.model;

import java.util.*;

public final class Menu {
    private static final Menu DIVIDER = new Menu(null, null, false);

    private final String url;
    private final String title;
    private final boolean hidden;
    private List<Menu> children;

    public Menu(String url, String title, boolean hidden) {
        this.url = url;
        this.title = title;
        this.hidden = hidden;
        this.children = null;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
    
    public boolean isHidden() {
        return hidden;
    }

    public boolean isDivider() {
        return this == DIVIDER;
    }

    public List<Menu> getChildren() {
        return (children == null) ? Collections.<Menu>emptyList() : children;
    }

    public void addChild(String url, String title) {
        if (children == null) {
            children = new ArrayList<Menu>();
        }
        children.add(new Menu(url, title, true));
    }

    public void addDivider() {
        children.add(DIVIDER);
    }
}
