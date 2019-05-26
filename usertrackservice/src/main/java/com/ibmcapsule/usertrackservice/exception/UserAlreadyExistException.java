package com.ibmcapsule.usertrackservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "UserDTO Already Exists")
public class UserAlreadyExistException extends Exception {
}
