package jetbrick.website.app;

import java.util.LinkedHashMap;
import java.util.Map;
import jetbrick.lang.StringUtils;
import jetbrick.template.runtime.JetPageContext;
import jetbrick.template.runtime.JetUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class AppFunctions {
 
    private static String autolist(String html) {
        Document document = Jsoup.parse(html);
        Elements headers = document.select("h1,h2,h3,h4,h5,h6");
        int n[] = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        for (Element header : headers) {
            String tag = header.tagName().toLowerCase();
            String text = header.text();
            if ("h1".equals(tag)) {
                header.text((++n[1]) + " " + text);
                n[2] = n[3] = n[4] = n[5] = n[6] = 0;
            } else if ("h2".equals(tag)) {
                header.text((n[1]) + "." + (++n[2]) + " " + text);
                n[3] = n[4] = n[5] = n[6] = 0;
            } else if ("h3".equals(tag)) {
                header.text((n[1]) + "." + (n[2]) + "." + (++n[3]) + " " + text);
                n[4] = n[5] = n[6] = 0;
            } else if ("h4".equals(tag)) {
                header.text((n[1]) + "." + (n[2]) + "." + (n[3]) + "." + (++n[4]) + " " + text);
                n[5] = n[6] = 0;
            } else if ("h5".equals(tag)) {
                header.text((n[1]) + "." + (n[2]) + "." + (n[3]) + "." + (n[4]) + "." + (++n[5]) + " " + text);
                n[6] = 0;
            } else if ("h6".equals(tag)) {
                header.text((n[1]) + "." + (n[2]) + "." + (n[3]) + "." + (n[4]) + "." + (n[5]) + "." + (++n[6]) + " " + text);
            }
            header.html("<a name=\"x" + Math.abs(text.hashCode()) + "\"></a><span>" + header.text() + "</span>");
        }
        return document.body().html();
    }
 
    public static Map<String, String> getHeaders(String html, int level) {
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        Document document = Jsoup.parse(html);
        StringBuilder tags = new StringBuilder("h1");
        if (level > 1) tags.append(",h2");
        if (level > 2) tags.append(",h3");
        if (level > 3) tags.append(",h4");
        if (level > 4) tags.append(",h5");
        if (level > 5) tags.append(",h6");
        Elements headers = document.select(tags.toString());
        for (Element header : headers) {
            String tag = header.tagName().toLowerCase();
            String text = header.child(1).text();
            if ("h1".equals(tag)) {
                // nothing to do
            } else if ("h2".equals(tag)) {
                text = StringUtils.repeat("&nbsp", 2) + text;
            } else if ("h3".equals(tag)) {
                text = StringUtils.repeat("&nbsp", 4) + text;
            } else if ("h4".equals(tag)) {
                text = StringUtils.repeat("&nbsp", 6) + text;
            } else if ("h5".equals(tag)) {
                text = StringUtils.repeat("&nbsp", 8) + text;
            } else if ("h6".equals(tag)) {
                text = StringUtils.repeat("&nbsp", 10) + text;
            }
            resultMap.put("#" + header.child(0).attr("name"), text);
        }
        return resultMap;
    }
    
}
