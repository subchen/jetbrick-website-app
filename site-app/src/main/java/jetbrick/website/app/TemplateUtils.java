package jetbrick.website.app;

import java.util.*;
import java.io.*;
import jetbrick.template.*;

public final class TemplateUtils {
    private static final JetEngine engine;
    private static final JetTemplate template;

    static {
        engine = JetEngine.create();
        engine.getGlobalContext().set(String.class, "WEBROOT_PATH", AppConfig.WEBROOT_PATH);
        
        template = engine.getTemplate("/main.jetx");
    }

    public static void render(Map<String, Object> context, File file) {
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            OutputStream out = new FileOutputStream(file);
            template.render(context, out);
            out.close();
        } catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
