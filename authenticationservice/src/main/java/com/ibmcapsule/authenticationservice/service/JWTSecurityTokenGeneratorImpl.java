package com.ibmcapsule.authenticationservice.service;

import com.ibmcapsule.authenticationservice.domain.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator {

  @Override
  public Map<String, String> generateToken(User user) {

    String jwtToken = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date())
                      .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
    Map<String, String> map = new HashMap<>();
    map.put("token", jwtToken);
    map.put("message", "User Successfully Logged In");
    return map;
  }
}
