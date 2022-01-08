package com.tweet.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweet.model.PostTweet;

public interface PostTweetRepo extends MongoRepository<PostTweet, String>{

	List<PostTweet> findByUserName(String userName);

}
