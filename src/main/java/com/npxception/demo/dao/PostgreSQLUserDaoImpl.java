package com.npxception.demo.dao;

import com.npxception.demo.entity.User;
import com.npxception.demo.helperMethods.Usernames;
import com.npxception.demo.mapper.UserRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Robert on 6/5/2017.
 */
@Repository("PostgresUserRepo")
public class PostgreSQLUserDaoImpl implements UserDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Collection<User> getAllUser() {
    final String sql = "SELECT * FROM users";
    List<User> users = jdbcTemplate.query(sql, new UserRowMapper());
    return users;
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @Override
  public User getUserById(int userid) {
    final String sql = "SELECT * FROM users WHERE userid = ?";
    User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), userid);
    return user;
  }

  @Override
  public void removeUserById(int id) {
    final String sql = "DELETE FROM users WHERE userid = ?";
    jdbcTemplate.update(sql, id);
  }

  @Override
  public void updateUser(User user) {
    final String sql = "UPDATE users SET email = ?, age = ?, gender = ?, country = ?, " +
        "city = ?, password = ? WHERE firstName = ? AND lastName = ?";
    jdbcTemplate.update(sql, new Object[]{user.getEmail(), user.getAge()
        , user.getGender(), user.getCountry(), user.getCity(), user.getPassword()
        , user.getFirstName(), user.getLastName()});
  }

  @Override
  public void insertUserToDb(User user) {
    final String sql = "INSERT INTO users (firstName, lastName, email, age, gender, country, " +
        "city, password) SELECT ?, ?, ?, ?, ?, ?, ?, ? " +
        "WHERE NOT EXISTS (SELECT * FROM users WHERE (lastName = ? AND firstName = ?))";
    jdbcTemplate.update(sql, new Object[]{user.getFirstName(), user.getLastName(), user.getEmail()
        , user.getAge(), user.getGender(), user.getCountry(), user.getCity(), user.getPassword()
        , user.getFirstName(), user.getLastName()});
  }

  @Override
  public Collection<User> getUsersByFirstName(String name) {
    final String sql = "SELECT * FROM users WHERE firstName = ?";
    List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), name);
    return users;
  }

  public Collection<User> getUsersByLastName(String name) {
    final String sql = "SELECT * FROM users WHERE lastName = ?";
    List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), name);
    return users;
  }

  @Override
  public Collection<User> getUsersByFullName(String name) {
    String[] names = new Usernames().splitName(name);
    final String sql = "SELECT * FROM users WHERE firstName = ? OR lastName = ?";
    List<User> users = jdbcTemplate.query(sql, new UserRowMapper()
        , new Object[]{names[0], names[1]});
    return users;
  }

  @Override
  public User getUserByUserName(String name) {
    String[] names = new Usernames().splitName(name);
    final String sql = "SELECT * FROM users WHERE firstName = ? AND lastName = ?";
    User user = jdbcTemplate.queryForObject(sql, new UserRowMapper()
        , new Object[]{names[0], names[1]});
    return user;
  }

  @Override
  public User getUserByEmail(String email) {
    final String sql = "SELECT * FROM users WHERE email = ?";
    User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), email);
    return user;
  }

  @Override
  public Collection<User> getUsersByAge(int age) {
    final String sql = "SELECT * FROM users WHERE age = ?";
    List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), age);
    return users;
  }

  @Override
  public Collection<User> getUsersByGender(String gender) {
    final String sql = "SELECT * FROM users WHERE gender = ?";
    List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), gender);
    return users;
  }

  @Override
  public Collection<User> getUsersByCountry(String country) {
    final String sql = "SELECT * FROM users WHERE country = ?";
    List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), country);
    return users;
  }

  @Override
  public Collection<User> getUserByCity(String city) {
    final String sql = "SELECT * FROM users WHERE city = ?";
    List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), city);
    return users;
  }

  @Override
  public void setFirstName(String first) {
    final String sql = "UPDATE users SET firstname = ?";
    jdbcTemplate.update(sql, first);
  }

  @Override
  public void setLastName(String last) {
    final String sql = "UPDATE users SET lastname = ?";
    jdbcTemplate.update(sql, last);
  }

  @Override
  public void setEmail(String email) {
    final String sql = "UPDATE users SET email = ?";
    jdbcTemplate.update(sql, email);
  }

  @Override
  public void setAge(int age) {
    final String sql = "UPDATE users SET age = ?";
    jdbcTemplate.update(sql, age);
  }

  @Override
  public void setGender(String gender) {
    final String sql = "UPDATE users SET gender = ?";
    jdbcTemplate.update(sql, gender);
  }

  @Override
  public void setCountry(String country) {
    final String sql = "UPDATE users SET country = ?";
    jdbcTemplate.update(sql, country);
  }

  @Override
  public void setCity(String city) {
    final String sql = "UPDATE users SET city = ?";
    jdbcTemplate.update(sql, city);
  }
}