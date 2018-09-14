package it.uniroma1.lcl.tweetpro;

import java.util.ArrayList;
import java.util.List;

public interface TopHashTags 
{
	/**
	 * @param int k
	 * @param List<Tweets> listaTweet
	 * @return List<String> k hashtags most popular
	 */
	public List<String> calculate(int k, ArrayList<Tweet> listaTweet);
}
