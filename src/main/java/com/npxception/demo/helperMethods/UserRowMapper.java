package com.npxception.demo.helperMethods;

import com.npxception.demo.entity.User;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by RachelDi on 18/06/2017.
 */
public class UserRowMapper implements RowMapper<User> {
  @Override
  public User mapRow(ResultSet resultSet, int i) throws SQLException {

    return new User(
        resultSet.getInt("userid"),
        resultSet.getString("firstname"),
        resultSet.getString("lastname"),
        resultSet.getString("email"),
        resultSet.getInt("age"),
        resultSet.getString("gender"),
        resultSet.getString("country"),
        resultSet.getString("city"),
        resultSet.getString("password"),
        resultSet.getString("role"));
  }
}