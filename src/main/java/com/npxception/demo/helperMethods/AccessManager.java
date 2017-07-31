package com.npxception.demo.helperMethods;

import com.npxception.demo.exceptions.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Atharva Jakkanwar on 30-Jul-17.
 */
@Service
public class AccessManager {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public AccessManager() {}

  public void checkUser(int id, String token){
    try {
      System.out.println("Line 22\n");
      String database_token_sql = "SELECT token FROM loginfo WHERE userid = ?";
      System.out.println("Line 24\n");
      String database_token = jdbcTemplate.queryForObject(database_token_sql, new Object[]{id}, String.class);
      System.out.println("Line 26\n");
      String database_id_sql = "SELECT userid FROM loginfo WHERE token = ?";
      System.out.println("Line 28\n");
      Integer database_id = jdbcTemplate.queryForObject(database_id_sql, new Object[]{token}, Integer.class);
      if ((!database_id.equals(id)) || (!database_token.equals(token))){
        System.out.println("Line 31\n");
        throw new AuthenticationException(id);
      }
    }
    catch (EmptyResultDataAccessException except){
      throw new AuthenticationException((id));
    }
  }
}