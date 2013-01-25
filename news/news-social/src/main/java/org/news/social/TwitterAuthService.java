package org.news.social;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.TwitterException;

public class TwitterAuthService extends AuthService {

	private final static String CONSUMER_KEY = "JnRICKn0Oi9FCd4yZ4jWfQ";
    private final static String CONSUMER_KEY_SECRET = "rqpkfE7OtxIQgtW9MntxkpzEl1HKU5yn24p1yB5H7sM";

    Twitter twitter = new TwitterFactory().getInstance();
    RequestToken requestToken = null;
	public TwitterAuthService(String accessToken, String accessTokenSecret) {
		super(accessToken, accessTokenSecret);
		if(accessToken != null) {
			init(true);
		}
		else{
			init(false);
		}
	}
	
	private void init(Boolean hasAccessToken) {
		try{ 
		    twitter = new TwitterFactory().getInstance();
		    twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
		    
		    if(hasAccessToken) {
		    	AccessToken accessToken1 = new AccessToken(accessToken, accessTokenSecret);
		    	twitter.setOAuthAccessToken(accessToken1);
		    }
		    else{
		    	requestToken = twitter.getOAuthRequestToken();
		    	this.authTokenURL = requestToken.getAuthorizationURL();
		    	System.out.println("Authorization URL: \n" + requestToken.getAuthorizationURL());
		    	
		    }
		}
		catch(TwitterException te) {
			te.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Boolean isValidAccessToken() {
		return true;
	}
	
	public String getAuthToken() {
		return null;
	}
	
	public String getAccessToken(String pin) {
		try{
			AccessToken accessToken1 = twitter.getOAuthAccessToken(requestToken, pin);
			accessToken = accessToken1.getToken();
			accessTokenSecret = accessToken1.getTokenSecret();
			return accessToken;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getAuthTokenURL() {
		return null;
	}
	
}