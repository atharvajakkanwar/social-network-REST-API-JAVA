package com.npxception.demo.controller;

import com.npxception.demo.entity.Role;
import com.npxception.demo.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by Atharva Jakkanwar on 03-Jul-17.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authService;


@RequestMapping(method = RequestMethod.GET, value = "/login")
public RedirectView redirectToUser() {

  //if user exists
  // if yes => int id = getUserid

  RedirectView redirectView = new RedirectView();
  int id =5;
  redirectView.setUrl(id+"/posts/");
  return redirectView;
}
//  }
}
