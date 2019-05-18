package com.simpleApp.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.simpleApp.model.User;

@Repository
public interface UserDao extends MongoRepository<User,String>{
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String userName);
    Boolean existsByEmail(String email);

}
