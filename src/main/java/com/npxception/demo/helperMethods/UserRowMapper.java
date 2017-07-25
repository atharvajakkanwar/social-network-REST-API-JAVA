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

<<<<<<< HEAD
    User user = new User();//return new User(
//        resultSet.getInt("userid"),
    user.setAge(resultSet.getInt("age"));
    user.setCity(resultSet.getString("city"));
    user.setCountry(resultSet.getString("country"));
    user.setEmail(resultSet.getString("email"));
    user.setFirstName(resultSet.getString("firstname"));
    user.setGender(resultSet.getString("gender"));
    user.setId(resultSet.getInt("userid"));
    user.setLastName(resultSet.getString("lastname"));
    user.setPassword(resultSet.getString("password"));
    user.setRole(resultSet.getString("role"));
//        resultSet.getString("firstname"),
//        resultSet.getString("lastname"),
//        resultSet.getString("email"),
//        resultSet.getInt("age"),
//        resultSet.getString("gender"),
//        resultSet.getString("country"),
//        resultSet.getString("city"),
//        resultSet.getString("password"),
//        resultSet.getString("role");
    return user;
=======
    return new User(
        //resultSet.getInt("userid"),
        resultSet.getString("firstname"),
        resultSet.getString("lastname"),
        resultSet.getString("email"),
        resultSet.getInt("age"),
        resultSet.getString("gender"),
        resultSet.getString("country"),
        resultSet.getString("city"),
        resultSet.getString("password"));
      //  resultSet.getString("role"));
>>>>>>> 92e900fd6296dd545f8e7896a0a716f416adc814
  }

}