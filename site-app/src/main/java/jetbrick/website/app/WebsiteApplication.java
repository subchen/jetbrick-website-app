package jetbrick.website.app;

public final class WebsiteApplication {

    public WebsiteApplication() {

    }

    public static void main(String[] args) {
        String html = MarkdownUtils.toHTML("jetbrick-webmvc/view.md", "utf-8");
        html = MarkdownUtils.toPrettyHTML(html);
        System.out.println(html);
    }

}
