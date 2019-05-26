package com.ibmcapsule.usertrackservice.config;

import com.ibmcapsule.rabbitMq.domain.UserDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

  @Autowired
  private DirectExchange exchange;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  public void sendMessageToRabbitMq(UserDTO userDTO){
    rabbitTemplate.convertAndSend(exchange.getName(), "user_routing", userDTO);
  }

  public void sendTrackInfoToRabbitMq(UserDTO userDTO){
    rabbitTemplate.convertAndSend(exchange.getName(), "track_routing", userDTO);
  }
}
