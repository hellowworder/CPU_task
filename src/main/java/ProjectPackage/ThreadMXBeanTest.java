package ProjectPackage;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

//Класс длямониторинга загрузки ЦП

public class ThreadMXBeanTest {
    class UserThread extends Thread {

        @Override
        public String toString() {
            OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

            return Math.round(bean.getSystemCpuLoad() * 100)  + "%";
        }

    }
}
