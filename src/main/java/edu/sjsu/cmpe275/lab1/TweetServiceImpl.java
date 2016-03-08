package edu.sjsu.cmpe275.lab1;

import java.io.IOException;

public class TweetServiceImpl implements TweetService {

    /***
     * Following is the dummy implementation of methods.
     * Students are expected to complete the actual implementation of these methods as part of lab completion.
     */

    public void tweet(String user, String message) throws IllegalArgumentException, IOException {
    		//if message is longer than 140, throw IllegalArgumentException
    		System.out.println("tweet__________" + user + "  " + message + "___________");
    		if(message.length() > 140) {
    			throw new IllegalArgumentException();
    		}
    		
    		//if(RetryAndDoStats.countTry == 1) {
    		//	return;
    		//}
    		
    		if(false) {
    			throw new IOException();
    		}
    }

    public void follow(String follower, String followee) throws IOException {
    		System.out.println("---------" + follower+ "  " + followee + "------------");
    		//if(RetryAndDoStats.countTry == 2) {
    		//	return;
    		//}
    		if(true) {
			throw new IOException();
		}
    }

}
