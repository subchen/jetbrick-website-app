package jetbrick.website.app.model;

import java.util.*;
import jetbrick.util.StringUtils;

public final class Menu {
    private static final Menu SEPARATOR = new Menu(null, null, true, true);

    private final String location;
    private final String title;
    private final boolean separator;
    private final boolean display;
    private List<Menu> children;

    public Menu(String title) {
        this(null, title, false, true);
    }

    public Menu(String location, String title) {
        this(location, title, false, true);
    }

    private Menu(String location, String title, boolean separator, boolean display) {
        this.location = location;
        this.title = title;
        this.separator = separator;
        this.display = display;
        this.children = null;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public boolean isSeparator() {
        return separator;
    }
    
    public boolean isDisplay() {
        return display;
    }

    public String getBasePath() {
        int count = StringUtils.count(location, '/');
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<count; i++) {
            if (sb.length() > 0) {
                sb.append('/');
            }
            sb.append("..");
        }
        return sb.toString();
    }

    public List<Menu> getChildren() {
        return (children == null) ? Collections.<Menu>emptyList() : children;
    }

    public Menu add(Menu menu) {
        if (children == null) {
            children = new ArrayList<Menu>();
        }
        children.add(menu);
        return menu;
    }

    public void addSeparator() {
        children.add(SEPARATOR);
    }
}
