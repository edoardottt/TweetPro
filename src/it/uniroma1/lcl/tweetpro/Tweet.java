package it.uniroma1.lcl.tweetpro;

import java.net.URL;
import java.util.List;
import java.util.Optional;

public class Tweet implements Tweets
{
	private long id;
	private String text;
	private User user;
	private int likeCount;
	private int RTCount;
	private boolean isRetweet;
	private Tweet originalTweet;
	private URL urlMedia;
	private List<String> hashtags;
	
	public Tweet(long id,String text,User user,int likeCount,int RTCount, boolean isRetweet, Tweet originalTweet,URL urlMedia, List<String> hashtags)
	{
		this.id=id;
		this.user=user;
		this.text=text;
		this.likeCount=likeCount;
		this.RTCount=RTCount;
		this.isRetweet=isRetweet;
		this.originalTweet=originalTweet;
		this.urlMedia=urlMedia;
		this.hashtags=hashtags;
	}
	@Override
	public long getID() 
	{
		return id;
	}
	@Override
	public User getUser() 
	{
		return user;
	}

	@Override
	public String getText() 
	{
		return text;
	}

	@Override
	public List<String> getHashtags() 
	{
		return hashtags;
	}

	@Override
	public int getLikeCount() 
	{
		return likeCount;
	}

	@Override
	public int getRTCount() 
	{
		return RTCount;
	}

	@Override
	public boolean isRetweet() 
	{
		return isRetweet;
	}

	@Override
	public Tweet getOriginalTweet() 
	{
		return originalTweet;
	}

	@Override
	public Optional<URL> getMedia() 
	{
		if (urlMedia!=null) return Optional.of(urlMedia);
		return Optional.empty();
		
	}
	@Override
	public String toString()
	{
		return "{"+"id: "+id+", "+"text: "+text+", "+"user: "+user.toString()+", "+"like: "+likeCount+", "
				+"RT: "+RTCount+", "+"isRetweet: "+isRetweet+", "+"originalTweet: "+originalTweet+", "
				+"url: "+urlMedia+", "+"hashtags: "+hashtags+"}"; 
	}
}
