import java.util.ArrayList;
import java.util.Collection;

/**
 * Запускать с JVM опциями в IDEA "Edit_Configuration->VM Options":
 *  -Xms100m -Xmx100m -verbose:gc -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCDetails -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCTimeStamps -XX:+UseSerialGC
 * Подключиться с помощью VisualVM, установив плагин VisualGC
 */
public class FullGcMain {

    private static final Collection<Object> collection = new ArrayList();
    private static volatile Object obj;

    public static void main(String[] args) throws InterruptedException {
        while(true) {
            try {
//                Подбирать значение  паузы под свое железо
                Thread.sleep(500);
//                Раскомментирова, получим накопление ссылок на объекты и затем OutOfMemoryError
                collection.add(new byte[1024 * 1024]);

                obj = new byte[1024 * 1024];
            } catch(OutOfMemoryError e) {
                e.printStackTrace();
                collection.clear();
            }
        }
    }
}
