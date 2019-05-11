package com.ibmcapsule.authenticationservice.repository;

import com.ibmcapsule.authenticationservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  public User findByUsernameAndPassword(String username, String password);
}
