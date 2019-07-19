package tweetpro;

public class User implements Users
{
	private long id;
	private String name;
	private String screenName;
	private int followersCount;
	private int tweetsCount;
	private int favouriteCount;
	private boolean isVerified;
	private int friendsCount;
	public User(long id,String name,String screenName,int followersCount,int friendsCount ,int favouriteCount,boolean isVerified,int tweetsCount)
	{
		this.id=id;
		this.name=name;
		this.screenName=screenName;
		this.tweetsCount=tweetsCount;
		this.favouriteCount=favouriteCount;
		this.followersCount=followersCount;
		this.isVerified=isVerified;
		this.friendsCount=friendsCount;
	}
	
	@Override
	public String getName() 
	{
		return name;
	}

	@Override
	public String getScreenName() 
	{
		return screenName;
	}

	@Override
	public int getTweetsCount() 
	{
		return tweetsCount;
	}

	@Override
	public int getFavsCount() 
	{
		return favouriteCount;
	}

	@Override
	public int getFollowersCount() 
	{
		return followersCount;
	}

	@Override
	public int getFriendsCount() 
	{
		return friendsCount;
	}

	@Override
	public long getId() 
	{
		return id;
	}

	@Override
	public boolean isVerified() 
	{
		return isVerified;
	}
	
	/**
	 * @return string of User
	 */
	@Override 
	public String toString()
	{
		return "{"+"id: "+id+", "+"name: "+name+", "+"screenName: "+screenName+", "+"followers: "+followersCount+", "
	+"tweets: "+tweetsCount+", "+"favourite: "+favouriteCount+", "+"is Verified: "+isVerified+", "+"friends: "+friendsCount+"}";
	}
}
