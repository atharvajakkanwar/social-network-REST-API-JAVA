package com.npxception.demo.service;

import com.npxception.demo.dao.UserDao;
import com.npxception.demo.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

  @Autowired
  @Qualifier("Postgre")
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

//  public Collection<User> getUserByCourse(String course) {
//    return this.userDao.getUserByCourse(course);
//  }

  public Collection<User> getUserByName(String name) {
    return this.userDao.getUserByName(name);
  }

  public Collection<User> getUserByGender(String gender) {
    return this.userDao.getUserByGender(gender);
  }

  public Collection<User> getUserByAge(int age) {
    return this.userDao.getUserByAge(age);
  }

//  public void removeUserByCourse(String course) {
//    this.userDao.removeUserByCourse(course);
//  }
//
//  public Collection<User> getUserInCourseByName(String course, String name) {
//    return this.userDao.getUserInCourseByName(course, name);
//  }

}
