package com.tweet.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweet.model.PostTweet;
import com.tweet.model.User;
import com.tweet.service.TweetService;

@RestController
@RequestMapping("/api/v1.0/tweets")
public class TweetController {
	Logger logger = LoggerFactory.getLogger(TweetController.class);

	@Autowired
	private TweetService tweetService;

	@GetMapping("/users/all")
	public List<User> getAllUsers() {
		List<User> users = tweetService.getAllUser();
		return users;
	}

	@GetMapping("/user/search/{userName}")
	public ResponseEntity<?> searchUsers(@PathVariable String userName) {
		Optional<User> users = tweetService.searchUsers(userName);

		return ResponseEntity.ok(users);
	}

	@PostMapping("/{username}/add")
	public ResponseEntity<?> addPost(@PathVariable String username, @RequestBody PostTweet post) {
		logger.info("{}", post);
		post.setUserName(username);
		tweetService.postTweet(post);
		return ResponseEntity.ok("Posted successfully");

	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllTweets() {
		List<PostTweet> tweets = tweetService.getTweets();

		if (tweets.isEmpty())
			return new ResponseEntity<>("No tweets found !", HttpStatus.OK);

		return ResponseEntity.ok(tweets);
	}

	@GetMapping("/{username}")
	public ResponseEntity<?> getAllTweetsForUser(@PathVariable String username) {
		List<PostTweet> tweets = tweetService.getTweetsByUser(username);

		if (tweets.isEmpty())
			return new ResponseEntity<>("No tweets found for this user!", HttpStatus.OK);

		return ResponseEntity.ok(tweets);
	}

	@DeleteMapping("/{username}/delete/{id}")
	public ResponseEntity<?> deleteTweet(@PathVariable String username, @PathVariable String id) {

		logger.info("Delete tweet for tweet id: {}", id);

		if (!tweetService.existingUser(username)) {
			return new ResponseEntity<>("Invalid user!", HttpStatus.BAD_REQUEST);
		}

		tweetService.deleteTweet(username, id);
		return ResponseEntity.ok("Successfully deleted the post");

	}

}
