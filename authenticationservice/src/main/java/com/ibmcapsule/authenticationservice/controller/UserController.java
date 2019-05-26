package com.ibmcapsule.authenticationservice.controller;

import com.ibmcapsule.authenticationservice.domain.User;
import com.ibmcapsule.authenticationservice.exception.UserNotFoundException;
import com.ibmcapsule.authenticationservice.service.SecurityTokenGenerator;
import com.ibmcapsule.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/userservice")
public class UserController {
  private UserService userService;
  private SecurityTokenGenerator securityTokenGenerator;
  private ResponseEntity responseEntity;

  @Autowired
  public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator){
    this.userService = userService;
    this.securityTokenGenerator = securityTokenGenerator;
  }

 /* @PostMapping("/save")
  public ResponseEntity saveUser(@RequestBody User user){
    userService.saveUser(user);
    return responseEntity = new ResponseEntity(user, HttpStatus.CREATED);
  }*/

  @PostMapping("/login")
  public ResponseEntity loginUser(@RequestBody User user) throws UserNotFoundException {
    Map<String, String> map = null;

    try {
      User userObj = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());

      if (userObj.getUsername().equals(user.getUsername())) {
        map = securityTokenGenerator.generateToken(user);
      }

      responseEntity = new ResponseEntity(map, HttpStatus.OK);
    }
    catch(UserNotFoundException e){
      throw new UserNotFoundException();
    }
    catch(Exception e){
      responseEntity = new ResponseEntity("Try After Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return responseEntity;
  }

}
