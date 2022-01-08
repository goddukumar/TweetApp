package com.tweet.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweet.model.PostTweet;
import com.tweet.model.User;
import com.tweet.repo.PostTweetRepo;
import com.tweet.repo.UserRepo;

@Service
public class TweetService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PostTweetRepo postTweetRepo;

	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	public Optional<User> searchUsers(String userName) {
		return userRepo.findByEmail(userName);

	}

	public boolean existingUser(String email) {
		if (!userRepo.findByEmail(email).isEmpty())
			return true;

		return false;
	}

	public void postTweet(PostTweet tweet) {
		// userRepo.i
		tweet.setCreatedDate(formatDate());
		postTweetRepo.insert(tweet);

	}

	public List<PostTweet> getTweets() {
		return postTweetRepo.findAll();
	}

	public List<PostTweet> getTweetsByUser(String userName) {
		return postTweetRepo.findByUserName(userName);
	}

	public void deleteTweet(String username, String id) {
		postTweetRepo.deleteById(id);
	}

	private String formatDate() {
		SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return DateFor.format(new Date());
	}

}
