package com.npxception.demo.helperMethods;

import com.npxception.demo.entity.User;
import com.npxception.demo.exceptions.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
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
      String database_token_sql = "SELECT token FROM loginfo WHERE userid = ?";
      String database_token = jdbcTemplate.queryForObject(database_token_sql, new Object[]{id}, String.class);
      System.out.println("TOKEN::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: " + database_token);
      String database_id_sql = "SELECT userid FROM loginfo WHERE token = ?";
      Integer database_id = jdbcTemplate.queryForObject(database_id_sql, new Object[]{token}, Integer.class);
      System.out.println("ID::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: "+ database_id);
      if ((!database_id.equals(id)) || (!database_token.equals(token))){
        System.out.println("HULUALALALALALALALALALALALALALALALALAL");
        System.out.println("DB_ID:" + database_id);
        System.out.println("ID:" + id);
        System.out.println("DB_TOKEn:" + database_token);
        System.out.println("TOKEN:" + id);
        throw new AuthenticationException(id);
      }
    }
    catch (EmptyResultDataAccessException except){
      throw new AuthenticationException((id));
    }
  }
}