package com.ibmcapsule.muzixservice.service;

import com.ibmcapsule.muzixservice.domain.Track;
import com.ibmcapsule.muzixservice.exception.TrackAlreadyExistException;
import com.ibmcapsule.muzixservice.exception.TrackNotFoundException;

import java.util.List;

public interface MuzixService {
    Track saveTrackToWishList(Track track) throws TrackAlreadyExistException;
    boolean deleteTrackFromWishList(String id) throws TrackNotFoundException;
    Track updateCommentForTrack(String comment, String id) throws TrackNotFoundException;
    List<Track> getAllTrackFromWishList() throws Exception;
}
