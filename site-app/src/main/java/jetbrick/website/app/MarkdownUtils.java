package jetbrick.website.app;

import java.io.*;
import java.util.Properties;
import jetbrick.io.*;
import jetbrick.lang.*;
import jetbrick.template.JetEngine;
import jetbrick.template.JetConfig;
import jetbrick.template.resource.loader.ClasspathResourceLoader;

public final class MarkdownUtils {

    public static String toHTML(String fileName, String encoding) {

        ShellUtils.Result result = ShellUtils.execute(
            AppConfig.BASE_DOCS_PATH, null,
            AppConfig.RUBY_BIN, "-Ku", AppConfig.GFM_SCRIPT_FILE, fileName
        );

        if (result.good()) {
            return result.stdout(encoding);
        }

        throw new IllegalStateException(result.toString());
    }

}
