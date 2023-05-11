import twitter4j.*;
import twitter4j.v1.Query;
import twitter4j.v1.QueryResult;
import twitter4j.v1.Status;

public class PostTweet {
    public static void main(String[] args) throws TwitterException {

        String apikey = "hA63lAYsxmQUvNRLw936WLo1b";
            String api_key_secret= "gh7jgaq9fOwPPIw0FjJJayGk8oZqOflfzKAH9uIuVVml9R5oZm";
            String access_toke = "1656212869414010881-Cyxf6gsprBD6opQ0Pkwp3EiOiuBcwm";
            String access_token_secret= "6tcFW1LPG4przxiWUAPZzNj048QiMH5iS0mTQkxbDhXOZ";

        Twitter twitter = Twitter.getInstance();
        Query query = Query.of("aatifdevtest");
        QueryResult result = twitter.v1().search().search(query);
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }


    }
}
