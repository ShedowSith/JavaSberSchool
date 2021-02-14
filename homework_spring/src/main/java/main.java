import api.PoolDownloader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.Config;

import java.io.File;

public class main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        PoolDownloader poolDownloader = context.getBean(PoolDownloader.class);
        poolDownloader.setFileURL(new File("D:\\file.txt"));
        poolDownloader.setFolder("D:\\");
        poolDownloader.startLoad();

    }

}
