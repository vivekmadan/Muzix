package com.ibmcapsule.usertrackservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {
  private String userExchange = "user_exchange";
  private String userQueue = "user_queue";
  private String trackQueue = "track_queue";

  @Bean
  public DirectExchange userExchange(){
    return new DirectExchange(userExchange);
  }

  @Bean
  public Queue userQueue(){
    return new Queue(userQueue, false);
  }

  @Bean
  public Queue trackQueue() {
    return new Queue(trackQueue, false);
  }

  @Bean
  public Binding trackBinding(Queue trackQueue, DirectExchange exchange){
    return BindingBuilder.bind(trackQueue).to(exchange).with("track_routing");
  }

  @Bean
  public Binding userBinding(Queue userQueue, DirectExchange exchange){
    return BindingBuilder.bind(userQueue).to(exchange).with("user_routing");
  }

  @Bean
  public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
    return rabbitTemplate;
  }


}
