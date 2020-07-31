package filedownloaderwithlimit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Class has realizes download file from network
 *
 * @author Денис Висков
 * @version 1.0
 * @since 29.07.2020
 */
public class FileDownloader implements Download, Runnable {

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
            while (in.available() != -1) {
                long time = System.currentTimeMillis();
                in.read(byteBuffer);
                fileOut.write(byteBuffer);
                time = System.currentTimeMillis() - time;
                pause(time);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method of sleep thread
     * if read time more than 1 second  current thread sleeping in 1 second
     *
     * @param time
     */
    private void pause(long time) {
        if (time < 1000) {
            try {
                Thread.sleep(1000 - time);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void run() {
        downloading();
    }

    public static void main(String[] args) throws IOException {
        File file = new File("Hi");
        file.createNewFile();
        new FileDownloader(new URL("https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml"),
                file, 200).downloading();
    }
}
