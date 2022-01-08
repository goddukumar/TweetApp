package com.tweet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "register")
public class User {
	
	@Id
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String contactNumber;

}
