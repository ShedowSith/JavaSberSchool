package model;

import api.FileLoader;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.io.FileUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileLoaderImpl implements FileLoader {
    @Override
    public void loadFile(URL url, String fileName) {
        final RateLimiter throttler = RateLimiter.create(500 * FileUtils.ONE_KB);
        try(InputStream in = new ThrottlingInputStream(url.openStream(), throttler); FileOutputStream out = new FileOutputStream(fileName)) {
            byte buffer[] = new byte[1024];
            while(in.read(buffer) != -1) {
                out.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
