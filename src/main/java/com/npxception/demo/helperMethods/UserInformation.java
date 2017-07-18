package com.npxception.demo.helperMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by RachelDi on 29/06/2017.
 */
public class UserInformation {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  final String GET_ID_BY_NAME = "SELECT userid FROM users WHERE firstname =? AND lastname = ?";

  public String[] splitUserName(String name){
    String[] result = name.split("\\.");
    return result;
  }

  public int getIdByFullName(String[] names){
    return jdbcTemplate.queryForObject(GET_ID_BY_NAME,
        new Object[]{names[0], names[1]}, Integer.class);
  }

  public String getFullName(String firstName, String lastName){
    return firstName + "." + lastName;
  }


  public String getEmail() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    return currentPrincipalName;
  }

}
