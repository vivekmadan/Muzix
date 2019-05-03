package com.ibmcapsule.muzixservice.repository;

import com.ibmcapsule.muzixservice.domain.Artist;
import com.ibmcapsule.muzixservice.domain.Image;
import com.ibmcapsule.muzixservice.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MuzixRepositoryTest {

    @Autowired
    private MuzixRepository muzixRepository;

    private Image image;
    private Artist artist;
    private Track track;

    @Before
    public void setUp(){
        image = new Image(1, "http:url", "large");
        artist = new Artist("101", "John", "new URL", image);
        track = new Track("Track1", "MyNewTrack", "New Comments", "123", "new track URL", artist);
    }

    @After
    public void tearDown(){
        image = null;
        artist = null;
        track = null;
    }

    @Test
    public void testSaveTrack(){
        muzixRepository.insert(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(fetchTrack.getTrackName(), track.getTrackName());
    }

    @Test
    public void testUpdateComments(){
        track = new Track("Track201", "Track201", "Track 201 Comments", "123", "httP:new url", artist);
        muzixRepository.insert(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        fetchTrack.setComments("Updating the comments");
        muzixRepository.save(fetchTrack);
        Track updatedFetchTrack = muzixRepository.findById(track.getTrackId()).get();
        Assert.assertEquals("Updating the comments", updatedFetchTrack.getComments());
    }

    @Test
    public void testDeleteTrack(){
        track = new Track("Track2012", "Track2012", "Track 2012 Comments", "123", "httP:new url", artist);
        muzixRepository.insert(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        muzixRepository.delete(fetchTrack);
        Assert.assertEquals(Optional.empty(), muzixRepository.findById(track.getTrackId()));
    }

    @Test
    public void testGetAllTracks(){
        muzixRepository.deleteAll();
        track = new Track("Track20129", "Track20129", "Track 20129 Comments", "123", "httP:new url", artist);
        muzixRepository.insert(track);
        Image image = new Image(2, "http:url", "large2");
        Artist artist = new Artist("201", "Oliver", "new URL", image);
        track = new Track("Track2", "MyNewTrack2", "New Comments2", "1234", "new track URL2", artist);
        muzixRepository.insert(track);
        List<Track> tracks = muzixRepository.findAll();
        Assert.assertEquals(2, tracks.size());
        Assert.assertEquals("Track2", tracks.get(1).getTrackId());
    }

}
