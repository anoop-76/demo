package org.news.social;

import java.util.List;

import org.news.social.*;

public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwitterSocialApi api = new TwitterSocialApi();
		ConnectionRepository.buildRepository();
		String searchStr = "";
		List<Response> responses = api.search(searchStr);
		System.out.println("responses>>>>" + responses);
		
	}

}
