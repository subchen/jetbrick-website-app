package jetbrick.website.app;

import java.io.File;
import jetbrick.io.IoUtils;
import jetbrick.template.JetAnnotations;
import jetbrick.util.CharsetUtils;

@JetAnnotations.Functions
public final class AppFunctions {

    public static String htmlGet(String location) {
        File file = new File(AppConfig.MARKDOWN_HTML_DIR, location);
        return IoUtils.toString(file, CharsetUtils.UTF_8);
    }

}
