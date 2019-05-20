package com.example.orchestrationservice.controller;

import com.example.orchestrationservice.domain.User;
import com.example.orchestrationservice.exception.UserAlreadyExistException;
import com.example.orchestrationservice.service.OrchestrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("*")
public class OrchestrationController {

  @Autowired
  private OrchestrationService orchestrationService;

  @PostMapping("/user")
  public ResponseEntity<?> registerAndSave(@RequestBody User user) throws UserAlreadyExistException{
    ResponseEntity responseEntity = null;
    try {
      User userObj = orchestrationService.registerUser(user);
      responseEntity = new ResponseEntity(userObj, HttpStatus.CREATED);
    } catch(UserAlreadyExistException e){
      throw new UserAlreadyExistException();
    }

    return responseEntity;
  }
}
