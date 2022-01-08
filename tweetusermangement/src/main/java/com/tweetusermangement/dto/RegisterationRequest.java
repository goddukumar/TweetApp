package com.tweetusermangement.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class RegisterationRequest {
	@NotEmpty(message = "First name is required")	
	private String firstName;
	
	@NotEmpty(message = "Last name is required")
	private String lastName;
	
	@NotEmpty(message = "Emailis required")
	@Email
	private String email;
	
	@NotEmpty(message = "Password is required")
	private String password;
	
	@NotEmpty(message = "Mobile number is required")
	@Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
    message="Mobile number is invalid")
	private String contactNumber;

}
