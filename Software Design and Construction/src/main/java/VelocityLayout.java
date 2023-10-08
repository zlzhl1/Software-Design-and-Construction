import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.util.Date;

public class VelocityLayout extends Layout {
    private Template template;
    private final VelocityEngine velocityEngine;
    private String pattern;


    public VelocityLayout() throws IOException {
        this("$d: [$p] {$c} $m$n");
    }

    public VelocityLayout(String pattern) throws IOException {
        this.pattern = pattern;
        velocityEngine= new VelocityEngine();
        template = new Template();
        setPattern(pattern);
    }

    public void setPattern(String pattern) throws IOException {
        File file = new File("velocityLayout.vm");
        file.deleteOnExit();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(pattern);
        bufferedWriter.close();
        String templatePath = "";
        String templateName = "velocityLayout.vm";
        velocityEngine.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, templatePath);
        template = velocityEngine.getTemplate(templateName);
    }


    @Override
    public String format(LoggingEvent loggingEvent) {
        VelocityContext context = new VelocityContext();
        StringWriter stringWriter = new StringWriter();
        context.put("c", loggingEvent.getLoggerName());
        context.put("d", new Date(loggingEvent.getTimeStamp()));
        context.put("m", loggingEvent.getMessage());
        context.put("p", String.valueOf(loggingEvent.getLevel()));
        context.put("n", "\n");
        context.put("t", loggingEvent.getThreadName());
        template.merge(context, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public boolean ignoresThrowable() {
        return false;
    }

    @Override
    public void activateOptions() {
        // nothing to do.
    }
}
