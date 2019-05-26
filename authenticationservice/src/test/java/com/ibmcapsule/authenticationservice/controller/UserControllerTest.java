package com.ibmcapsule.authenticationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibmcapsule.authenticationservice.domain.User;
import com.ibmcapsule.authenticationservice.service.SecurityTokenGenerator;
import com.ibmcapsule.authenticationservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @MockBean
  private SecurityTokenGenerator securityTokenGenerator;

  private User user;

  @InjectMocks
  private UserController userController;

  @Before
  public void setUp(){
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    user = new User();
    user.setUsername("John");
    user.setPassword("John123");
  }

  /*@Test
  public void testSaveUser() throws Exception{
    when(userService.saveUser(user)).thenReturn(user);
    mockMvc.perform(post("/api/v1/userservice/save")
    .contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
      .andExpect(status().isCreated()).andDo(print());
    verify(userService, times(1)).saveUser(any());
  }*/

  @Test
  public void testLoginSuccess() throws Exception{
    when(userService.saveUser(user)).thenReturn(user);
    when(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
    mockMvc.perform(post("/api/v1/userservice/login")
    .contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
    .andExpect(status().isOk()).andDo(print());
    verify(userService, times(1)).findByUsernameAndPassword(user.getUsername(), user.getPassword());
  }

  public String jsonToString(User user){
    String result = null;

    try{
      final ObjectMapper objectMapper = new ObjectMapper();
      final String jsonContent = objectMapper.writeValueAsString(user);
      result = jsonContent;
    }
    catch(JsonProcessingException e)
    {
      result = "Json Processing Exception";
    }
    return result;
  }

}
