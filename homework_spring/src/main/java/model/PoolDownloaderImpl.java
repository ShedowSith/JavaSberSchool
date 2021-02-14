package model;

import api.FileLoader;
import api.PoolDownloader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class PoolDownloaderImpl implements PoolDownloader {
    private FileLoader fileLoader;
    private File fileURL;
    private String folder;

    public PoolDownloaderImpl() {
    }

    public PoolDownloaderImpl(FileLoader fileLoader, File fileURL, String folder) {
        this.fileLoader = fileLoader;
        this.fileURL = fileURL;
        this.folder = folder;
    }

    @Override
    public void setFileLoader(FileLoader loader) {
        this.fileLoader = loader;
    }

    @Override
    public void setFileURL(File fileURL) {
        this.fileURL = fileURL;
    }

    public void startLoad(){
        ExecutorService executor;
        String[] list;
        executor = Executors.newFixedThreadPool(3);
        try (Stream<String> linesStream = Files.lines(fileURL.toPath())) {
            list = linesStream.toArray(String[]::new);
            CountDownLatch latch = new CountDownLatch(list.length);
            Arrays.stream(list).forEach(line -> executor.execute(() -> {
                try {
                    String[] name = line.split("/");
                    fileLoader.loadFile(new URL(line), folder+File.separator+name[name.length-1]);
                    latch.countDown();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }));
            latch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }

    @Override
    public void setFolder(String folder) {
        this.folder = folder;
    }
}
