package edu.sjsu.cmpe275.lab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TweetStatsImpl implements TweetStats {

    public void resetStats() {
    		RetryAndDoStats.longestAttempt = 0;
    		RetryAndDoStats.mostFollowMap = new HashMap<String, Set<String>>();
    		RetryAndDoStats.mostProduceMap = new HashMap<String, Integer>();
    }

    
    public int getLengthOfLongestTweetAttempted() {		    			
        return RetryAndDoStats.longestAttempt;
    }

    
    public String getMostFollowedUser() {
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

    
    public String getMostProductiveUser() {
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
   

}



