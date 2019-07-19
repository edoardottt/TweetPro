package tweetpro;

import java.net.URL;
import java.util.List;
import java.util.Optional;

public interface Tweets 
{
	/**
	 * @return id
	 */
	long getID();
	
	/**
	 * @return user
	 */
	Users getUser();
	
	/**
	 * @return text
	 */
	String getText();
	
	/**
	 * @return List<String> hashtags
	 */
	List<String> getHashtags();
	
	/**
	 * @return likeCount
	 */
	int getLikeCount();
	
	/**
	 * @return RTCount
	 */
	int getRTCount();
	
	/**
	 * @return isRetweet
	 */
	boolean isRetweet();
	
	/**
	 * @return originalTweet
	 */
	Tweets getOriginalTweet();
	
	/**
	 * @return mediaUrl
	 */
	Optional<URL> getMedia();


	

}
