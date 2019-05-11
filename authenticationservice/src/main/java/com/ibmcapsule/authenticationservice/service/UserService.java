package com.ibmcapsule.authenticationservice.service;

import com.ibmcapsule.authenticationservice.domain.User;
import com.ibmcapsule.authenticationservice.exception.UserNotFoundException;

public interface UserService {
  public User saveUser(User user);
  public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException;
}
