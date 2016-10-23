package org.doctorx.twitteringestion;

/**
 * Created by ragsingh on 19/10/16.
 */
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

import java.util.ArrayList;
import java.util.List;


public class TwitterConnector {
    public static void main(String[] args) throws Exception {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setJSONStoreEnabled(true)
                .setOAuthConsumerKey("WSqyghzuNgkoYoq5AdJ3OGVLh")
                .setOAuthConsumerSecret("LWfyFfqTKwFpB98tiKJScJH3usUOtHQkpORvoo1eXCXrIts0pw")
                .setOAuthAccessToken("3179443656-MMjzePn9plmBrZhjGN0g5HESLift48EhA5kcvlC")
                .setOAuthAccessTokenSecret("YFCl7p3qKw3Eft0TKML7mOUY8Oe3gi0lMB4Qv8l2fbM3G");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Paging paging = null;
        int maxMessagesToRead = 50000;
        Long lastMessageIdRead = 0L;
        String collectorEndpoint = "http://collector.test.expedia.com/sample.json?stream=true&persist=true";
        while (true) {
            List<DirectMessage> dmList = new ArrayList<DirectMessage>();
            CloseableHttpClient client = HttpClientBuilder.create().build();
            try {
                HttpPost request = new HttpPost(collectorEndpoint);
                String jsonData = new String();
                paging = new Paging(1, maxMessagesToRead, lastMessageIdRead);
                dmList = twitter.getDirectMessages(paging);
                for (DirectMessage dm : dmList) {
                    jsonData = jsonData + "\n" + TwitterObjectFactory.getRawJSON(dm);
                }
                StringEntity se = new StringEntity(jsonData);
                request.setHeader("Content-Type", "application/json");
                request.setEntity(se);
                lastMessageIdRead = dmList.get(dmList.size() - 1).getId();
                System.out.println("Hello World!");
            } catch (Throwable th) {
                System.out.println("Reading or posting to collector threw an exception, whole process to be redone" + th);
            } finally {
                client.close();
            }
            Thread.sleep(60000);
        }
    }
}
