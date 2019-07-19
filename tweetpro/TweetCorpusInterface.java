package tweetpro;

import static java.util.stream.Collectors.toList;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public interface TweetCorpusInterface extends Iterable<Tweets> 
{
	/**
	 * @param File file
	 * @return TweetCorpusInterface
	 * @throws IOException
	 */
	static TweetCorpusInterface parseFile(File file) throws IOException 
	{
		TweetCorpus tc = new TweetCorpus();
		List<String> list=new ArrayList<>();
		file.setReadable(true);
		Scanner scanner=new Scanner(file.toPath());
		{
			while (scanner.hasNextLine())
		{
				String line = scanner.nextLine();
		list.add(line);
		}
			scanner.close();
		}
		List<String> listaFiltrata=list.stream().filter(l->l.length()>0).collect(toList());
		/**
		 * riconoscimento stringhe e creazione parametri Tweet
		 */
		for (String str:listaFiltrata)
		{
			ArrayList<String> hashList=new ArrayList<>();
			Tweet tweetOriginale=null;
			boolean isRetweet=false;
			URL mediaUrl=null;
			str=str.substring(str.indexOf("\"id\":"));
			long id=Long.parseLong(str.substring(5,str.indexOf(",")));
			str=str.substring(str.indexOf("\"text\":"));
			String text=str.substring(8,str.indexOf("\","));
			str=str.substring(str.indexOf("\"id\":"));
			long userId=Long.parseLong(str.substring(5,str.indexOf(",")));
			str=str.substring(str.indexOf("\"name\":"));
			String userName=str.substring(8, str.indexOf("\",\""));
			str=str.substring(str.indexOf("\"screen_name\":"));
			String userScreenName=str.substring(15, str.indexOf("\","));
			str=str.substring(str.indexOf("\"followers_count\":"));
			int userFollowersCount=Integer.parseInt(str.substring(18, str.indexOf(",")));
			str=str.substring(str.indexOf("\"friends_count\":"));
			int userFriendsCount=Integer.parseInt(str.substring(16, str.indexOf(",")));
			str=str.substring(str.indexOf("\"favourites_count\":"));
			int userFavouriteCount=Integer.parseInt(str.substring(19, str.indexOf(",")));
			str=str.substring(str.indexOf("\"verified\":"));
			boolean userVerified = Boolean.parseBoolean(str.substring(11, str.indexOf(",")));
			str=str.substring(str.indexOf("\"statuses_count\":"));
			int userTweetsCount = Integer.parseInt(str.substring(17, str.indexOf(",")));
			if (str.contains("\"retweeted_status\":"))
			{
				ArrayList<String> hashtags=new ArrayList<>();
				URL mediaURL=null;
				isRetweet=true;
				str=str.substring(str.indexOf("\"id\":"));
				long idOriginal=Long.parseLong(str.substring(5,str.indexOf(",")));
				str=str.substring(str.indexOf("\"text\":"));
				String textOriginal=str.substring(8,str.indexOf("\","));
				str=str.substring(str.indexOf("\"id\":"));
				long userIdOriginal=Long.parseLong(str.substring(5,str.indexOf(",")));
				str=str.substring(str.indexOf("\"name\":"));
				String userNameOriginal=str.substring(8, str.indexOf("\","));
				str=str.substring(str.indexOf("\"screen_name\":"));
				String userScreenNameOriginal=str.substring(15, str.indexOf("\","));
				str=str.substring(str.indexOf("\"followers_count\":"));
				int userFollowersCountOriginal=Integer.parseInt(str.substring(18, str.indexOf(",")));
				str=str.substring(str.indexOf("\"friends_count\":"));
				int userFriendsCountOriginal=Integer.parseInt(str.substring(16, str.indexOf(",")));
				str=str.substring(str.indexOf("\"favourites_count\":"));
				int userFavouriteCountOriginal=Integer.parseInt(str.substring(19, str.indexOf(",")));
				str=str.substring(str.indexOf("\"verified\":"));
				boolean userVerifiedOriginal= Boolean.parseBoolean(str.substring(11, str.indexOf(",")));
				str=str.substring(str.indexOf("\"statuses_count\":"));
				int userTweetsCountOriginal = Integer.parseInt(str.substring(17, str.indexOf(",")));
				str=str.substring(str.indexOf("\"retweet_count\":"));
				int retweetCountOriginal=Integer.parseInt(str.substring(16, str.indexOf(",")));
				str=str.substring(str.indexOf("\"favorite_count\":"));
				int likeCountOriginal=Integer.parseInt(str.substring(17,str.indexOf(",")));
				str=str.substring(str.indexOf("\"hashtags\":"));
				for (int i=0;true;i++)
				{
					if (str.substring(i, i+10).equals("\"symbols\":")) break;
					if (str.substring(i,i+8).equals("\"text\":"))
					{
						String hashtag=str.substring(8,str.indexOf("\","));
						hashtags.add(hashtag.toLowerCase());
					}
				}
				if (str.contains("\"media\":"))
				{
					str=str.substring(str.indexOf("\"media_url\":"));
					try 
					{
						mediaURL=new URL(str.substring(13, str.indexOf("\",")).replace("\\", "") );
					} 
					catch (MalformedURLException e) 
					{
						e.printStackTrace();
					}
				}
				
				tweetOriginale=new Tweet(idOriginal,textOriginal,new User(userIdOriginal,userNameOriginal,userScreenNameOriginal,
						userFollowersCountOriginal,userFriendsCountOriginal,userFavouriteCountOriginal,userVerifiedOriginal,userTweetsCountOriginal),
						likeCountOriginal,retweetCountOriginal,false,null,mediaURL,hashtags);
			}
			str=str.substring(str.indexOf("\"retweet_count\":"));
			int retweetCount=Integer.parseInt(str.substring(16, str.indexOf(",")));
			str=str.substring(str.indexOf("\"favorite_count\":"));
			int likeCount=Integer.parseInt(str.substring(17,str.indexOf(",")));
			
			str=str.substring(str.indexOf("\"hashtags\":"));
			String stringHash=str.substring(11, str.indexOf("],"));
			if (stringHash.length()>1)
			{
				while (stringHash.contains("\"text\":"))
						{
							stringHash=stringHash.substring(stringHash.indexOf("\"text\":")+8);
							hashList.add(stringHash.substring(0, stringHash.indexOf("\",\"")).toLowerCase());
							
						}
			}
			
			if (str.contains("\"media\":"))
			{
				str=str.substring(str.indexOf("http:"));
				try 
				{
					mediaUrl=new URL(str.substring(0, str.indexOf("\",")).replace("\\", ""));
				} 
				catch (MalformedURLException e) 
				{
					e.printStackTrace();
				}
			}
			Tweet tweet=new Tweet(id,text,new User(userId,userName,userScreenName,userFollowersCount,userFriendsCount,userFavouriteCount,
			userVerified,userTweetsCount),likeCount,retweetCount,isRetweet,tweetOriginale,mediaUrl,hashList);
			tc.tweetList.add(tweet);
		}
		return tc;	
	}
	
	/**
	 * @return int tweetCount
	 */
	int getTweetCount();
	
	/**
	 * @param  User user
	 * @return List<Tweets> with user=user
	 */
	List<Tweets> getTweets(Users user);
	
	/**
	 * @param int k
	 * @return List<String> k hashtags most popular
	 */
	List<String> getTopHashtags(int k);
	
	/**
	 * 
	 * @return int of unique Users
	 */
	int getUniqueUsersCount();
	

}
