package com.ibmcapsule.muzixservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Track with specified id is not found")
public class TrackNotFoundException extends Exception {
}
