package videocamera;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringJoiner;
import java.util.concurrent.CountDownLatch;

/**
 * Class is an Agregator
 *
 * @author Денис Висков
 * @version 1.0
 * @since 25.08.2020
 */
public class Aggregator implements Runnable {
    /**
     * Store
     */
    private final Store store;

    /**
     * CountDownLatch
     */
    private final CountDownLatch latch;

    public Aggregator(Store store, CountDownLatch latch) {
        this.store = store;
        this.latch = latch;
    }

    /**
     * Method reading url and return JSONObject
     *
     * @param url
     * @return JSONObject
     */
    private JSONObject readUrl(String url) {
        URL link = null;
        try {
            link = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(link.openStream()))) {
            reader.lines()
                    .forEach(joiner::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(joiner.toString());
    }

    @Override
    public void run() {
        JSONObject jsonObject = store.get();
        JSONObject sourceDataUrl = readUrl(jsonObject.getString("sourceDataUrl"));
        JSONObject tokenDataUrl = readUrl(jsonObject.getString("tokenDataUrl"));
        JSONObject result = new JSONObject();
        result.put("id", jsonObject.getInt("id"));
        result.put("urlType", sourceDataUrl.getString("urlType"));
        result.put("videoUrl", sourceDataUrl.getString("videoUrl"));
        result.put("value", tokenDataUrl.getString("value"));
        result.put("ttl", tokenDataUrl.getInt("ttl"));
        store.addToResult(result);
        latch.countDown();
    }
}
