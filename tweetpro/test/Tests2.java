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

import org.junit.BeforeClass;
import org.junit.Test;

import tweetpro.Tweet;
import tweetpro.TweetCorpus;
import tweetpro.User;

public class Tests2 {
	
	private static TweetCorpus tweets;
	private static Tweet mediaTweet; 
	private static Tweet nomediaTweet; 
	private static Tweet nomediaTweet2;

	private static Tweet verified; 


	@BeforeClass
	public static void setup() {
		try {
			mediaTweet = TweetCorpus.parseFile(new File("mediaTrumpF.json")).iterator().next();			 
			nomediaTweet = TweetCorpus.parseFile(new File("noMediaTrumpF.json")).iterator().next();
			verified =  TweetCorpus.parseFile(new File("verified.json")).iterator().next();
			tweets =  TweetCorpus.parseFile(new File("reducedTrumpF.json"));
			nomediaTweet2 = TweetCorpus.parseFile(new File("nomediaTweet.json")).iterator().next();

			//((TweetCorpusImpl)tweets).setCountSketchStrategy();
			//((TweetCorpusImpl)tweets).setLogLogStrategy();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCount() {
		assertEquals(8651, tweets.getTweetCount());
	}

	@Test
	public void testUser() {
		User user = nomediaTweet.getUser();
		assertEquals("Alok Awasthi", user.getName());
		assertEquals("AlokAwasthi147", user.getScreenName());
		assertEquals(1, user.getFollowersCount());
		assertEquals(144, user.getFriendsCount());
		assertEquals(1, user.getTweetsCount());
		assertEquals(false, user.isVerified());
	}
	
	@Test
	public void testNORT() {
		try {
			assertFalse(nomediaTweet2.isRetweet());
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testUserVerified() {
		User user = verified.getUser();
		assertEquals("Target en francais", user.getName());
		assertEquals("Targetfrancais", user.getScreenName());
		assertEquals(11717, user.getFollowersCount());
		assertEquals(650, user.getFriendsCount());
		assertEquals(1317, user.getTweetsCount());
		assertEquals(190, user.getFavsCount());
		assertEquals(true, user.isVerified());
	}
	
	@Test
	public void testRetweet() {
		assertFalse(nomediaTweet.isRetweet());
		assertEquals(null, nomediaTweet.getOriginalTweet());
	}
	
	@Test
	public void testRetweetException() {
		try {
			assertFalse(nomediaTweet.isRetweet());
			nomediaTweet.getOriginalTweet().getID();
			fail("expected exception was not occured.");
		} catch (Exception e) {
			
		}
		

	}
	
	@Test
	public void testMedia() {
		Optional<URL> url = mediaTweet.getMedia();
		assertTrue(url.isPresent());		
		assertEquals("/media/DCt6rw7VwAAeFWs.jpg", url.get().getFile());
		assertFalse(nomediaTweet.getMedia().isPresent());
	}
	@Test
	public void testTopHashtag() {
		List<String> expected  = Arrays.asList("trump", "maga", "megynkelly", "resist", "theresistance", "news", "ga06", "resistance", "breaking", "19jun");
		List<String> list = tweets.getTopHashtags(5);
		System.out.println(list);
		assertTrue(expected.containsAll(list));
		//assertTrue(list.containsAll(expected));
	}
	
	@Test
	public void testCardinality() {
		double aca = tweets.getUniqueUsersCount();
		System.out.println(aca);
		assertTrue((Math.abs(6592.0 - aca) / 6592.0) < 0.2);
	}

}

