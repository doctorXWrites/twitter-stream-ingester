package org.doctorx.twitteringestion;

/**
 * Created by ragsingh on 19/10/16.
 */
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterConnector {
    public static void main(String[] args) throws Exception {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("WSqyghzuNgkoYoq5AdJ3OGVLh")
                .setOAuthConsumerSecret("LWfyFfqTKwFpB98tiKJScJH3usUOtHQkpORvoo1eXCXrIts0pw")
                .setOAuthAccessToken("3179443656-MMjzePn9plmBrZhjGN0g5HESLift48EhA5kcvlC")
                .setOAuthAccessTokenSecret("YFCl7p3qKw3Eft0TKML7mOUY8Oe3gi0lMB4Qv8l2fbM3G");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        System.out.println("Hello World! " + twitter.getDirectMessages());
    }
}
