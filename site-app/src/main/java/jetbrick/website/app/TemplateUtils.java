package jetbrick.website.app;

import java.io.*;
import java.util.Map;
import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;

public final class TemplateUtils {
    private static final JetEngine engine = JetEngine.create();

    public static void render(Map<String, Object> context, String templateName, File outputFile) {
        File dir = outputFile.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            JetTemplate template = engine.getTemplate(templateName);
            OutputStream out = new FileOutputStream(outputFile);
            template.render(context, out);
            out.close();
        } catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
