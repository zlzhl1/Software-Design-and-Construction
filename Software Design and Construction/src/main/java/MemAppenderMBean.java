import java.util.List;

public interface MemAppenderMBean {
    List<String> getEventStrings();
    long getDiscardedLogCount();
    long getSize_cachedLog();
}
