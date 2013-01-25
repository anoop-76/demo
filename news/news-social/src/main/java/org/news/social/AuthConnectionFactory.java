package org.news.social;

public class AuthConnectionFactory {

	public AuthService getAuthService(String socialMedia, String accessToken, String accessTokenSecret) {
		if(socialMedia.equalsIgnoreCase("twitter")) {
			return new TwitterAuthService(accessToken, accessTokenSecret);
		}
		else if(socialMedia.equalsIgnoreCase("facebook")) {
			return new FacebookAuthService(accessToken, accessTokenSecret);
		}
		return null;
	}
}
