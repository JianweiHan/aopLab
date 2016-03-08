package edu.sjsu.cmpe275.lab1;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        /***
         * Following is the dummy implementation of App to demonstrate bean creation with Application context.
         * Students may alter the following code as required.
         */

        ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetServiceProxy");
        TweetStats tweetStats = (TweetStats) ctx.getBean("tweetStats");

        ArrayList<ArrayList<String>> inputTweet = new ArrayList<ArrayList<String>>();
        
        inputTweet.add(new ArrayList<String>(Arrays.asList("alex", "This is my first tweet!")));
        inputTweet.add(new ArrayList<String>(Arrays.asList("alex", "This is my second tweet")));
        inputTweet.add(new ArrayList<String>(Arrays.asList("bob", "+01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789")));
        inputTweet.add(new ArrayList<String>(Arrays.asList("bob", "Hello world!")));
        inputTweet.add(new ArrayList<String>(Arrays.asList("bob", "This is great!")));
        inputTweet.add(new ArrayList<String>(Arrays.asList("pippen", "Let's play!")));
        
        ArrayList<ArrayList<String>> inputFollow = new ArrayList<ArrayList<String>>();
        inputFollow.add(new ArrayList<String>(Arrays.asList("abc", "a")));
        inputFollow.add(new ArrayList<String>(Arrays.asList("alex", "alex")));
        inputFollow.add(new ArrayList<String>(Arrays.asList("bob", "abc")));
        inputFollow.add(new ArrayList<String>(Arrays.asList("bob", "alex")));
        inputFollow.add(new ArrayList<String>(Arrays.asList("pippen", "a")));
        inputFollow.add(new ArrayList<String>(Arrays.asList("jordan", "abc")));
        
        for(int i = 0; i < 6; i ++) {
            try {
                tweeter.tweet(inputTweet.get(i).get(0), inputTweet.get(i).get(1));
                tweeter.follow(inputFollow.get(i).get(0), inputFollow.get(i).get(1));               
                //tweetStats.resetStats();
                //tweeter.follow("bob", "cob");
               //tweeter.tweet("alex", "12345");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //tweetStats.resetStats();
        System.out.println("Most productive user: " + tweetStats.getMostProductiveUser());
        System.out.println("Most followed user: " + tweetStats.getMostFollowedUser());
        System.out.println("Length of the longest tweet: " + tweetStats.getLengthOfLongestTweetAttempted());
    }
}
