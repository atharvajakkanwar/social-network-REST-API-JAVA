package com.npxception.demo.config;

import com.npxception.demo.controller.AuthenticationController;
import com.npxception.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
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
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private UserService userService;
  public static int userid = 0;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse,
                                      Authentication authentication)
      throws IOException, ServletException {
//    String sql = "TRUNCATE loginfo";
//    jdbcTemplate.update(sql);
    AuthenticationController auth = new AuthenticationController();
    String email = auth.getEmail();
    String header = auth.getHeader();
    com.npxception.demo.entity.User user = userService.getUserByEmail(email);
    String password = user.getPassword();
    userid = user.getId();
    String sql2 = "INSERT INTO loginfo (userid, email, password, token) " +
        "SELECT ?, ?, ?, ? WHERE NOT EXISTS (SELECT * FROM loginfo WHERE userid = ?)";
    jdbcTemplate.update(sql2, new Object[]
        {userid, email, password,
            "nokey", userid});
  }
}
