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

  final String GET_USER_BY_NAME = "SELECT u FROM users u " +
      "WHERE u.firstName like :firstname AND u.lastName like :lastname";

  // static int id;
//
//  public PostgreSQLUserDaoImpl(int id){
//    userid = id;
//  }
 // int userid;

//  @Autowired
//  private BCryptPasswordEncoder

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
  public void updateUser(User student) {

  }

  @Override
  public void insertUserToDb(User user) {
    //INSERT INTO table_name (column1, column2, column3,...)
    //VALUES (value1, value2, value3,...)
    final String sql = "INSERT INTO users (userid, firstname, lastname, email, age, gender, country, city, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    jdbcTemplate.update(sql, user.getId(),
        user.getFirstName(),
        user.getLastName(),
        user.getEmail(),
        user.getAge(),
        user.getGender(),
        user.getCountry(),
        user.getCity(),
        "PASS");

  }

  @Override
  public Collection<User> getUserByName(String name) {
    StringTokenizer stok = new StringTokenizer(name);
    String firstname = stok.nextToken();
    String lastname = stok.nextToken();
    return jdbcTemplate.query(GET_USER_BY_NAME, new UserRowMapper(), firstname, lastname);
  }

  @Override
  public Collection<User> getUserByAge(int age) {
    return null;
  }

  @Override
  public Collection<User> getUserByGender(String gender) {
    return null;
  }

//  @Override
//  // login will set the user id, so later all the operations will be bound to this id
//  public void login(String email, String password) {
//    //int userid;
//    final String sql = "SELECT FROM users WHERE email = ? AND password = ?";
//    User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), email, password);
//    if (user != null){
//      id = user.getId();
//    }
//  }

}