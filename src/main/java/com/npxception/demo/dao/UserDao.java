package com.npxception.demo.dao;

import com.npxception.demo.entity.User;

import java.sql.SQLException;
import java.util.Collection;

public interface UserDao {
  Collection<User> getAllUser();

  User getUserById(int id);

  void removeUserById(int id);

  void updateUser(User user);

  void insertUserToDb(User user);

//  Collection<User> getUserByCourse(String course);

  Collection<User> getUserByName(String name);

  Collection<User> getUserByAge(int age);

  Collection<User> getUserByGender(String gender);

//  void removeUserByCourse(String course);
//
//  // get students in course by name, return a list of students
//  Collection<User> getUserInCourseByName(String course, String firstName);

}
