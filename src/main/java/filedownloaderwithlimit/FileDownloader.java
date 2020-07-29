package filedownloaderwithlimit;

import java.io.File;
import java.net.URL;

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

    }
}
