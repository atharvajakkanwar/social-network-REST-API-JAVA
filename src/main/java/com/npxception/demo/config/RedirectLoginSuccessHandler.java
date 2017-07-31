package com.npxception.demo.config;

import com.npxception.demo.controller.AuthenticationController;
import com.npxception.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by RachelDi on 17/07/2017.
 */
public class RedirectLoginSuccessHandler implements AuthenticationSuccessHandler {

  @Autowired
  private UserService userService;
  public static int userid = 0;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse,
                                      Authentication authentication)
      throws IOException, ServletException {
    String email = new AuthenticationController().getEmail();
    com.npxception.demo.entity.User user = userService.getUserByEmail(email);
    userid = user.getId();
  }
}
