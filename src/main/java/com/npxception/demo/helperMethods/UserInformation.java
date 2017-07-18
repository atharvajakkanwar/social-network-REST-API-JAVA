package com.npxception.demo.helperMethods;

import com.npxception.demo.dao.PostgreSQLUserDaoImpl;
import com.npxception.demo.entity.User;
import com.npxception.demo.service.UserService;

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

  @Autowired
  private UserService userService;

  final String GET_ID_BY_NAME = "SELECT userid FROM users WHERE firstname =? AND lastname = ?";

  public String[] splitUserNameWithDot(String name){
    String[] result = name.split("\\.");
    return result;
  }

  public String[] splitUserNameWithoutDot(String name) {
    String[] result = name.split(" ");
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

  public String getFullNameById(int id){
    User user = userService.getUserById(id);
    String name = user.getFirstName() + " " + user.getFirstName();
    return name;
  }
}

