package com.ibmcapsule.authenticationservice.config;

import com.ibmcapsule.authenticationservice.domain.User;
import com.ibmcapsule.authenticationservice.service.UserService;
import com.ibmcapsule.rabbitMq.domain.UserDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

  @Autowired
  private UserService userService;

  @RabbitListener(queues = "user_queue")
  public void getUserDtoFromRabbitMq(UserDTO userDTO){
    User user = new User();
    user.setUsername(userDTO.getUsername());
    user.setPassword(userDTO.getPassword());
    userService.saveUser(user);

  }
}
