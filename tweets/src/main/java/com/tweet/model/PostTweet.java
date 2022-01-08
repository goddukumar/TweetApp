package com.tweet.model;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "PostTweet")
public class PostTweet {

	@Id
	private String id;

	private String userName;

	@Size(max = 144)
	private String message;
	private String createdDate;
	private String modifiedDat;
}