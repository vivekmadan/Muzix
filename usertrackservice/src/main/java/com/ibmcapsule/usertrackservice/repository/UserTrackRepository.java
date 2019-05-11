package com.ibmcapsule.usertrackservice.repository;

import com.ibmcapsule.usertrackservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserTrackRepository extends MongoRepository<User, String> {
  public User findByUsername(String username);
}
