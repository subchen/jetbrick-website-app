package jetbrick.website.app.model;

import java.util.*;

public final class Product {
    private final String name;
    private final String version;
    private final String homeURL;
    private final String githubURL;
    private List<Menu> menuList;

    public Product(String name, String version, String homeURL, String githubURL) {
        this.name = name;
        this.version = version;
        this.homeURL = homeURL;
        this.githubURL = githubURL;
        this.menuList = new ArrayList<Menu>();
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getHomeURL() {
        return homeURL;
    }

    public String getGithubURL() {
        return githubURL;
    }
    
    public List<Menu> getMenuList() {
        return menuList;
    }

    public Menu add(Menu menu) {
        menuList.add(menu);
        return menu;
    }

    public void addHidden(String location) {
        menuList.add(new Menu(location, null));
    }
}
