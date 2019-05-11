package com.ibmcapsule.authenticationservice.service;

import com.ibmcapsule.authenticationservice.domain.User;
import com.ibmcapsule.authenticationservice.exception.UserNotFoundException;
import com.ibmcapsule.authenticationservice.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

  @Mock
  private UserRepository userRepository;
  private User user;

  @InjectMocks
  private UserServiceImpl userService;

  @Before
  public void setUp(){
    MockitoAnnotations.initMocks(this);
    user = new User();
    user.setUsername("John123");
    user.setPassword("John123");
  }

  @After
  public void tearDown(){
    user = null;
  }

  @Test
  public void testSaveUserSuccess(){
    Mockito.when(userRepository.save(user)).thenReturn(user);
    User fetchedUser = userService.saveUser(user);
    Assert.assertEquals(user.getUsername(), fetchedUser.getUsername());
    verify(userRepository, times(1)).save(user);
  }

  @Test
  public void testFindByUsernameAndPasswordSuccess() throws UserNotFoundException {
    Mockito.when(userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword())).thenReturn(user);
    User fetchedUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    Assert.assertEquals(user.getUsername(), fetchedUser.getUsername());
    verify(userRepository, times(1)).findByUsernameAndPassword(user.getUsername(), user.getPassword());
  }
}
