package filedownloaderwithlimit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Class has realizes download file from network
 *
 * @author Денис Висков
 * @version 1.0
 * @since 29.07.2020
 */
public class FileDownloader implements Download {

    /**
     * URL
     */
    private final URL url;

    /**
     * File out
     */
    private final File out;

    /**
     * Limit byte
     */
    private final int limit;

    public FileDownloader(URL url, File out, int limit) {
        this.url = url;
        this.out = out;
        this.limit = limit;
    }

    /**
     * Method of downloading file from network
     */
    @Override
    public void downloading() {
        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream fileOut = new FileOutputStream(out)) {
            byte[] byteBuffer = new byte[limit];
            int read;
            while ((read = in.read(byteBuffer)) != -1) {
                fileOut.write(byteBuffer);
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
