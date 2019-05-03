package com.ibmcapsule.muzixservice.service;

import com.ibmcapsule.muzixservice.domain.Track;
import com.ibmcapsule.muzixservice.exception.TrackAlreadyExistException;
import com.ibmcapsule.muzixservice.exception.TrackNotFoundException;
import com.ibmcapsule.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuzixServiceServiceImpl implements  MuzixService {


    private MuzixRepository muzixRepository;

    @Autowired
    public MuzixServiceServiceImpl(MuzixRepository muzixRepository)
    {
        this.muzixRepository = muzixRepository;
    }

    @Override
    public Track saveTrackToWishList(Track track) throws TrackAlreadyExistException {
        Optional optional = muzixRepository.findById(track.getTrackId());

        if(optional.isPresent())
        {
            throw new TrackAlreadyExistException();
        }
        muzixRepository.insert(track);
        return track;
    }

    @Override
    public boolean deleteTrackFromWishList(String id) throws TrackNotFoundException {
        boolean deleteFlag = false;

        Optional optional = muzixRepository.findById(id);

        if(optional.isPresent())
        {
            muzixRepository.deleteById(id);
            deleteFlag = true;
        }
        else
        {
            throw new TrackNotFoundException();
        }
        return deleteFlag;
    }

    @Override
    public Track updateCommentForTrack(String comment, String id) throws TrackNotFoundException {
        Track track = null;
        Optional optional = muzixRepository.findById(id);

        if(optional.isPresent())
        {
            track = muzixRepository.findById(id).get();
            track.setComments(comment);
            muzixRepository.save(track);
        }
        else
        {
            throw new TrackNotFoundException();
        }
        return track;
    }

    @Override
    public List<Track> getAllTrackFromWishList() throws Exception {
        return muzixRepository.findAll();
    }
}
