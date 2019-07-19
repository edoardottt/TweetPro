package tweetpro;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MyUniqueUsers implements UniqueUsers
{
	@Override
	public int calculate(ArrayList<Tweets> listaTweet) 
	{
		return listaTweet.stream().map(s->s.getUser().getId()).collect(Collectors.toSet()).size();
	}
	
}
