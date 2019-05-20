package com.example.orchestrationservice.service;

import com.example.orchestrationservice.domain.User;
import com.example.orchestrationservice.exception.UserAlreadyExistException;

public interface OrchestrationService {

  public User registerUser(User user) throws UserAlreadyExistException;
}
