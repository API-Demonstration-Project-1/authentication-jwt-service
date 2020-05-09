package com.toystore.ecomm.authentication;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisClient {

	public static void main (String args[]) {
	try {
		Jedis jedis = new Jedis("localhost");
		// prints out "Connection Successful" if Java successfully connects to Redis server.
		System.out.println("Connection Successful");
		System.out.println("The server is running " + jedis.ping());
			/*
			 * jedis.set("company-name", "500Rockets.io");
			 * System.out.println("Stored string in redis:: "+ jedis.get("company-name"));
			 */
		Set<String> list =  jedis.keys("*"); 
	      
		;
			
		  for(int i = 0; i<list.size(); i++) {
			  System.out.println("List: " + jedis.get((list.toArray()[i]).toString())); 
		  }
			  
		}catch(Exception e) {
		System.out.println(e);
		}
	}
}
