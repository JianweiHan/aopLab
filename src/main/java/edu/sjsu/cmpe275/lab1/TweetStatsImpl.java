package edu.sjsu.cmpe275.lab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TweetStatsImpl implements TweetStats {

    /***
     * Following is the dummy implementation of methods.
     * Students are expected to complete the actual implementation of these methods as part of lab completion.
     */

    //@Override
    public void resetStats() {
        // TODO Auto-generated method stub
    		RetryAndDoStats.longestAttempt = 0;
    		RetryAndDoStats.mostFollowMap = new HashMap<String, Set<String>>();
    		RetryAndDoStats.mostProduceMap = new HashMap<String, Integer>();
    }

    //@Override
    public int getLengthOfLongestTweetAttempted() {
        // TODO Auto-generated method stub   		
    			
        return RetryAndDoStats.longestAttempt;
    }

    //@Override
    public String getMostFollowedUser() {
        // TODO Auto-generated method stub
    		if(RetryAndDoStats.mostFollowMap.size() == 0) {
    			return null;
    		}
    		List<String> sortedKeys = new ArrayList(RetryAndDoStats.mostFollowMap.keySet());
    		Collections.sort(sortedKeys);
    		int max = 0;
    		String name = "";
    		for(String keyItem : sortedKeys) {
    			if(RetryAndDoStats.mostFollowMap.get(keyItem).size() > max) {
    				max = RetryAndDoStats.mostFollowMap.get(keyItem).size();
    				name = keyItem;
    			}
    		}
        return name;
    }

    //@Override
    public String getMostProductiveUser() {
        // TODO Auto-generated method stub
    		if(RetryAndDoStats.mostProduceMap.size() == 0) {
    			return null;
    		}
    		List<String> sortedKeys = new ArrayList(RetryAndDoStats.mostProduceMap.keySet());
    		Collections.sort(sortedKeys);
    		int max = -1;
    		String name = "";    		
    		for(String keyItem : sortedKeys) {
    			if(RetryAndDoStats.mostProduceMap.get(keyItem) > max) {
    				max = RetryAndDoStats.mostProduceMap.get(keyItem);
    				name = keyItem;
    			}
    		}
        return name;
    }
    //...

}



