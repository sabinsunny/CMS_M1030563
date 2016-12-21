package com.mindtree.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.cms.configuration.TwitterConfig;

@RestController
@RequestMapping(value = "/twitter")
public class SocialController {

	@Autowired
	private TwitterConfig twitterConfig;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public List<Tweet> home(@RequestParam String title) {
		Twitter twitter = twitterConfig.getTwitterTemplate();
		List<Tweet> tweets=null;
		try {
			SearchResults searchResults = twitter.searchOperations().search(title);
			 tweets = searchResults.getTweets();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}
}
