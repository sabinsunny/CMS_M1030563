package com.mindtree.cms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

@Component
public class TwitterConfig {
	@Autowired
	private Environment environment;

	public Twitter getTwitterTemplate() {
		String consumerKey = environment.getProperty("consumerKey");
		String consumerSecret = environment.getProperty("consumerSecret");
		String accessToken = environment.getProperty("accessToken");
		String accessTokenSecret = environment.getProperty("accessTokenSecret");
		TwitterTemplate twitterTemplate = new TwitterTemplate(consumerKey, consumerSecret, accessToken,
				accessTokenSecret);
		return twitterTemplate;
	}
}
