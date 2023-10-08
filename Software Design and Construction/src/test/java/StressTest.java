import org.apache.log4j.*;
import org.apache.log4j.xml.XMLLayout;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class StressTest {
    private Logger logger;
    private VelocityLayout velocityLayout = new VelocityLayout("$d: [$p] {$c} $m$n");
    private MemAppender memAppender = MemAppender.getInstance();
    private int logCount,maxsize;

    public  StressTest(double maxsize, double logCount) throws IOException {
        this.maxsize = (int) maxsize;
        this.logCount = (int) logCount;
    }
    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] { {100, 10}, {1000, 1000} ,{100000, 1000000}});
    }

    @Before
    public void setUp() throws IOException {
        memAppender.setLayout(velocityLayout);
        memAppender.setMaxSize(maxsize);
    }

    @Test
    public void TestLinkedList() throws InterruptedException {
        logger = Logger.getLogger("LinkedList_Test");

        logger.addAppender(memAppender);
        memAppender.setLinkedList();
        for(int i = 0; i < logCount; i ++){
            logger.info("StressTest");
            logger.warn("StressTest");
            logger.debug("StressTest");
            logger.error("StressTest");
            if(i % maxsize == 0) memAppender.printLogs();
        }
        memAppender.printLogs();
        logger.removeAllAppenders();
        Thread.sleep(5000);
    }

    @Test
    public void TestArrayList() throws InterruptedException {
        logger = Logger.getLogger("ArrayList_Test");

        logger.addAppender(memAppender);
        memAppender.setArrayList();
        for(int i = 0;i < logCount; i ++){
            logger.info("StressTest");
            logger.warn("StressTest");
            logger.debug("StressTest");
            logger.error("StressTest");
            if(i % maxsize == 0) memAppender.printLogs();
        }
        memAppender.printLogs();
        logger.removeAllAppenders();
        Thread.sleep(5000);

    }

    @Test
    public void TestConsoleAppender() throws InterruptedException {
        logger = Logger.getLogger("ConsoleAppender_Test");
        ConsoleAppender consoleAppender;
        consoleAppender = new ConsoleAppender(velocityLayout);

        logger.addAppender(consoleAppender);
        for(int i = 0;i < logCount; i ++){
            logger.info("StressTest");
            logger.warn("StressTest");
            logger.debug("StressTest");
            logger.error("StressTest");
        }
        logger.removeAllAppenders();
        Thread.sleep(5000);

    }
    @Test
    public void TestFileAppender() throws IOException, InterruptedException {
        logger = Logger.getLogger("FileAppender_Test");
        FileAppender fileAppender;
        fileAppender = new FileAppender(velocityLayout,"StressTest.txt");

        logger.addAppender(fileAppender);
        for(int i = 0; i < logCount; i ++){
            logger.info("StressTest");
            logger.warn("StressTest");
            logger.debug("StressTest");
            logger.error("StressTest");
        }
        logger.removeAllAppenders();
        Thread.sleep(5000);

    }
    @Test
    public void TestPatternLayout() throws InterruptedException {
        PatternLayout patternLayout = new PatternLayout();

        logger = Logger.getLogger("PatternLayout_Test");

        logger.addAppender(memAppender);
        memAppender.setLayout(patternLayout);
        for(int i = 0;i < logCount; i ++){
            logger.info("StressTest");
            logger.warn("StressTest");
            logger.debug("StressTest");
            logger.error("StressTest");
        }
        logger.removeAllAppenders();
        Thread.sleep(5000);

    }
    @Test
    public void TestVelocityLayout() throws InterruptedException {
        logger = Logger.getLogger("VelocityLayout_Test");

        logger.addAppender(memAppender);
        memAppender.setLayout(velocityLayout);
        for(int i = 0;i < logCount; i ++){
            logger.info("StressTest");
            logger.warn("StressTest");
            logger.debug("StressTest");
            logger.error("StressTest");
        }
        logger.removeAllAppenders();
        Thread.sleep(5000);

    }

    @Test
    public void TestHTMLLayout() throws InterruptedException {
        HTMLLayout htmlLayout = new HTMLLayout();

        logger = Logger.getLogger("HTMLLayout_Test");

        logger.addAppender(memAppender);
        memAppender.setLayout(htmlLayout);
        for(int i = 0;i < logCount; i ++){
            logger.info("StressTest");
            logger.warn("StressTest");
            logger.debug("StressTest");
            logger.error("StressTest");
        }
        logger.removeAllAppenders();
        Thread.sleep(5000);

    }

    @Test
    public void TestSimpleLayout() throws InterruptedException {
        SimpleLayout simpleLayout = new SimpleLayout();

        logger = Logger.getLogger("SimpleLayout_Test");

        logger.addAppender(memAppender);
        memAppender.setLayout(simpleLayout);
        for(int i = 0;i < logCount; i ++){
            logger.info("StressTest");
            logger.warn("StressTest");
            logger.debug("StressTest");
            logger.error("StressTest");
        }
        logger.removeAllAppenders();
        Thread.sleep(5000);

    }

    @Test
    public void TestTTCCLayout() throws InterruptedException {
        TTCCLayout ttccLayout = new TTCCLayout();

        logger = Logger.getLogger("TTCCLayout_Test");

        logger.addAppender(memAppender);
        memAppender.setLayout(ttccLayout);
        for(int i = 0;i < logCount; i ++){
            logger.info("StressTest");
            logger.warn("StressTest");
            logger.debug("StressTest");
            logger.error("StressTest");
        }
        logger.removeAllAppenders();
        Thread.sleep(5000);

    }

    @Test
    public void TestXMLLayout() throws InterruptedException {
        XMLLayout xmlLayout = new XMLLayout();

        logger = Logger.getLogger("XMLLayout_Test");

        logger.addAppender(memAppender);
        memAppender.setLayout(xmlLayout);
        for(int i = 0;i < logCount; i ++){
            logger.info("StressTest");
            logger.warn("StressTest");
            logger.debug("StressTest");
            logger.error("StressTest");
        }
        logger.removeAllAppenders();
        Thread.sleep(5000);

    }
}
