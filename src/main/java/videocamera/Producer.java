package videocamera;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 25.08.2020
 */
public class Producer {
    private final String url = "http://www.mocky.io/v2/5c51b9dd3400003252129fb5";
    private final Store store = new Store();
    private final ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final CountDownLatch downLatch = new CountDownLatch(4);

    private String readUrl() throws IOException {
        URL link = new URL(url);
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(link.openStream()))) {
            reader.lines()
                    .forEach(joiner::add);
        }
        return joiner.toString();
    }

    public void parseAndPut(String json) {
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            store.add(array.getJSONObject(i));
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Producer producer = new Producer();
        String json = producer.readUrl();
        producer.parseAndPut(json);
        while (!producer.store.isEmpty()) {
            producer.service.execute(new Agregator(producer.store, producer.downLatch));
        }
        producer.downLatch.await();
        producer.service.shutdown();
        System.out.println(producer.store.getResult());
    }
}
