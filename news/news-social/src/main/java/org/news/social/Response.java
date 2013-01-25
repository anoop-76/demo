package org.news.social;

public class Response {

	private String text;
	private long retweetCount;
	private long likes;
	
	public Response(String text, long retweetCount, long likes) {
		text = text;
		retweetCount = retweetCount;
		likes = likes;
	}
}
