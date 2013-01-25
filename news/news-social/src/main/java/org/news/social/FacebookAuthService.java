package org.news.social;


public class FacebookAuthService extends AuthService {

	public FacebookAuthService(String accessToken, String accessTokenSecret) {
		super(accessToken, accessTokenSecret);
		init();
	}

	private void init() {
	}

}
