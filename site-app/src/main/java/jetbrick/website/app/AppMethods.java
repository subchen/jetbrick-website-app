package jetbrick.website.app;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;
import jetbrick.template.JetAnnotations;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@JetAnnotations.Methods
public final class AppMethods {
    private static final Pattern BLOCKQUOTE_PATTERN = Pattern.compile("<blockquote>\\s*<p>\\[(\\w+)\\]\\s*");
    private static final String BLOCKQUOTE_REPLACEMENT = "<blockquote class=\"$1\"><p>";

    public static String replaceContext(String html) {
        html = replaceVariable(html, "{{COMMONS.VERSION}}", AppConfig.COMMONS_VERSION);
        html = replaceVariable(html, "{{IOC.VERSION}}", AppConfig.IOC_VERSION);
        html = replaceVariable(html, "{{ORM.VERSION}}", AppConfig.ORM_VERSION);
        html = replaceVariable(html, "{{WEBMVC.VERSION}}", AppConfig.WEBMVC_VERSION);
        html = replaceVariable(html, "{{TEMPLATE.VERSION}}", AppConfig.TEMPLATE_VERSION);
        html = replaceVariable(html, "{{ALL.VERSION}}", AppConfig.ALL_VERSION);

        html = BLOCKQUOTE_PATTERN.matcher(html).replaceAll(BLOCKQUOTE_REPLACEMENT);

        return html;
    }

    private static String replaceVariable(String text, String name, String replacement) {
        text = text.replace(name, replacement);
        try {
            text = text.replace(URLEncoder.encode(name, "utf-8"), replacement);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
        return text;
    }

    public static String autolist(String html) {
        Document document = Jsoup.parse(html);
        Elements headers = document.select("h2,h3");
        int n2 = 0, n3 = 0;
        for (Element header : headers) {
            String tag = header.tagName().toLowerCase();
            String text = header.text();
            if ("h2".equals(tag)) {
                text = (++n2) + " " + text;
                n3 = 0;
            } else if ("h3".equals(tag)) {
                text = (n2) + "." + (++n3) + " " + text;
            }
            header.text(text);
        }
        return document.body().html();
    }

    public static String toPrettyHTML(String html) {
        Document document = Jsoup.parse(html);
        return document.body().html();
    }
}
