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

  public Collection<User> getAllUser() {
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

  Collection<User> getUserByFirstName(String name) {
    return this.userDao.getUserByFirstName(name);
  }

  Collection<User> getUserByLastName(String name) {
    return this.userDao.getUserByLastName(name);
  }

  // As in "First Last" nomenclature
  Collection<User> getUserByFullName(String name) {
    return this.userDao.getUserByFullName(name);
  }

  // As in "first.last" nomenclature
  User getUserByUserName(String name) {
    return this.userDao.getUserByUserName(name);
  }

  // Email
  User getUserByEmail(String email) {
    return this.userDao.getUserByEmail(email);
  }

  // Age
  Collection<User> getUserByAge(int age) {
    return this.userDao.getUserByAge(age);
  }

  // Gender
  Collection<User> getUserByGender(String gender) {
    return this.userDao.getUserByGender(gender);
  }

  // Country
  Collection<User> getUserByCountry(String country) {
    return this.userDao.getUserByCountry(country);
  }

  // City
  Collection<User> getUserByCity(String city) {
    return this.userDao.getUserByCity(city);
  }

  public void insertUserToDb(User user) {
    this.userDao.insertUserToDb(user);
  }

  public void login(String email, String password) {
    new Login().login(email, password);
  }
}
