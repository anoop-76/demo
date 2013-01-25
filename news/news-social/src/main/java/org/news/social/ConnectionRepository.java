package org.news.social;

import java.util.*;

import org.news.social.util.LruCache;

public class ConnectionRepository {
    
	private static int CACHE_SIZE = 5;
	private static Map<String, Connection> tokenCache = Collections.synchronizedMap(new LruCache<String, Connection>(CACHE_SIZE));
	
	//Will persist connections(tokens)
	public static void buildRepository() {
		//provider, String providerUserId, String accessToken, String secret, String refreshToken, Long expireTime
		Connection conn1 = new Connection("twitter", "twitter", "DDD-SmNJz7wnoLN7a2eyHrmNzrfhePErbJegnu3yTCy", "hMOd0EEcrp62YNZjEpd7KmJrcXQse2p7IGDIewk", "", System.currentTimeMillis(), false);
		Connection conn2 = new Connection("twitter", "twitter", "EEE-SmNJz7wnoLN7a2eyHrmNzrfhePErbJegnu3yTCy", "hMOd0EEcrp62YNZjEpd7KmJrcXQse2p7IGDIewk", "", System.currentTimeMillis(), false);
		Connection conn3 = new Connection("twitter", "twitter", "1077432260-SmNJz7wnoLN7a2eyHrmNzrfhePErbJegnu3yTCy", "hMOd0EEcrp62YNZjEpd7KmJrcXQse2p7IGDIewk", "", System.currentTimeMillis(), false);
		Connection conn4 = new Connection("twitter", "twitter", "239099429-6H3hrUfCRW7AnuaguzKNUXbX2QHmneHlqpNPyhxL", "OaIKqTYze7lgGSOrQk9SP01RiXBngX2sEcNo6U3Qc", "", System.currentTimeMillis(), false);
		Connection conn5 = new Connection("twitter", "twitter", "AAA-SmNJz7wnoLN7a2eyHrmNzrfhePErbJegnu3yTCy", "hMOd0EEcrp62YNZjEpd7KmJrcXQse2p7IGDIewk", "", System.currentTimeMillis(), false);
		Connection conn6 = new Connection("twitter", "twitter", "BBB-SmNJz7wnoLN7a2eyHrmNzrfhePErbJegnu3yTCy", "hMOd0EEcrp62YNZjEpd7KmJrcXQse2p7IGDIewk", "", System.currentTimeMillis(), false);
		Connection conn7 = new Connection("twitter", "twitter", "CCC-SmNJz7wnoLN7a2eyHrmNzrfhePErbJegnu3yTCy", "hMOd0EEcrp62YNZjEpd7KmJrcXQse2p7IGDIewk", "", System.currentTimeMillis(), false);
		
		tokenCache.put("AAA-SmNJz7wnoLN7a2eyHrmNzrfhePErbJegnu3yTCy", conn1);
		tokenCache.put("BBB-SmNJz7wnoLN7a2eyHrmNzrfhePErbJegnu3yTCy", conn2);
		tokenCache.put("1077432260-SmNJz7wnoLN7a2eyHrmNzrfhePErbJegnu3yTCy", conn3);
		tokenCache.put("239099429-6H3hrUfCRW7AnuaguzKNUXbX2QHmneHlqpNPyhxL", conn4);
		tokenCache.put("CCC-SmNJz7wnoLN7a2eyHrmNzrfhePErbJegnu3yTCy", conn5);
		tokenCache.put("DDD-SmNJz7wnoLN7a2eyHrmNzrfhePErbJegnu3yTCy", conn6);
		tokenCache.put("EEE-SmNJz7wnoLN7a2eyHrmNzrfhePErbJegnu3yTCy", conn7);
		
		System.out.println("tokenCache=====\n" + tokenCache);
	}
	
	public static Map<String, Connection> findAllConnections(String provider) {
		return tokenCache;
	}
	
	public static Connection getConnection(String provider) {
		for (Connection conn: tokenCache.values()) {
			System.out.println("conn>>>" + conn);
		    if(!conn.getExpired()) {
		    	return conn;
		    }
		    //TODO: this
		}
		return null;
	}
	
	public static void addConnection(Connection connection) {
		
	}
	public static void updateConnection(Connection connection) {
		connection.updateStats();
	}
	public static void removeConnections(String provider) {
		
	}
	public static void removeConnection(Connection connection) {
		
	}
}