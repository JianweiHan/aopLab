package edu.sjsu.cmpe275.lab1;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;



public class RetryAndDoStats implements MethodInterceptor {
    /***
     * Following is the dummy implementation of advice.
     * Students are expected to complete the required implementation as part of lab completion.
     */
	//statistic fields, used to record data
	public int countTry = 0;
	public boolean isReTry = false;
	public static int longestAttempt = 0;
	public static HashMap<String, Set<String>> mostFollowMap = new HashMap<String, Set<String>>();
	public static HashMap<String, Integer> mostProduceMap = new HashMap<String, Integer>();
	
	
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Method " + invocation.getMethod() + " is called");
        //return invocation.proceed();
        System.out.println("Method name :" + invocation.getMethod().getName());
        //System.out.println("Method arguments :" + Arrays.toString(invocation.getArguments()));
       // System.out.println("Method arguments :" + invocation.getArguments()[0]);
       // System.out.println("Method arguments :" + invocation.getArguments()[1]);
        
        
        
        String methodName = invocation.getMethod().getName();
        String argFirst = (String) invocation.getArguments()[0];
        String argSecond = (String) invocation.getArguments()[1];
        //System.out.println("Method arguments :" + argFirst + "   "+ argSecond);
        //update longestTweetAttempted
        if(methodName.equals("tweet")) {
        		if(argSecond.length() > longestAttempt) {
        			longestAttempt = argSecond.length();
        		}
        }
        
        Object result = null;
        
        while(countTry == 0 || (isReTry && countTry <= 3)) {
        		if(countTry > 0) {
        			System.out.println("We are trying to re-" + methodName + ".");
        		}
        		doProcess(result, invocation, methodName, argFirst, argSecond);
        		countTry++;
        }
        
        if(isReTry) {
            isReTry = false;
            countTry = 0;
        		throw new IOException();
        }
        
        isReTry = false;
        countTry = 0;
        	
        return result;
    }
    
    private void doProcess(Object result, MethodInvocation invocation, String methodName, String argFirst, String argSecond) throws Throwable {
        try {
    			result = invocation.proceed();
    			//System.out.println("This is after return aop.");
    			
    			if(methodName.equals("follow")) {
    				if(!argFirst.equals(argSecond)) {
    					if(!mostFollowMap.containsKey(argSecond)) {
    						mostFollowMap.put(argSecond, new HashSet<String>());
        				}
    					mostFollowMap.get(argSecond).add(argFirst);
    				}    				
    			}
    			
    			if(methodName.equals("tweet")) {
    				if(!mostProduceMap.containsKey(argFirst)) {
    					mostProduceMap.put(argFirst, 0);
    				}
    				mostProduceMap.put(argFirst, mostProduceMap.get(argFirst) + argSecond.length());
    			}
    			isReTry = false;
    			//while(countTry <= 3) {
    				//countTry++;
    				//result = invocation.proceed();
    				//System.out.println("This is after return aop.");
    				//TweetService tweeter = (TweetService) invocation.getThis();
    				//tweeter.tweet("alex", "first tweet");
    			//}   
        }
        catch(IOException e) {
    			//System.out.println("This is an IOException for " + methodName + " method.");
    			isReTry = true;
        }
        catch(IllegalArgumentException e) {
    			//System.out.println("This is an IllegalArgumentException for tweet method. More than 140 characters");
            isReTry = false;
            countTry = 0;
    			throw e;
        }
    }
    
}
