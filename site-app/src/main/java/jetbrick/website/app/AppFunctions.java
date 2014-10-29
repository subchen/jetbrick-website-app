package jetbrick.website.app;

import java.io.File;
import jetbrick.io.IoUtils;
import jetbrick.template.JetAnnotations;
import jetbrick.util.CharsetUtils;
import jetbrick.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@JetAnnotations.Functions
public final class AppFunctions {

    public static String htmlGet(String url) {
        url = url.replace(".md", ".html");
        File file = new File(AppConfig.MARKDOWN_HTML_DIR, url);
        return IoUtils.toString(file, CharsetUtils.UTF_8);
    }

    public static String getBasePath(String url) {
        int count = StringUtils.count(url, '/');
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<count; i++) {
            if (sb.length() > 0) {
                sb.append('/');
            }
            sb.append("..");
        }
        return sb.toString();
    }
    
    public static String getMarkdownTitle(String url) {
        String html = htmlGet(url);
        Document document = Jsoup.parse(html);
        Element header = document.select("h1").first();
        return header.text();
    }

}
