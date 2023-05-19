package tweetpro.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import tweetpro.TweetCorpusInterface;
import tweetpro.Tweets;
import tweetpro.Users;

public class Tests {
	
	private TweetCorpusInterface tweets;
	private Tweets tweet; 
	private Tweets mediaTweet; 
	private Tweets nomediaTweet; 

	@Before
	public void setup() {
		try {
			tweets =  TweetCorpusInterface.parseFile(new File("tweetsCorpus.json"));
			tweet = TweetCorpusInterface.parseFile(new File("tweet.json")).iterator().next();
			mediaTweet = TweetCorpusInterface.parseFile(new File("mediaTweet.json")).iterator().next();			 
			nomediaTweet = TweetCorpusInterface.parseFile(new File("nomediaTweet.json")).iterator().next();			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCount() {
		assertEquals(3114, tweets.getTweetCount());
	}
	
	@Test
	public void testSingleId() {
		assertEquals(464731336310140929l, tweet.getID());
	}
	
	@Test
	public void testUser() {
		Users user = tweet.getUser();
		assertEquals("jasmine s.", user.getName());
		assertEquals("jasminesaff", user.getScreenName());
		assertEquals(311, user.getFollowersCount());
		assertEquals(245, user.getFriendsCount());
		assertEquals(10704, user.getTweetsCount());
		assertEquals(false, user.isVerified());
	}
	
	@Test
	public void testRT() {
		try {
			assertTrue(tweet.isRetweet());
			Tweets rt = tweet.getOriginalTweet();
			assertEquals(464717213304627200l, rt.getID());		
			Users user = rt.getUser();
			assertEquals("9GAG Tweets", user.getName());
			assertEquals("9GAGTweets", user.getScreenName());
			assertEquals(251032, user.getFollowersCount());
			assertEquals(3, user.getFriendsCount());
			assertEquals(false, user.isVerified());
			assertEquals(124287, user.getTweetsCount());
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testMedia() {
		Optional<URL> url = mediaTweet.getMedia();
		assertTrue(url.isPresent());		
		assertEquals("/media/BnLliEDCcAAGxd9.jpg", url.get().getFile());
		assertFalse(nomediaTweet.getMedia().isPresent());
	}
	
	@Test
	public void testTopHashtag() {
		List<String> expected = Arrays.asList("hulk", "spiderman", "captainamerica", "pojokpulsa", "thor", "id_worldcup", "ironman", "pojok080514", "batman", "loki");
		List<String> list = tweets.getTopHashtags(10);
		assertTrue(expected.containsAll(list));
		assertTrue(list.containsAll(expected));
	}
	
	@Test
	public void testCardinality() {
		assertTrue((Math.abs(2698.0 - tweets.getUniqueUsersCount()) / 2698.0) < 0.05);
	}

}

