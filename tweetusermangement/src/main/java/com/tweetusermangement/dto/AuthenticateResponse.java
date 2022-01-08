package com.tweetusermangement.dto;

import java.io.Serializable;

public class AuthenticateResponse implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private final String jwtToken;

	public AuthenticateResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

}
