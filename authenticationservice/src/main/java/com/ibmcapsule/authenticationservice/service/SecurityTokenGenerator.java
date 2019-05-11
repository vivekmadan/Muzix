package com.ibmcapsule.authenticationservice.service;

import com.ibmcapsule.authenticationservice.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
  public Map<String, String> generateToken(User user);
}
