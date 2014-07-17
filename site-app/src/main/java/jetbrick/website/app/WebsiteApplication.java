package jetbrick.website.app;

public final class WebsiteApplication {

    public WebsiteApplication() {

    }

    public static void main(String[] args) {
        System.out.println("hello: " + args[0]);
        
        String html = MarkdownUtils.toHTML("jetbrick-webmvc/view.md", "utf-8");
        System.out.println(html);
    }

}
