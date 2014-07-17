package jetbrick.website.app;

import java.io.File;
import jetbrick.lang.ShellUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public final class MarkdownUtils {

    public static String toHTML(String fileName, String encoding) {
        //@formatter:off
        ShellUtils.Result result = ShellUtils.execute(
            new File(AppConfig.BASE_DOCS_PATH), null,
            AppConfig.RUBY_BIN, "-Ku", AppConfig.GFM_SCRIPT_FILE, fileName
        );
        //@formatter:on

        if (result.good()) {
            return result.stdout(encoding);
        }

        throw new IllegalStateException(result.toString());
    }

    public static String toPrettyHTML(String html) {
        Document document = Jsoup.parse(html);
        return document.body().html();
    }
}
