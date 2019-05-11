package com.ibmcapsule.usertrackservice.controller;

import com.ibmcapsule.usertrackservice.domain.Track;
import com.ibmcapsule.usertrackservice.domain.User;
import com.ibmcapsule.usertrackservice.exception.TrackAlreadyExistException;
import com.ibmcapsule.usertrackservice.exception.TrackNotFoundException;
import com.ibmcapsule.usertrackservice.exception.UserAlreadyExistException;
import com.ibmcapsule.usertrackservice.service.UserTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/usertrackservice")
public class UserTrackController {

  @Autowired
  private UserTrackService userTrackService;

  private ResponseEntity responseEntity;

  @PostMapping("/register")
  public ResponseEntity registerUser(@RequestBody User user) throws UserAlreadyExistException {

    try{
      userTrackService.registerUser(user);
      responseEntity = new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
    catch(UserAlreadyExistException e){
      throw new UserAlreadyExistException();
    }
    return responseEntity;
  }

  @PostMapping("/user/{username}/track")
  public ResponseEntity saveUserTrackToWishList(@RequestBody Track track, @PathVariable("username") String username) throws TrackAlreadyExistException{
    System.out.println("Username: "+ username + "Track: " + track);
    try
    {
      User user = userTrackService.saveUserTrackToWishlist(track, username);
      responseEntity = new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
    catch(TrackAlreadyExistException e){
      throw new TrackAlreadyExistException();
    }
    catch(Exception e){
      responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return responseEntity;
  }

  @DeleteMapping("/user/{username}/track")
  public ResponseEntity deleteUserTrackFromWishlist(@PathVariable("username") String username, @RequestBody Track track) throws TrackNotFoundException {
    try {
      User user = userTrackService.deleteUserTrackFromWishlist(username, track.getTrackId());
      responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);
    } catch (TrackNotFoundException e) {
      throw new TrackNotFoundException();
    } catch(Exception e){
      responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
  }

  @PatchMapping("/user/{username}/track")
  public ResponseEntity updateCommentForUserTrack(@RequestBody Track track, @PathVariable("username") String username) throws TrackNotFoundException{
    try{
      User user = userTrackService.updateCommentsForTracks(track.getComments(), track.getTrackId(), username);
      responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);
    } catch(TrackNotFoundException e){
      throw new TrackNotFoundException();
    } catch(Exception e){
      responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
  }

  @GetMapping("/user/{username}/tracks")
  public ResponseEntity getAllUserTrackFromWishlist(@PathVariable("username") String username){
    try{
      responseEntity = new ResponseEntity(userTrackService.getAllUserTracksFromWishlist(username), HttpStatus.OK);
    } catch(Exception e){
      responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
  }

}
