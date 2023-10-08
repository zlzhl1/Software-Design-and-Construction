import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.velocity.VelocityContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;


public class testVelocityLayout {

    private Logger logger;
    private VelocityLayout velocityLayout;

    @BeforeEach
    public void setUp() throws IOException {
        velocityLayout = new VelocityLayout("$d: [$p] {$c} $m$n");
    }
    @Test
    public void TestLayout_ConsoleAppender (){
        logger = Logger.getLogger("Layout_ConsoleAppender_Test");
        logger.addAppender(new ConsoleAppender(velocityLayout));
        logger.info("Test VelocityLayout with ConsoleAppender");
        logger.warn("Test VelocityLayout with ConsoleAppender");
        logger.error("Test VelocityLayout with ConsoleAppender");
        logger.debug("Test VelocityLayout with ConsoleAppender");
    }


    @Test
    public void TestLayout_MemAppender (){
        logger = Logger.getLogger("Layout_MemAppender_Test");
        MemAppender memAppender = MemAppender.getInstance();
        memAppender.setLayout(velocityLayout);
        logger.addAppender(memAppender);

        logger.info("Test VelocityLayout with MemAppender");
        Assert.assertEquals(velocityLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        memAppender.printLogs();

        logger.warn("Test VelocityLayout with MemAppender");
        Assert.assertEquals(velocityLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        memAppender.printLogs();

        logger.error("Test VelocityLayout with MemAppender");
        Assert.assertEquals(velocityLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        memAppender.printLogs();

        logger.debug("Test VelocityLayout with MemAppender");
        Assert.assertEquals(velocityLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        memAppender.printLogs();


    }

    @Test
    public void TestLayout_FileAppender () throws IOException {
        logger = Logger.getLogger("Layout_FileAppender_Test");
        File file = File.createTempFile("Temp",".txt");
        file.deleteOnExit();

        FileAppender fileAppender = new FileAppender(velocityLayout, "Temp.txt");
        logger.addAppender(fileAppender);

        logger.info("Test VelocityLayout with FileAppender");
        logger.warn("Test VelocityLayout with FileAppender");
        logger.error("Test VelocityLayout with FileAppender");
        logger.debug("Test VelocityLayout with FileAppender");

        BufferedReader bufferedReader = new BufferedReader(new FileReader("Temp.txt"));
        String string = bufferedReader.readLine();
        Assert.assertEquals(new Date() + ": [INFO] {Layout_FileAppender_Test} Test VelocityLayout with FileAppender",string);
        string = bufferedReader.readLine();
        Assert.assertEquals(new Date() + ": [WARN] {Layout_FileAppender_Test} Test VelocityLayout with FileAppender",string);
        string = bufferedReader.readLine();
        Assert.assertEquals(new Date() + ": [ERROR] {Layout_FileAppender_Test} Test VelocityLayout with FileAppender",string);
        string = bufferedReader.readLine();
        Assert.assertEquals(new Date() + ": [DEBUG] {Layout_FileAppender_Test} Test VelocityLayout with FileAppender",string);


    }


    @Test
    public void Testformat() throws IOException {
        VelocityLayout velocityLayout = new VelocityLayout();
        Logger logger = Logger.getLogger("Test");
        MemAppender memAppender = MemAppender.getInstance();
        memAppender.setLayout(velocityLayout);
        logger.addAppender(memAppender);
        velocityLayout.setPattern("");
    }
}
