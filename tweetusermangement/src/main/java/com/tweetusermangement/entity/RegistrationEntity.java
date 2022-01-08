package com.tweetusermangement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Document(collection = "register")
public class RegistrationEntity {
	@Id
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String contactNumber;

}
