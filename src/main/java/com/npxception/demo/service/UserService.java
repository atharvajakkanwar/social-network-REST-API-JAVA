package com.npxception.demo.service;

import com.npxception.demo.dao.UserDao;
import com.npxception.demo.entity.User;
import com.npxception.demo.login.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

  @Autowired
  @Qualifier("PostgresUserRepo")
  private UserDao userDao;

  public Collection<User> getAllUsers() {
    return this.userDao.getAllUser();
  }

  public User getUserById(int userid) {
    return this.userDao.getUserById(userid);
  }

  public void removeUserById(int id) {
    this.userDao.removeUserById(id);
  }

  public void updateUser(User user) {
    this.userDao.updateUser(user);
  }

  public void insertUser(User user) {
    this.userDao.insertUserToDb(user);
  }

  public Collection<User> getUsersByFirstName(String name) {
    return this.userDao.getUsersByFirstName(name);
  }

  public Collection<User> getUsersByLastName(String name) {
    return this.userDao.getUsersByLastName(name);
  }

  // As in "First Last" nomenclature
  public Collection<User> getUsersByFullName(String name) {
    return this.userDao.getUsersByFullName(name);
  }

  // As in "first.last" nomenclature
  public User getUserByUserName(String name) {
    return this.userDao.getUserByUserName(name);
  }

  // Email
  public User getUserByEmail(String email) {
    return this.userDao.getUserByEmail(email);
  }

  // Age
  public Collection<User> getUsersByAge(int age) {
    return this.userDao.getUsersByAge(age);
  }

  // Gender
  public Collection<User> getUsersByGender(String gender) {
    return this.userDao.getUsersByGender(gender);
  }

  // Country
  public Collection<User> getUsersByCountry(String country) {
    return this.userDao.getUsersByCountry(country);
  }

  // City
  public Collection<User> getUsersByCity(String city) {
    return this.userDao.getUserByCity(city);
  }

  public void register(User user) {
    this.userDao.insertUserToDb(user);
  }

  public void login(String email, String password) {
    new Login().login(email, password);
  }

  public void setFirst(String first) {
    this.userDao.setFirstName(first);
  }
}

