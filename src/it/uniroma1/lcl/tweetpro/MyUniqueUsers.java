package it.uniroma1.lcl.tweetpro;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MyUniqueUsers implements UniqueUsers
{
	@Override
	public int calculate(ArrayList<Tweet> listaTweet) 
	{
		return listaTweet.stream().map(s->s.getUser().getId()).collect(Collectors.toSet()).size();
	}
	
}
