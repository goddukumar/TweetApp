package com.tweetusermangement.Iservice;

import com.tweetusermangement.dto.RegisterationRequest;
import com.tweetusermangement.entity.RegistrationEntity;

public interface UserRegistrationServiceI {

	RegistrationEntity register(RegisterationRequest request);

	boolean existingUser(String email);

}
