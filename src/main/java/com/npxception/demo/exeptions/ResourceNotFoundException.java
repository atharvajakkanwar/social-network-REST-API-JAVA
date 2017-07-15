package com.npxception.demo.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Atharva Jakkanwar on 15-Jul-17.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="The resource you were trying to reach is not found.")
public class ResourceNotFoundException extends  RuntimeException {
  public ResourceNotFoundException(String msg) {
    super(msg + " not found!");
  }
}
