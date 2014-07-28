package jetbrick.website.app;

import java.io.File;
import jetbrick.lang.SystemUtils;

public final class AppConfig {

    public static final String BASE_PATH;

    public static final String BASE_DOCS_PATH;

    public static final String RUBY_BIN;

    public static final String GFM_SCRIPT_FILE;

    static {

        if (SystemUtils.IS_OS_WINDOWS) {
            // my laptop config
            BASE_PATH = "D:/workspace/jetbrick-framework/jetbrick-website-app";
            RUBY_BIN = "C:/dev/ruby-1.9.3/bin/ruby.exe";
        } else {
            // cloud vm config
            BASE_PATH = "/home/ubuntu/workspace/github-jetbrick-website-app";
            RUBY_BIN = "/usr/local/rvm/rubies/ruby-2.1.1/bin/ruby";
        }

        BASE_DOCS_PATH = new File(BASE_PATH, "site-docs").getAbsolutePath();

        GFM_SCRIPT_FILE = new File(BASE_DOCS_PATH, "scripts/gfm.rb").getAbsolutePath();

    }

}
