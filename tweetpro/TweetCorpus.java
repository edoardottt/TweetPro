package tweetpro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TweetCorpus implements TweetCorpusInterface
{
	protected ArrayList<Tweets> tweetList;
	private TopHashTags strategyTH;
	private UniqueUsers strategyUU;
	
	public TweetCorpus()
	{
		tweetList=new ArrayList<>();
		strategyTH=new MyTopHashtags();
		strategyUU=new MyUniqueUsers();
	}
	
	@Override
	public Iterator<Tweets> iterator() 
	{
		return tweetList.iterator();
	}

	@Override
	public int getTweetCount() 
	{
		return (int)tweetList.stream().count();
	}

	@Override
	public List<Tweets> getTweets(Users user) 
	{
		return tweetList.stream().filter(t->t.getUser().equals(user)).collect(Collectors.toList());
	}

	@Override
	public List<String> getTopHashtags(int k) 
	{
		return strategyTH.calculate(k, tweetList);
	}

	@Override
	public int getUniqueUsersCount() 
	{
		return strategyUU.calculate(tweetList);
	}
	
	@Override
	public String toString()
	{
		return tweetList.toString();
	}
	
	public void setUniqueUsersCountStrategy(UniqueUsers u)
	{
		strategyUU=u;
	}
	
	public void  setTopHashtagsStrategy(TopHashTags t)
	{
		strategyTH=t;
	}
	
}
