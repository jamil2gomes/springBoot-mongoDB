package com.jamil.mongoteste.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jamil.mongoteste.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
