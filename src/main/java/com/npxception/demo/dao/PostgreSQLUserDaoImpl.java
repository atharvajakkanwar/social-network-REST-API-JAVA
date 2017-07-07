package com.npxception.demo.dao;

import com.npxception.demo.entity.User;
import com.npxception.demo.mapper.UserRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

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

  }

  @Override
  public void updateUser(User user) {

  }

  @Override
  public void insertUserToDb(User user) {

  }

  @Override
  public Collection<User> getUsersByFirstName(String name) {
    final String sql = "SELECT * FROM users";
    List<User> users = jdbcTemplate.query(sql, new UserRowMapper());

    return users;
  }

  @Override
  public Collection<User> getUsersByLastName(String name) {
    return null;
  }

  @Override
  public Collection<User> getUsersByFullName(String name) {
    return null;
  }

  @Override
  public User getUserByUserName(String name) {
    return null;
  }

  @Override
  public User getUserByEmail(String email) {
    return null;
  }

  @Override
  public Collection<User> getUsersByAge(int age) {
    return null;
  }

  @Override
  public Collection<User> getUsersByGender(String gender) {
    return null;
  }

  @Override
  public Collection<User> getUsersByCountry(String country) {
    return null;
  }

  @Override
  public Collection<User> getUserByCity(String city) {
    return null;
  }

  @Override
  public void setFirstName(String first) {

  }

  @Override
  public void setLastName(String last) {

  }

  @Override
  public void setEmail(String email) {

  }

  @Override
  public void setAge(int age) {

  }

  @Override
  public void setGender(String gender) {

  }

  @Override
  public void setCountry(String country) {

  }

  @Override
  public void setCity(String city) {

  }


}