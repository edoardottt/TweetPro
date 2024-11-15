# TweetPro

![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/edoardottt/TweetPro)
![Java Version](https://github.com/edoardottt/images/blob/main/TweetPro/java8.svg)

**Follow [@edoardottt2](https://twitter.com/edoardottt2) on Twitter!**

Description :mega:
------
TweetPro is a University project of the *Programming Metodologies* Course held by Prof. Roberto Navigli.
It's a Java tool that analyzes Tweets and other things related to Twitter.


Tweet :baby_chick:
-------

It contains a numerical ID, a body, an User that wrote it and counter of retweets and likes.
A tweet can be retweetted. In this case is itself a tweet, with an original tweet's reference.
It can have also media.


User :boy:
-------

An User is identified by an unique name ([@edoardottt2](https://twitter.com/edoardottt2)), an unique numeric id and by
a name 'name', not unique. An User can be verified (which adds a sign of
check blue to its name). An User has followers (followers), or other users who follow i
tweets, and he himself is a follower of other users (following), that is users to whom the user is
interested. An User can post tweets.


A tweet is stored as a JSON file :books:
-------

Like this:

```JSON
"tweet": {
  "id": 464731336310140929,
  "text": "RT @9GAGTweets: From Hulk... hue hue hue - http:\/\/t.co\/bNRmL7uUVf",
  "user": {
    "id": 534017128,
    "name": "jasmine s.",
    "screen_name": "jasminesaff",
    "followers_count": 311,
    "favourites_count": 0,
    "friends_count": 245,
    "verified": false,
    "statuses_count": 10704,
  },
  "retweeted_status": {
    "id": 464717213304627200,
    "text": "From Hulk... hue hue hue - http:\/\/t.co\/bNRmL7uUVf",
    "user": {
      "id": 471022109,
      "name": "9GAG Tweets",
      "screen_name": "9GAGTweets",
      "followers_count": 251032,
      "friends_count": 3,
      "favourites_count": 0,
      "statuses_count": 124287,
    },
    "retweet_count": 151,
    "favorite_count": 46,
    "entities": {
      "hashtags": [],
      "media": [
        {
          "media_url": "http:\/\/pbs.twimg.com\/media\/BnMB19sIIAAJv0O.jpg",
        }
      ]
    }
  },
  "retweet_count": 0,
  "favorite_count": 0,
  "entities": {
    "hashtags": []
    "media": [
      {
        "media_url": "http:\/\/pbs.twimg.com\/media\/BnMB19sIIAAJv0O.jpg",
      }
    ]
  }
}
```

Download :satellite:
--------

```console
git clone https://github.com/edoardottt/TweetPro
```

--------
 
[edoardottt.com](https://edoardottt.com/) to contact me.
 
