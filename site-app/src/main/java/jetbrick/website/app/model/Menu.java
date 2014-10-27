package jetbrick.website.app.model;

import java.util.*;

public final class Menu {
    private static final Menu SEPARATOR = new Menu(null, null, true);

    private final String location;
    private final String title;
    private final boolean separator;
    private List<Menu> children;

    public Menu(String title) {
        this(null, title, false);
    }

    public Menu(String location, String title) {
        this(location, title, false);
    }

    private Menu(String location, String title, boolean separator) {
        this.location = location;
        this.title = title;
        this.separator = separator;
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
