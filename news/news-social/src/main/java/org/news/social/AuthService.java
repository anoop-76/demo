package org.news.social;

public class AuthService {
	private String socialMedia;
	protected String authToken;
	protected String authTokenURL;
	protected String accessToken;
	protected String accessTokenSecret;
	
	public AuthService(String accessToken, String accessTokenSecret) {
		accessToken = accessToken;
		accessTokenSecret = accessTokenSecret;
	}
	
	public String getAuthToken() {
		return null;
	}
	
	public String getAuthTokenURL() {
		return null;
	}
	
	public Boolean isValidAccessToken(String accessToken) {
		return true;
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}
	
	public String getSocialService() {
		return null;
	}
	
	public void saveAuthToken() {
		//TODO: insert or update?	
	}
	
	
}
