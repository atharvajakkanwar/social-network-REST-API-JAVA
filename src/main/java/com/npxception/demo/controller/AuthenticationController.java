package com.npxception.demo.controller;

import com.npxception.demo.entity.Role;
import com.npxception.demo.exceptions.AuthenticationException;
import com.npxception.demo.helperMethods.UserInformation;
import com.npxception.demo.service.AuthenticationService;
import com.npxception.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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
public class AuthenticationController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private AuthenticationService authService;

  @Autowired
  private UserService userService;

  public String getEmail() {
    return new UserInformation().getEmail();
  }

  public String getHeader() {
    return  new UserInformation().getHeaders();
  }

  @RequestMapping(value = "{userid}/verify", method = RequestMethod.GET)
  public void verify(@PathVariable("userid") int id, @RequestHeader("authorization") String token) {
    try {
      System.out.println("TOKEN VERIFY: :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::;"+ token);
      String sql2 = "UPDATE loginfo SET token = ? WHERE userid = ?";
      jdbcTemplate.update(sql2, new Object[]{token, id});
    } catch (EmptyResultDataAccessException except){
      throw new AuthenticationException((id));
    }
  }

}