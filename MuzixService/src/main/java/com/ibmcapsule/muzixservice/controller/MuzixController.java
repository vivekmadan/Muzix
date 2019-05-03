package com.ibmcapsule.muzixservice.controller;

import com.ibmcapsule.muzixservice.domain.Track;
import com.ibmcapsule.muzixservice.exception.TrackAlreadyExistException;
import com.ibmcapsule.muzixservice.exception.TrackNotFoundException;
import com.ibmcapsule.muzixservice.service.MuzixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/muzixservice")
public class MuzixController {

    @Autowired
    private MuzixService muzixService;
    private ResponseEntity<?> responseEntity;

    @PostMapping("track")
    @ExceptionHandler(TrackAlreadyExistException.class)
    public ResponseEntity<?> saveTrackToWishList(@RequestBody Track track) throws TrackAlreadyExistException {
        try {
            muzixService.saveTrackToWishList(track);
            responseEntity = new ResponseEntity<>(track, HttpStatus.CREATED);
        } catch (TrackAlreadyExistException e) {
            e.printStackTrace();
            throw new TrackAlreadyExistException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error, Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackFromWishList(@PathVariable("id") String id) throws TrackNotFoundException{
        try
        {
            boolean deleteFlag = muzixService.deleteTrackFromWishList(id);
            responseEntity = new ResponseEntity<>("Successfully deleted !!!", HttpStatus.OK);
        }
        catch(TrackNotFoundException e) {
            throw new TrackNotFoundException();
        }
        catch(Exception e)
        {
            responseEntity = new ResponseEntity<>("Error !!! try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateCommentForTrack(@RequestBody Track track, @PathVariable("id") String id) throws TrackNotFoundException{
        try
        {
            Track updatedTrack = muzixService.updateCommentForTrack(track.getComments(), id);
            responseEntity = new ResponseEntity<>(track, HttpStatus.OK);
        }
        catch(TrackNotFoundException e){
            throw new TrackNotFoundException();
        }
        catch(Exception e){
            responseEntity = new ResponseEntity<>("Error !!! Try aftre sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracksFromWishList(){
        try
        {
            responseEntity = new ResponseEntity<>(muzixService.getAllTrackFromWishList(), HttpStatus.OK);
        }
        catch(Exception e){
            responseEntity = new ResponseEntity<>("Error !!! Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

}
