package jetbrick.website.app;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import jetbrick.template.JetAnnotations;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@JetAnnotations.Methods
public final class AppMethods {
    private static final Pattern BLOCKQUOTE_PATTERN = Pattern.compile("<blockquote>\\s*<p>\\[(\\w+)\\]\\s*");
    private static final String BLOCKQUOTE_REPLACEMENT = Matcher.quoteReplacement("<blockquote class=\"$1\">");

    public static String replaceContext(String html) {
        html = html.replace("{{COMMON.VERSION}}", AppConfig.COMMON_VERSION);
        html = html.replace("{{IOC.VERSION}}", AppConfig.IOC_VERSION);
        html = html.replace("{{ORM.VERSION}}", AppConfig.ORM_VERSION);
        html = html.replace("{{WEBMVC.VERSION}}", AppConfig.WEBMVC_VERSION);
        html = html.replace("{{TEMPLATE.VERSION}}", AppConfig.TEMPLATE_VERSION);
        html = html.replace("{{ALL.VERSION}}", AppConfig.ALL_VERSION);

        html = BLOCKQUOTE_PATTERN.matcher(html).replaceAll(BLOCKQUOTE_REPLACEMENT);
        
        return html;
    }

    public static String toPrettyHTML(String html) {
        Document document = Jsoup.parse(html);
        return document.body().html();
    }
}
