package com.npxception.demo.helperMethods;

import com.npxception.demo.controller.AuthenticationController;
import com.npxception.demo.dao.PostgreSQLUserDaoImpl;
import com.npxception.demo.entity.User;
import com.npxception.demo.exceptions.AuthenticationException;
import com.npxception.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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

  public String getHeaders() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentHeader = authentication.getDetails().toString();
    return currentHeader;
  }

//  public String getPassword() {
//   // UserDetails user=userDetailsService.loadUserByUsername(username);
//    AbstractAuthenticationToken authentication = (AbstractAuthenticationToken)
//        SecurityContextHolder.getContext().getAuthentication();
//    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, oldPassword));
//
//    UserDetails user = (UserDetails)authentication.getDetails();
//    String password = user.getPassword();
//    System.out.println(password);
//    return password;
//  }

  public String getFullNameById(int id){
    User user = userService.getUserById(id);
    String name = user.getFirstName() + " " + user.getFirstName();
    return name;
  }

//  public void checkUser(int id){
////    User user = userService.getUserById(id);
//    String sql0 = "SELECT * FROM users WHERE userid = id";
//    User user = jdbcTemplate.queryForObject(sql0, new Object[]{id}, new UserRowMapper());
//    String email = user.getEmail();
//    String password = user.getPassword();
//    String sql1 = "SELECT email FROM logingfo";
//    String sql2 = "SELECT password FROM loginfo";
//    String loginEmail = jdbcTemplate.queryForObject(sql1, new Object[]{}, String.class);
//    System.out.println(loginEmail);
//    String loginPass = jdbcTemplate.queryForObject(sql2, new Object[]{}, String.class);
//    System.out.println(loginPass);
//    if (email != loginEmail || password != loginPass){
//       throw new AuthenticationException(id);
//    }
//  }
}
