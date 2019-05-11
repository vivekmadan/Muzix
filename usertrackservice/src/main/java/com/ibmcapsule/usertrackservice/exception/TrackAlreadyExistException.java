package com.ibmcapsule.usertrackservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Track Already Exist")
public class TrackAlreadyExistException extends Exception{
}
