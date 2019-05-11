package com.ibmcapsule.usertrackservice.service;

import com.ibmcapsule.usertrackservice.domain.Track;
import com.ibmcapsule.usertrackservice.domain.User;
import com.ibmcapsule.usertrackservice.exception.TrackAlreadyExistException;
import com.ibmcapsule.usertrackservice.exception.TrackNotFoundException;
import com.ibmcapsule.usertrackservice.exception.UserAlreadyExistException;

import java.util.List;

public interface UserTrackService {
  public User saveUserTrackToWishlist(Track track, String userName) throws TrackAlreadyExistException;
  public User deleteUserTrackFromWishlist(String username, String trackId) throws TrackNotFoundException;
  public User updateCommentsForTracks(String comments, String trackId, String username) throws TrackNotFoundException;
  public List<Track> getAllUserTracksFromWishlist(String username);
  public User registerUser(User user) throws UserAlreadyExistException;
}
