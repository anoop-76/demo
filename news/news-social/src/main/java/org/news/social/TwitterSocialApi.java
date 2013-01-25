package org.news.social;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;


public class TwitterSocialApi extends SocialApi{

    private final static String CONSUMER_KEY = "JnRICKn0Oi9FCd4yZ4jWfQ";
    private final static String CONSUMER_KEY_SECRET = "rqpkfE7OtxIQgtW9MntxkpzEl1HKU5yn24p1yB5H7sM";

	public List<Response> search(String[] searchStrs) {
		return null;
	}
	
	public List<Response> search(String searchStr) {
    	List<Response> responses = new ArrayList<Response>();
		Connection conn = ConnectionRepository.getConnection("twitter");
		if(conn == null) {
			System.out.println("No active twitter connection");
			return null;
		}
        try {
        	Twitter twitter = new TwitterFactory().getInstance();
        	setValidAccessToken(twitter, conn);
            Query query = new Query("Nearly 60 are injured in ferry crash in Lower Manhattan");
            QueryResult result;
            do {
            	if(conn.getExpired()) {
            		conn = ConnectionRepository.getConnection("twitter");
            	}
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText() + " - " + tweet.getRetweetCount());
                    Response response = new Response(tweet.getText(), tweet.getRetweetCount(), 0l);
                    responses.add(response);
                }
                conn.updateStats();
                System.out.println("conn" + conn);
            } while ((query = result.nextQuery()) != null);
            
	        /*
	    	Extractor extractor = new Extractor();
	        Paging paging = new Paging(1, 100);
	        List<Status> statuses = twitter.getUserTimeline("ndtv",paging);
	        for (Status status : statuses) {
	            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
	            List<String> urlList = extractor.extractURLs(status.getText());
	            for(String url : urlList) {
	            	System.out.println("Short URL=" + url);
	            	//UrlTools.getLongUrl(url);
	            	//System.out.println("Long URL=" + UrlTools.getLongUrl(url));
	        	}
	        }*/
	        
	        Map<String, RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus();
	        for (String endpoint : rateLimitStatus.keySet()) {
	            RateLimitStatus status = rateLimitStatus.get(endpoint);
	            System.out.println("Endpoint: " + endpoint);
	            System.out.println(" Limit: " + status.getLimit());
	            System.out.println(" Remaining: " + status.getRemaining());
	            //System.out.println(" ResetTimeInSeconds: " + status.getResetTimeInSeconds());
	            //System.out.println(" SecondsUntilReset: " + status.getSecondsUntilReset());
	        }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
        
        return responses;
    }
	
	private void setValidAccessToken(Twitter twitter, Connection conn) {
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
		AccessToken accessToken = new AccessToken(conn.getAccessToken(), conn.getSecret());
		twitter.setOAuthAccessToken(accessToken);
	}
	
}
