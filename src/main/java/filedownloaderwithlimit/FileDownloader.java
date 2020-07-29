package filedownloaderwithlimit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 29.07.2020
 */
public class FileDownloader implements Download {

    private final URL url;
    private final File out;
    private final int limit;

    public FileDownloader(URL url, File out, int limit) {
        this.url = url;
        this.out = out;
        this.limit = limit;
    }

    @Override
    public void downloading() {
        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream fileOut = new FileOutputStream(out)) {
            byte[] byteBuffer = new byte[1024];
            int read;
            while ((read = in.read(byteBuffer)) != -1) {
                pause(read);
                fileOut.write(byteBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pause(int read) {
        if (read > limit) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        System.setProperty("https.proxyHost", "192.168.111.102");
        System.setProperty("https.proxyPort", "3128");
        Download download = new FileDownloader(new URL("https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml"),
                new File("C:\\Users\\vda-it\\Downloads\\Новый текстовый документ.txt"), 200);
        download.downloading();
        System.setProperty("https.proxyHost", null);
    }
}
