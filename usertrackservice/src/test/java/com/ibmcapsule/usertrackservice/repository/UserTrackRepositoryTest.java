package com.ibmcapsule.usertrackservice.repository;

import com.ibmcapsule.usertrackservice.domain.Artist;
import com.ibmcapsule.usertrackservice.domain.Image;
import com.ibmcapsule.usertrackservice.domain.Track;
import com.ibmcapsule.usertrackservice.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserTrackRepositoryTest {

  @Autowired
  private UserTrackRepository userTrackRepository;

  private Track track;
  private User user;

  @Before
  public void setUp(){
    Image image = new Image(1, "http:url", "large");
    Artist artist = new Artist("101", "John", "new url", image);
    Track track = new Track("Track1","MyNewTrack", "new Comments", "1234","http:url",artist);
    List<Track> trackList = new ArrayList<>();
    trackList.add(track);
    user = new User("John","john@gmail.com",trackList);
  }

  @After
  public void tearDown() throws Exception {
    userTrackRepository.deleteAll();
  }

  @Test
  public void testSaveUserTrack() {
    userTrackRepository.save(user);
    User fetchUser = userTrackRepository.findByUsername(user.getUsername());
    List<Track> list = fetchUser.getTrackList();
    Assert.assertEquals(list.get(0).getTrackId(), user.getTrackList().get(0).getTrackId());
  }
}
