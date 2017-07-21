package com.npxception.demo.controller;

import com.npxception.demo.entity.Role;
import com.npxception.demo.helperMethods.UserInformation;
import com.npxception.demo.service.AuthenticationService;
import com.npxception.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Atharva Jakkanwar on 03-Jul-17.
 * Represents a controller for the Authentication service.

 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authService;

  @Autowired
  private UserService userService;

//  @RequestMapping(method = RequestMethod.GET, value = "/login")
//  public void login(HttpServletResponse httpServletResponse) {
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    String email = new AuthenticationController().getEmail();
//    com.npxception.demo.entity.User user = userService.getUserByEmail(email);
//    String fullName = new UserInformation().getFullName(user.getFirstName(), user.getLastName());
//    if (authentication.isAuthenticated()){
//      try {
//        httpServletResponse.sendRedirect("http://localhost:8081/" + fullName);
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//  }

  @RequestMapping(method = RequestMethod.GET, value = "/login/{roleid}")
  public Role getRoleById(@PathVariable int roleid) {
    return this.authService.getRoleById(roleid);
  }

  @RequestMapping(value = "/useremail", method = RequestMethod.GET)
  public String getEmail() {
    return new UserInformation().getEmail();
  }
}
