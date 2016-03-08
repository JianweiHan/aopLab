package edu.sjsu.cmpe275.lab1;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;



public class RetryAndDoStats implements MethodInterceptor {

	//statistic fields, used to record data
	public int countTry = 0;
	public boolean isReTry = false;
	public static int longestAttempt = 0;
	public static HashMap<String, Set<String>> mostFollowMap = new HashMap<String, Set<String>>();
	public static HashMap<String, Integer> mostProduceMap = new HashMap<String, Integer>();
	
	
    public Object invoke(MethodInvocation invocation) throws Throwable {
                     
        //get method's information. For example, name(e.g. tweet or follow), and arguments
        String methodName = invocation.getMethod().getName();
        String argFirst = (String) invocation.getArguments()[0];
        String argSecond = (String) invocation.getArguments()[1];

        //update longest attempt if message length is longer than previous ones
        if(methodName.equals("tweet")) {
        		if(argSecond.length() > longestAttempt) {
        			longestAttempt = argSecond.length();
        		}
        }
        
        Object result = null;
        
        //try to proceed the tweet or follow method, for max 3 times retry
        while(countTry == 0 || (isReTry && countTry <= 3)) {
        		doProcess(result, invocation, methodName, argFirst, argSecond);
        		countTry++;
        }        
        
        isReTry = false;
        countTry = 0;
        	
        return result;
    }
    
    // try to proceed the method, or catch the IOException and IllegalArgumentException exception 
    private void doProcess(Object result, MethodInvocation invocation, String methodName, String argFirst, String argSecond) throws Throwable {
        try {
    			result = invocation.proceed();
    			
    			// if method is follow, update mostFollowMap, in which key is followee and value is follower list.
    			if(methodName.equals("follow")) {
    				if(!argFirst.equals(argSecond)) {
    					if(!mostFollowMap.containsKey(argSecond)) {
    						mostFollowMap.put(argSecond, new HashSet<String>());
        				}
    					mostFollowMap.get(argSecond).add(argFirst);
    				}    				
    			}
    			
    			//if method is tweet, update mostProduceMap, in which key is user and value is the total successful messages length.
    			if(methodName.equals("tweet")) {
    				if(!mostProduceMap.containsKey(argFirst)) {
    					mostProduceMap.put(argFirst, 0);
    				}
    				mostProduceMap.put(argFirst, mostProduceMap.get(argFirst) + argSecond.length());
    			}
    			isReTry = false;  
        }
        catch(IOException e) {
    			isReTry = true;
    			
    			 // if have retried 3 times throw IOExcepton
    			if(countTry == 3) {
        		    isReTry = false;
                countTry = 0;
                throw e;
    			}
        }
        catch(IllegalArgumentException e) {
            isReTry = false;
            countTry = 0;
    			throw e;
        }
    }
    
}
