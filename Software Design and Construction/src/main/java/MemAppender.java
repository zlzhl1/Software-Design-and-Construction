import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MemAppender extends AppenderSkeleton implements MemAppenderMBean{

    protected final int DEFAULT_SIZE = 999;
    protected List<LoggingEvent> logEvents;
    protected long discardedCount = 0;

    protected int maxSize;

    private MemAppender (){
        maxSize = DEFAULT_SIZE;
        logEvents = new ArrayList<>();
    }

    @Override
    public void close() {
        printLogs();
        logEvents.clear();
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    private static class SingletonHolder {
        private static MemAppender INSTANCE = new MemAppender();
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        if (logEvents.size() + 1 > maxSize){
            discardedCount++;
            logEvents.remove(0);
        }
        logEvents.add(loggingEvent);
    }

    public static MemAppender getInstance() {
            return SingletonHolder.INSTANCE;
        }
    private void reset() {
        SingletonHolder.INSTANCE = null;
    }

    public List<LoggingEvent> getCurrentLogs() {
        return Collections.unmodifiableList(logEvents);
    }
    public List<String> getEventStrings(){
        List<String> EventStrings = new ArrayList<>();
        if(layout == null){
            LogLog.error("Please set the layout before calling 'getEventStrings()' method.");
            System.exit(1);
        }else {
            for (LoggingEvent currentLog : logEvents) {
                EventStrings.add(layout.format(currentLog));
            }
        }
        return Collections.unmodifiableList(EventStrings);
    }
    public void  printLogs(){
        if(layout == null){
            System.out.println("Please set the layout before calling 'printLogs()' method.");
            System.exit(1);
        }
        else{
            for(LoggingEvent loggingEvent:logEvents){
                System.out.println(layout.format(loggingEvent));
            }
        }
        logEvents.clear();
    }
    public long getDiscardedLogCount() {
        return discardedCount;
    }

    @Override
    public long getSize_cachedLog() {
        return 0;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setLinkedList(){
        maxSize = DEFAULT_SIZE;
        discardedCount = 0;
        logEvents = new LinkedList<>();
    }
    public void setArrayList(){
        maxSize = DEFAULT_SIZE;
        discardedCount = 0;
        logEvents = new ArrayList<>();
    }
}





























































