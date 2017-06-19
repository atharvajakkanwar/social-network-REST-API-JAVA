package com.npxception.demo.mapper;

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
    User user = new User();
    user.setId(resultSet.getInt("userid"));
    user.setFirstName(resultSet.getString("firstname"));
    user.setLastName(resultSet.getString("lastname"));
    user.setEmail(resultSet.getString("email"));
    user.setAge(resultSet.getInt("age"));
    user.setGender(resultSet.getString("gender"));
    user.setCountry(resultSet.getString("country"));
    user.setCity(resultSet.getString("city"));
    user.setPassword(resultSet.getString("password"));
    return user;
  }
}

