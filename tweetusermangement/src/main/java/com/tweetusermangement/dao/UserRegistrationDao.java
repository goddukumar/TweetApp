package com.tweetusermangement.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetusermangement.entity.RegistrationEntity;

@Repository
public interface UserRegistrationDao extends MongoRepository<RegistrationEntity,String> {

	public Optional<RegistrationEntity>  findByEmail(String username);

	

}
