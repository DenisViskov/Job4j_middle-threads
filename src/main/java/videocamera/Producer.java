package videocamera;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringJoiner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class is a Producer
 *
 * @author Денис Висков
 * @version 1.0
 * @since 25.08.2020
 */
public class Producer {
    /**
     * Source URL
     */
    private final String url = "http://www.mocky.io/v2/5c51b9dd3400003252129fb5";
    /**
     * Store
     */
    private final Store store = new Store();
    /**
     * ExecutorService
     */
    private final ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    /**
     * Counter
     */
    private final CountDownLatch downLatch = new CountDownLatch(Runtime.getRuntime().availableProcessors());

    /**
     * Method of reading URL and return JSONArray
     *
     * @return
     * @throws IOException
     */
    private JSONArray readUrl() throws IOException {
        URL link = new URL(url);
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(link.openStream()))) {
            reader.lines()
                    .forEach(joiner::add);
        }
        return new JSONArray(joiner.toString());
    }

    /**
     * Method of put JSON from source to store
     *
     * @param array
     */
    public void putToStore(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            store.add(array.getJSONObject(i));
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("http.proxyHost", "192.168.111.102");
        System.setProperty("http.proxyPort", "3128");
        Producer producer = new Producer();
        producer.putToStore(producer.readUrl());
        while (!producer.store.isEmpty()) {
            producer.service.execute(new Aggregator(producer.store, producer.downLatch));
            Thread.sleep(500);
        }
        producer.downLatch.await();
        producer.service.shutdown();
        System.out.println(producer.store.getResult());
    }
}
