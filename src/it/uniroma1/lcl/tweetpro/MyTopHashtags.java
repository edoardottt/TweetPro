package it.uniroma1.lcl.tweetpro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyTopHashtags implements TopHashTags
{
	public List<String> calculate(int k,ArrayList<Tweet> listaTweet)
	{
		Map<String,Integer> mapHashtag=new HashMap<>();
		List<String> hashList=new ArrayList<>();
		
		for (Tweets t: listaTweet)
		{ if (t.getHashtags().size()>0) hashList.addAll(t.getHashtags()); }
		
		for (String h:hashList)
		{
			if (mapHashtag.isEmpty() || (!mapHashtag.containsKey(h))) mapHashtag.put(h, 1);
			if (mapHashtag.containsKey(h)) mapHashtag.put(h, mapHashtag.get(h)+1);
		}
		List<String> result = mapHashtag.entrySet().stream()
		        .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
		        .map(Map.Entry::getKey)
		        .limit(k)
		        .collect(Collectors.toList());
		return result;
	}
}
