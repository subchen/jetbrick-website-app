package jetbrick.website.app.model;

import java.util.ArrayList;
import java.util.List;

public final class Product {
    private final String name;
    private final String version;
    private String basedir;
    private String welcome;
    private String scm;
    private boolean hidden;
    private String announcement;
    private final List<Menu> menuList;
    private final List<String> fileList;

    public Product(String name, String version) {
        this.name = name;
        this.version = version;
        this.basedir = name;
        this.welcome = name + "/index.html";
        this.scm = "https://github.com/subchen/" + name;
        this.hidden = false;
        this.announcement = null;
        this.menuList = new ArrayList<Menu>();
        this.fileList = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getBasedir() {
        return basedir;
    }

    public void setBasedir(String basedir) {
        this.basedir = basedir;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getScm() {
        return scm;
    }

    public void setScm(String scm) {
        this.scm = scm;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public Menu addMenu(String name) {
        Menu menu = new Menu(null, name);
        menuList.add(menu);
        return menu;
    }

    public Menu addMenu(String url, String name) {
        Menu menu = new Menu(url, name);
        menuList.add(menu);
        return menu;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void add(String url) {
        fileList.add(url);
    }

}
