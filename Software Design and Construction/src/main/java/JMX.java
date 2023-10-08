import javax.management.*;
import java.lang.management.ManagementFactory;

public class JMX {

    public static void main(String[] args) throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedObjectNameException
    {
        MBeanServer server=ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName=new ObjectName("jmx:type=MemAppender");
        MemAppender memAppender=MemAppender.getInstance();
        server.registerMBean(memAppender,objectName);
    }
}