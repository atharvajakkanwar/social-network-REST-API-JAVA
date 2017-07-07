package com.npxception.demo.HelperMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by RachelDi on 29/06/2017.
 */
public class Usernames {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  final String GET_ID_BY_NAME = "SELECT userid FROM users WHERE firstname =? AND lastname = ?";

  public String[] splitName(String name){
    String[] result = name.split(".");
    return result;
  }

  public int getIdByname(String[] names){
    return jdbcTemplate.queryForObject(GET_ID_BY_NAME,
        new Object[]{names[0], names[1]}, Integer.class);
  }
}
