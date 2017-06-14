package com.npxception.demo.dao;

import com.npxception.demo.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

/**
 * Created by Robert on 6/5/2017.
 */
@Repository("Postgre")
public class PostgreSQLUserDaoImpl implements UserDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public PostgreSQLUserDaoImpl() {
      }

  public Connection connect() throws SQLException {
    return DriverManager.getConnection("jdbc:postgresql://localhost/", "postgres", "postgres");
  }

  private static class UserRowMapper implements RowMapper<User> {
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

  @Override
  public Collection<User> getAllUser() {
    //SELECT column_name(s) FROM table_name
    final String sql = "SELECT * FROM users";
    List<User> users = jdbcTemplate.query(sql, new UserRowMapper());

    return users;
  }

  @Override
  public User getUserById(int userid) {
    final String sql = "SELECT * FROM users WHERE userid = ?";
    User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), userid);
    return user;
  }

  @Override
  public void removeUserById(int id) {

  }

  @Override
  public void updateUser(User student) {

  }

  @Override
  public void insertUserToDb(User user) {
    //INSERT INTO table_name (column1, column2, column3,...)
    //VALUES (value1, value2, value3,...)
    final String sql = "INSERT INTO users (userid, firstname, lastname, email, age, gender, country, city, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    jdbcTemplate.update(sql, new Object[]{
        user.getId(),
        user.getFirstName(),
        user.getLastName(),
        user.getEmail(),
        user.getAge(),
        user.getGender(),
        user.getCountry(),
        user.getCity(),
        "PASS"
    });

  }

  @Override
  public Collection<User> getUserByName(String name) {
    return null;
  }

  @Override
  public Collection<User> getUserByAge(int age) {
    return null;
  }

  @Override
  public Collection<User> getUserByGender(String gender) {
    return null;
  }

}
