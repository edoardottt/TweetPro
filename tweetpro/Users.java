package tweetpro;

public interface Users 
{
	/**
	 * @return name
	 */
	String getName();
	
	/**
	 * @return screenName
	 */
	String getScreenName();
	
	/**
	 * @return tweetsCount
	 */
	int getTweetsCount();
	
	/**
	 * @return favouriteCount
	 */
	int getFavsCount();
	
	/**
	 * @return followersCount
	 */
	int getFollowersCount();
	
	/**
	 * @return friendsCount
	 */
	int getFriendsCount();
	
	/**
	 * @return id
	 */
	long getId();
	
	/**
	 * @return isVerified
	 */
	boolean isVerified();

}
