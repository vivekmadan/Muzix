package com.ibmcapsule.muzixservice.service;

import com.ibmcapsule.muzixservice.domain.Artist;
import com.ibmcapsule.muzixservice.domain.Image;
import com.ibmcapsule.muzixservice.domain.Track;
import com.ibmcapsule.muzixservice.exception.TrackAlreadyExistException;
import com.ibmcapsule.muzixservice.exception.TrackNotFoundException;
import com.ibmcapsule.muzixservice.repository.MuzixRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

//@RunWith(SpringRunner.class)
public class MuzixServiceTest {

    @Mock
    private MuzixRepository muzixRepository;

    private Optional optional;
    private Track track;
    private Image image;
    private Artist artist;
    private List<Track> listTrack = null;

    @InjectMocks
    private MuzixServiceServiceImpl muzixService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        image = new Image(1, "http:url", "large");
        artist = new Artist("101", "John", "new URL", image);
        track = new Track("Track1", "MyNewTrack", "New Comments", "123", "new track url", artist);
        listTrack = new ArrayList<>();
        listTrack.add(track);
        optional = Optional.of(track);
    }

    @After
    public void tearDown(){
        image = null;
        artist = null;
        track = null;
    }

    @Test
    public void testSaveTrackSuccess() throws TrackAlreadyExistException {
        when(muzixRepository.insert(track)).thenReturn(track);
        Track fetchTrack = muzixService.saveTrackToWishList(track);
        Assert.assertEquals(track, fetchTrack);
        verify(muzixRepository, times(1)).insert(track);
        verify(muzixRepository, times(1)).findById(track.getTrackId());

    }

    @Test(expected = TrackAlreadyExistException.class)
    public void testSaveTrackFailure() throws TrackAlreadyExistException{
        when(muzixRepository.insert(track)).thenReturn(track);
        when(muzixRepository.findById(track.getTrackId())).thenReturn(optional);
        Track fetchTrack = muzixService.saveTrackToWishList(track);
        Assert.assertEquals(track, fetchTrack);
        verify(muzixRepository, times(1)).insert(track);
        verify(muzixRepository, times(1)).findById(track.getTrackId());
    }

    @Test
    public void testUpdateCommentsSuccess() throws TrackNotFoundException{
        when(muzixRepository.findById(track.getTrackId())).thenReturn(optional);
        track.setComments("comments updated");
        Track fetchTrack = muzixService.updateCommentForTrack(track.getComments(), track.getTrackId());
        System.out.println("fetch track" + fetchTrack.toString());
        Assert.assertEquals(fetchTrack.getComments(), "comments updated");

        verify(muzixRepository, times(1)).save(track);
        verify(muzixRepository, times(2)).findById(track.getTrackId());


    }

    @Test
    public void testDeleteTrack() throws TrackNotFoundException{
        when(muzixRepository.findById(track.getTrackId())).thenReturn(optional);
        boolean fetchTrack = muzixService.deleteTrackFromWishList(track.getTrackId());
        Assert.assertEquals(true, fetchTrack);
        verify(muzixRepository, times(1)).findById(track.getTrackId());
        verify(muzixRepository, times(1)).deleteById(track.getTrackId());
    }

    @Test
    public void testGetAllTrack() throws Exception{
        when(muzixRepository.findAll()).thenReturn(listTrack);
        List<Track> list = muzixService.getAllTrackFromWishList();
        Assert.assertEquals(list, listTrack);
        verify(muzixRepository, times(1)).findAll();
    }
}
