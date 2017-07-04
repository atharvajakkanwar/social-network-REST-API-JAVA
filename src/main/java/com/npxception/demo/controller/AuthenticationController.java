package com.npxception.demo.controller;

import com.npxception.demo.entity.Role;
import com.npxception.demo.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Atharva Jakkanwar on 03-Jul-17.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authService;

  @RequestMapping(method = RequestMethod.GET, value = "/login/{roleid}")
  public Role getRoleById(@PathVariable int roleid) {
    return this.authService.getRoleById(roleid);
  }
}
