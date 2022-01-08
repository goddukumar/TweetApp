package com.tweetusermangement.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetusermangement.Iservice.UserRegistrationServiceI;
import com.tweetusermangement.dao.UserRegistrationDao;
import com.tweetusermangement.dto.RegisterationRequest;
import com.tweetusermangement.entity.RegistrationEntity;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationServiceI {

	@Autowired
	private UserRegistrationDao userRegDao;

	@Override
	public RegistrationEntity register(RegisterationRequest request) {
		RegistrationEntity register = new RegistrationEntity();
		BeanUtils.copyProperties(request, register);
		RegistrationEntity res = userRegDao.save(register);
		return res;
	}

	@Override
	public boolean existingUser(String email) {
		if (!userRegDao.findByEmail(email).isEmpty())
			return true;
		return false;
	}

}
