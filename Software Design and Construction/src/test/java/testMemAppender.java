import org.apache.log4j.*;
import org.apache.log4j.xml.XMLLayout;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public class testMemAppender {
    private Logger logger = Logger.getLogger(testMemAppender.class);
    private VelocityLayout velocityLayout;
    private MemAppender memAppender;

    @Before
    public void setUp() throws IOException {
        memAppender = MemAppender.getInstance();
        velocityLayout = new VelocityLayout("$d: [$p] {$c} $m$n");
        logger.addAppender(memAppender);
    }

    @Test
    public void TestAppender_velocityLayout_INFO(){
//        logger = Logger.getLogger("Appender_velocityLayout_INFO_Test");

        memAppender.setLayout(velocityLayout);

        logger.setLevel(Level.INFO);
        logger.info("Test function of INFO");
        Assert.assertEquals(velocityLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        memAppender.printLogs();

    }

    @Test
    public void TestAppender_velocityLayout_WARN(){
//        logger = Logger.getLogger("Appender_velocityLayout_WARN_Test");

        memAppender.setLayout(velocityLayout);

        logger.setLevel(Level.WARN);
        logger.warn("Test function of WARN");
        Assert.assertEquals(velocityLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        memAppender.printLogs();

    }

    @Test
    public void TestAppender_velocityLayout_DEBUG(){
//        logger = Logger.getLogger("Appender_velocityLayout_DEBUG_Test");

        memAppender.setLayout(velocityLayout);

        logger.setLevel(Level.DEBUG);
        logger.debug("Test function of DEBUG");
        Assert.assertEquals(velocityLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        memAppender.printLogs();

    }

    @Test
    public void TestAppender_velocityLayout_ERROR(){
//        logger = Logger.getLogger("Appender_velocityLayout_ERROR_Test");

        memAppender.setLayout(velocityLayout);

        logger.setLevel(Level.ERROR);
        logger.error("Test function of ERROR");
        Assert.assertEquals(velocityLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        memAppender.printLogs();

    }

    @Test
    public void TestAppender_velocityLayout_TRACE(){
//        logger = Logger.getLogger("Appender_velocityLayout_TRACE_Test");

        memAppender.setLayout(velocityLayout);

        logger.setLevel(Level.TRACE);
        logger.trace("Test function of TRACE");
        Assert.assertEquals(velocityLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        memAppender.printLogs();

    }

    @Test
    public void TestAppender_htmlLayout(){
//        logger = Logger.getLogger("Appender_htmlLayout_Test");

        HTMLLayout htmlLayout = new HTMLLayout();
        memAppender.setLayout(htmlLayout);

        logger.setLevel(Level.INFO);
        logger.info("Test function of INFO");
        Assert.assertEquals(htmlLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        memAppender.printLogs();

    }

    @Test
    public void TestAppender_PatternLayout(){
//        logger = Logger.getLogger("Appender_PatternLayout_Test");

        PatternLayout patternLayout = new PatternLayout();
        memAppender.setLayout(patternLayout);

        logger.setLevel(Level.INFO);
        logger.info("Test function of INFO");
        Assert.assertEquals(patternLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        memAppender.printLogs();

    }

    @Test
    public void TestAppender_simpleLayout(){
//        logger = Logger.getLogger("Appender_simpleLayout_Test");

        SimpleLayout simpleLayout = new SimpleLayout();
        memAppender.setLayout(simpleLayout);

        logger.setLevel(Level.INFO);
        logger.info("Test function of INFO");
        Assert.assertEquals(simpleLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        memAppender.printLogs();

    }

    @Test
    public void TestAppender_ttccLayout(){
//        logger = Logger.getLogger("Appender_ttccLayout_Test");

        TTCCLayout ttccLayout = new TTCCLayout();
        memAppender.setLayout(ttccLayout);

        logger.setLevel(Level.INFO);
        logger.info("Test function of INFO");
        Assert.assertEquals(ttccLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        memAppender.printLogs();

    }

    @Test
    public void TestAppender_xmlLayout(){
//        logger = Logger.getLogger("Appender_xmlLayout_Test");

        XMLLayout xmlLayout = new XMLLayout();
        memAppender.setLayout(xmlLayout);

        logger.setLevel(Level.INFO);
        logger.info("Test function of INFO");
        Assert.assertEquals(xmlLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        memAppender.printLogs();

    }

    @Test
    public void TestgetEventStrings () {
//        logger = Logger.getLogger("getEventStrings_Test");
        memAppender.setLayout(velocityLayout);
        logger.info("Test getEventStrings");
        logger.warn("Test getEventStrings");
        logger.error("Test getEventStrings");
        logger.debug("Test getEventStrings");
        Assert.assertEquals(velocityLayout.format(memAppender.getCurrentLogs().get(0)), memAppender.getEventStrings().get(0));
        Assert.assertEquals(velocityLayout.format(memAppender.getCurrentLogs().get(1)), memAppender.getEventStrings().get(1));
        Assert.assertEquals(velocityLayout.format(memAppender.getCurrentLogs().get(2)), memAppender.getEventStrings().get(2));
        Assert.assertEquals(velocityLayout.format(memAppender.getCurrentLogs().get(3)), memAppender.getEventStrings().get(3));
        memAppender.printLogs();

    }
}
