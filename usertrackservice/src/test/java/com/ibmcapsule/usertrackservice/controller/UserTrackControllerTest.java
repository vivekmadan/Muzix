package com.ibmcapsule.usertrackservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibmcapsule.usertrackservice.domain.Artist;
import com.ibmcapsule.usertrackservice.domain.Image;
import com.ibmcapsule.usertrackservice.domain.Track;
import com.ibmcapsule.usertrackservice.domain.User;
import com.ibmcapsule.usertrackservice.exception.TrackAlreadyExistException;
import com.ibmcapsule.usertrackservice.service.UserTrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserTrackController.class)
public class UserTrackControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserTrackService userTrackService;

  private Artist artist;
  private Image image;
  private Track track;
  private User user;
  private List<Track> trackList;

  @Before
  public void setUp() throws Exception {
    trackList = new ArrayList<>();
    image = new Image(123, "http:url", "large");
    artist = new Artist("123", "John", "http:url",image);
    track = new Track("Track123", "MyNewTrack","Old Comments", "12345", "http:url", artist);
    trackList.add(track);

    user = new User("John", "john@gmail.com", trackList);
  }

  @After
  public void tearDown() throws Exception {
    trackList = null;
    image = null;
    artist = null;
    track = null;
    user = null;
  }

  @Test
  public void testSaveTrackSuccess() throws Exception {
    when(userTrackService.saveUserTrackToWishlist(track, user.getUsername())).thenReturn(user);
    mockMvc.perform(post("/api/v1/usertrackservice/user/{username}/track",user.getUsername())
    .contentType(MediaType.APPLICATION_JSON)
    .content(jsonToString(track)))
      .andExpect(status().isCreated())
      .andDo(print());

    verify(userTrackService, times(1)).saveUserTrackToWishlist(any(), eq(user.getUsername()));
  }

  @Test
  public void testSaveTrackFailure() throws Exception {
    when(userTrackService.saveUserTrackToWishlist(track, user.getUsername())).thenThrow(TrackAlreadyExistException.class);
    mockMvc.perform(post("/api/v1/usertrackservice/user/{username}/track",user.getUsername())
      .contentType(MediaType.APPLICATION_JSON)
      .content(jsonToString(track)))
      .andExpect(status().isCreated())
      .andDo(print());
    verify(userTrackService, times(1)).saveUserTrackToWishlist(any(), eq(user.getUsername()));
  }

  @Test
  public void testUpdateCommentSuccess() throws Exception {
    when(userTrackService.updateCommentsForTracks(track.getComments(), track.getTrackId(), user.getUsername())).thenReturn(user);
    mockMvc.perform(patch("/api/v1/usertrackservice/user/{username}/track",user.getUsername())
      .contentType(MediaType.APPLICATION_JSON)
      .content(jsonToString(track)))
      .andExpect(status().isOk())
      .andDo(print());
    verify(userTrackService, times(1)).updateCommentsForTracks(track.getComments(), track.getTrackId(), user.getUsername());
  }

  @Test
  public void testDeleteTrack() throws Exception {
    when(userTrackService.deleteUserTrackFromWishlist(user.getUsername(), track.getTrackId())).thenReturn(user);
    mockMvc.perform(delete("/api/v1/usertrackservice/user/{username}/track",user.getUsername())
      .contentType(MediaType.APPLICATION_JSON)
      .content(jsonToString(track)))
      .andExpect(status().isOk())
      .andDo(print());
    verify(userTrackService, times(1)).deleteUserTrackFromWishlist(user.getUsername(), track.getTrackId());
  }

  @Test
  public void getAllTracksFromWishlistTest() throws Exception {
    when(userTrackService.getAllUserTracksFromWishlist(user.getUsername())).thenReturn(trackList);
    mockMvc.perform(get("/api/v1/usertrackservice/user/{username}/tracks",user.getUsername())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andDo(print());
    verify(userTrackService, times(1)).getAllUserTracksFromWishlist(user.getUsername());
  }

  private static String jsonToString(final Object obj) throws JsonProcessingException {
    String result = null;
    try{
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonContent = objectMapper.writeValueAsString(obj);
      result = jsonContent;
    } catch (JsonProcessingException e){
      result = "Json Processing Error";
    }
    return result;
  }
}
