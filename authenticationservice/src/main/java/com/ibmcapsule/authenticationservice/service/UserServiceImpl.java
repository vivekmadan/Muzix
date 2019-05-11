package com.ibmcapsule.authenticationservice.service;

import com.ibmcapsule.authenticationservice.domain.User;
import com.ibmcapsule.authenticationservice.exception.UserNotFoundException;
import com.ibmcapsule.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService {

  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
    User user = userRepository.findByUsernameAndPassword(username, password);

    if(user == null)
    {
      throw new UserNotFoundException();
    }
   return user;
  }
}
