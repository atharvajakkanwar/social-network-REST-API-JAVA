package com.npxception.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by RachelDi on 30/07/2017.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Incorrect credentials!")
public class AuthenticationException extends RuntimeException{
  public AuthenticationException(int userid) {
    super("Incorrect credentials for user: " + userid);
  }
}