package com.example.orchestrationservice.service;

import com.example.orchestrationservice.domain.User;
import com.example.orchestrationservice.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrchestrationServiceImpl implements OrchestrationService{

  @Autowired
  private RestTemplate restTemplate;

  private String userTrackServiceUrl = "http://usertrackservice/api/v1/usertrackservice/register";
  private String authenticationServiceUrl = "http://authenticationsrvice/api/v1/userservice/save";

  @Override
  public User registerUser(User user) throws UserAlreadyExistException {
    User userRespone = null;

    try{
      userRespone = restTemplate.postForObject(userTrackServiceUrl, user, User.class);
      restTemplate.postForObject(authenticationServiceUrl, user, User.class);
    } catch (Exception e){
      throw new UserAlreadyExistException();
    }
    return userRespone;
  }
}
