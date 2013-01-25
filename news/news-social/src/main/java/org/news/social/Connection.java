package org.news.social;

import java.io.*;

public class Connection implements Serializable {

	private String provider;
	private String providerUserId;
	private String accessToken;
	private String secret;
	private String refreshToken;
	private int numOfTimesUsed;
	private Long expireTime;
	private Long firstUsedAt;
	private Long timeLastUsedAt;
	private Long expiredUntil;
	private Boolean expired;
	
	public Connection(String provider, String providerUserId, String accessToken, String secret, String refreshToken, Long expireTime, Boolean expired) {
		this.provider = provider;
		this.providerUserId = providerUserId;
		this.accessToken = accessToken;
		this.secret = secret;
		this.refreshToken = refreshToken;
		this.expireTime = expireTime;
		this.expired = expired;
	}

	public String getProvider() {
		return provider;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getSecret() {
		return secret;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public Long getExpireTime() {
		return expireTime;
	}
	
	public Long getTimeLastUsedAt() {
		return timeLastUsedAt;
	}	
	
	public int getNumOfTimesUsed() {
		return numOfTimesUsed;
	}	
	public void setNumOfTimesUsed(int numOfTimesUsed) {
		numOfTimesUsed = numOfTimesUsed;
	}	

	public Long getFirstUsedAt() {
		return firstUsedAt;
	}	
	public void setFirstUsedAt(Long firstUsedAt) {
		if(firstUsedAt == 0 || expired == false) {
			firstUsedAt = firstUsedAt;
		}
	}	
	
	public void setTimeLastUsedAt(Long timeLastUsedAt) {
		timeLastUsedAt = timeLastUsedAt;
	}	
	
	public void setNumOfTimesUsed(Integer numOfTimesUsed) {
		numOfTimesUsed = numOfTimesUsed;
	}	

	public void updateStats() {
		numOfTimesUsed = numOfTimesUsed + 1;
		if(numOfTimesUsed == 170) {
			setExpired(true);
		}
		if(expired == true && expiredUntil != 0l) {
			if(System.currentTimeMillis() >= expiredUntil) { //reset status as 15 min over for this connection
				setExpired(false);
			}
		}
	}

	public Boolean getExpired() {
		return expired;
	}	

	public void setExpired(Boolean expired) {
		expired = expired;
		if(expired == false) { //reset status. Connection is available for use again
			numOfTimesUsed = 0;
			expiredUntil = 0l;
			timeLastUsedAt = 0l;
		}
		if(expired == true) { //Connection is not available for use
			numOfTimesUsed = 0;
			expiredUntil = System.currentTimeMillis() + 900000; //add 15 min
		}
	}	

	public String toString() {
		return "\n accessToken=" + accessToken + "\n" +
			"secret=" + secret + "\n" + 
			"numOfTimesUsed=" + numOfTimesUsed + "\n" + 
			"expiredUntil=" + expiredUntil + "\n" + 
			"expired=" + expired + "\n"; 
	}
	
}