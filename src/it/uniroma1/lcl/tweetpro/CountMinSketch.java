package it.uniroma1.lcl.tweetpro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountMinSketch implements TopHashTags
{
	@Override
	public List<String> calculate(int k, ArrayList<Tweet> listaTweet) 
	{
		List<String> hashtagList=listaTweet.stream().map(s->s.getHashtags()).flatMap(List::stream).collect(Collectors.toList());
		Map<String,Integer> m=new HashMap<>();
		int l=k*100;
		for (String h: hashtagList)
		{
			if ((m.containsKey(h)) || m.size()<l)
			{
				if (m.containsKey(h)) m.put(h, m.get(h)+1);
				else m.put(h, 1);
			}
			else
			{
				String minStr="";
				int min=k;
				for (String hash:m.keySet())
				{
					if (m.get(hash)<min)
							{minStr=hash.toString(); min=m.get(hash);}
				}
				int value =m.get(minStr);
				m.remove(minStr);
				m.put(h, value++);
			}
				
		}
		return m.entrySet().stream()
		        .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
		        .map(Map.Entry::getKey)
		        .limit(k)
		        .collect(Collectors.toList());
	}

}
