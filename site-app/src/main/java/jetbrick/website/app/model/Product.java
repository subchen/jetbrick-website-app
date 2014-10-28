package jetbrick.website.app.model;

import java.util.*;

public final class Product {
    private final String name;
    private final String version;
    private String dir;
    private String scm;
    private boolean hidden;
    private String announcement;
    private final List<Menu> menuList;
    private final List<String> pageList;

    public Product(String name, String version) {
        this.name = name;
        this.version = version;
        this.dir = name;
        this.scm = "https://github.io/subchen/" + name;
        this.hidden = false;
        this.announcement = null;
        this.menuList = new ArrayList<Menu>();
        this.pageList = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
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
        Menu menu = new Menu(null, name, false);
        menuList.add(menu);
        return menu;
    }
    
    public Menu addMenu(String url, String name) {
        Menu menu = new Menu(url, name, false);
        menuList.add(menu);
        return menu;
    }

    public void addHiddenMenu(String url) {
        menuList.add(new Menu(url, null, true));
    }
    
    public List<String> getPageList() {
        return pageList;
    }
    
    public void addPage(String url) {
        pageList.add(url);
    }

}
