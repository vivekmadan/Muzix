package com.ibmcapsule.usertrackservice.service;

import com.ibmcapsule.usertrackservice.config.Producer;
import com.ibmcapsule.usertrackservice.domain.Artist;
import com.ibmcapsule.usertrackservice.domain.Image;
import com.ibmcapsule.usertrackservice.domain.Track;
import com.ibmcapsule.usertrackservice.domain.User;
import com.ibmcapsule.usertrackservice.exception.TrackAlreadyExistException;
import com.ibmcapsule.usertrackservice.exception.TrackNotFoundException;
import com.ibmcapsule.usertrackservice.repository.UserTrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserTrackServiceTest {

  @Mock
  private UserTrackRepository userTrackRepository;

  @Mock
  Producer producer;

  private User user;
  private Track track;
  private Artist artist;
  private List<Track> list;

  @InjectMocks
  private UserTrackServiceImpl userTrackService;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    Image image = new Image(123, "http:url", "large");
    artist = new Artist("123","John","http:url",image);
    track = new Track("Track1","MyNewTrack","New Comments","1234","http:url",artist);
    list = new ArrayList<>();
    list.add(track);
    user = new User("John","john@gmail.com", list);
  }

  @After
  public void tearDown() throws Exception {
    userTrackRepository.deleteAll();
    user = null;
    track = null;
    list = null;
    artist = null;
  }

  @Test
  public void testSaveUserTrackSuccess() throws TrackAlreadyExistException {
    user = new User("John123", "john@gmail.com", null);
    when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
    User fetchUser = userTrackService.saveUserTrackToWishlist(track, user.getUsername());
    Assert.assertEquals(fetchUser, user);
    verify(userTrackRepository, times(1)).findByUsername(user.getUsername());
    verify(userTrackRepository, times(1)).save(user);
  }

  @Test
  public void testDeleteUserTrackFromWishlist() throws TrackNotFoundException {
    when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
    User fetchUser = userTrackService.deleteUserTrackFromWishlist(user.getUsername(),track.getTrackId());
    Assert.assertEquals(fetchUser, user);
    verify(userTrackRepository, times(1)).findByUsername(user.getUsername());
    verify(userTrackRepository, times(1)). save(user);
  }

  @Test
  public void testUpdateCommentsForTrack() throws TrackNotFoundException {
    when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
    User fetchUser = userTrackService.updateCommentsForTracks("This is new comment", track.getTrackId(), user.getUsername());
    Assert.assertEquals(fetchUser.getTrackList().get(0).getComments(), "This is new comment");
    verify(userTrackRepository, times(1)).findByUsername(user.getUsername());
    verify(userTrackRepository, times(1)).save(user);
  }

  @Test
  public void testGetAllUserTracks() throws Exception {
    when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
    List<Track> fetchList = userTrackService.getAllUserTracksFromWishlist(user.getUsername());
    Assert.assertEquals(fetchList, list);
    verify(userTrackRepository, times(1)).findByUsername(user.getUsername());

  }
}
