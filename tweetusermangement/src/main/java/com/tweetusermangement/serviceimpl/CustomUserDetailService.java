package com.tweetusermangement.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tweetusermangement.dao.UserRegistrationDao;
import com.tweetusermangement.entity.RegistrationEntity;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRegistrationDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RegistrationEntity user = dao.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found UserName: " + username));
		return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}
}
