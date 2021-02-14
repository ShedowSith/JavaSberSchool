package spring;

import api.FileLoader;
import api.PoolDownloader;
import model.FileLoaderImpl;
import model.PoolDownloaderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class Config {
    @Bean
    public FileLoader fileLoader(){
        return new FileLoaderImpl();
    }

    @Bean
    public PoolDownloader poolDownloader(FileLoader fileLoader){
        PoolDownloader downloader = new PoolDownloaderImpl();
        downloader.setFileLoader(fileLoader);
        return downloader;
    }
}
