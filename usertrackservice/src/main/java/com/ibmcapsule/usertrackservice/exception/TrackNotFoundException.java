package com.ibmcapsule.usertrackservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "TrackDTO Not Found")
public class TrackNotFoundException extends Exception {
}
