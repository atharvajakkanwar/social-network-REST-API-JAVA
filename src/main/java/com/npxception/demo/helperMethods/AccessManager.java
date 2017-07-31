package com.npxception.demo.helperMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Atharva Jakkanwar on 30-Jul-17.
 */
public class AccessManager {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public AccessManager() {}

  public boolean check(int userid, String email, String password) {
    //check if {userid} from request
    // and email and password from login details
    // match those in the loginfo table
    //if yes return true, else return false

    // example
    //request : // 1/friends
    //username: user1@gmail.com
    //password: pass

    // in table: 1|user1@gmail.com|pass
    //this will return true

    // example
    //request : // 1/friends
    //username: user99@gmail.com
    //password: pass99

    // in table: 1|user1@gmail.com|pass
    //this will return false
    return false;
  }
}
