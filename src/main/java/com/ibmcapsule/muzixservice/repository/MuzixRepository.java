package com.ibmcapsule.muzixservice.repository;

import com.ibmcapsule.muzixservice.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MuzixRepository extends MongoRepository<Track, String> {
}
