package com.ibmcapsule.usertrackservice.service;

import com.ibmcapsule.usertrackservice.domain.Track;
import com.ibmcapsule.usertrackservice.domain.User;
import com.ibmcapsule.usertrackservice.exception.TrackAlreadyExistException;
import com.ibmcapsule.usertrackservice.exception.TrackNotFoundException;
import com.ibmcapsule.usertrackservice.exception.UserAlreadyExistException;
import com.ibmcapsule.usertrackservice.repository.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTrackServiceImpl implements UserTrackService {

  @Autowired
  private UserTrackRepository userTrackRepository;

  @Override
  public User saveUserTrackToWishlist(Track track, String userName) throws TrackAlreadyExistException {
      User fetchedUser = userTrackRepository.findByUsername(userName);
        List<Track> trackList = fetchedUser.getTrackList();

        if (trackList != null && trackList.size() > 0) {
          for (Track trackObj : trackList) {
            if (trackObj.getTrackId().equals(track.getTrackId()))
              throw new TrackAlreadyExistException();
          }
        } else {
          trackList = new ArrayList<>();
        }
          trackList.add(track);
          fetchedUser.setTrackList(trackList);
          userTrackRepository.save(fetchedUser);


    return fetchedUser;
  }

  @Override
  public User deleteUserTrackFromWishlist(String username, String trackId) throws TrackNotFoundException {
    User fetchUser = userTrackRepository.findByUsername(username);
    List<Track> trackList = fetchUser.getTrackList();

    if(trackList != null && trackList.size() > 0)
    {
      for(int i = 0; i < trackList.size(); i++)
      {
        if(trackId.equals(trackList.get(i).getTrackId()))
        {
          trackList.remove(i);
          fetchUser.setTrackList(trackList);
          userTrackRepository.save(fetchUser);
          break;
        }
      }
    }
    else
    {
      throw new TrackNotFoundException();
    }
    return fetchUser;
  }

  @Override
  public User updateCommentsForTracks(String comments, String trackId, String username) throws TrackNotFoundException {
    User fetchUser = userTrackRepository.findByUsername(username);
    List<Track> trackList = fetchUser.getTrackList();

    if(trackList != null && trackList.size() > 0)
    {
      for(int i = 0; i < trackList.size(); i++)
      {
        if(trackId.equals(trackList.get(i).getTrackId()))
        {
          trackList.get(i).setComments(comments);
          userTrackRepository.save(fetchUser);
          break;
        }
      }
    }
    else
    {
      throw new TrackNotFoundException();
    }
    return fetchUser;
  }

  @Override
  public List<Track> getAllUserTracksFromWishlist(String username) {
    User fetchUser = userTrackRepository.findByUsername(username);
    return fetchUser.getTrackList();
  }

  @Override
  public User registerUser(User user) throws UserAlreadyExistException {
    User fetchedUser = userTrackRepository.findByUsername(user.getUsername());
    if(fetchedUser != null)
    {
      throw new UserAlreadyExistException();
    }
    else
    {
      userTrackRepository.save(user);
    }
    return user;
  }
}
