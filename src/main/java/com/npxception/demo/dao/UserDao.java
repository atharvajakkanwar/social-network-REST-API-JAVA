package com.npxception.demo.dao;

import com.npxception.demo.entity.User;

import java.util.Collection;

public interface UserDao {
  // need to find a way to let login and register happen first
  Collection<User> getAllUser();

  // Developer methods
  User getUserById(int id);

  void removeUserById(int id);

  void updateUser(User user);

  void insertUserToDb(User user);

  // Client API methods
  Collection<User> getUsersByFirstName(String name);

  Collection<User> getUsersByLastName(String name);

  // As in "First Last" nomenclature
  Collection<User> getUsersByFullName(String name);

  // As in "first.last" nomenclature
  User getUserByUserName(String name);

  // Email
  User getUserByEmail(String email);

  // Age
  Collection<User> getUsersByAge(int age);

  // Gender
  Collection<User> getUsersByGender(String gender);

  // Country
  Collection<User> getUsersByCountry(String country);

  // City
  Collection<User> getUserByCity(String city);

  void setFirstName(User user, String first);

  void setLastName(User user, String last);

  void setEmail(User user, String email);

  void setAge(User user, int age);

  void setGender(User user, String gender);

  void setCountry(User user, String country);

  void setCity(User user, String city);

}