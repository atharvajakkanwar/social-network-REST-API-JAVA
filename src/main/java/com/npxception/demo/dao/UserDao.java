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
  User getUsersByFullName(String name);

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

  void setFirstName(String first);

  void setLastName(String last);

  void setEmail(String email);

  void setAge(int age);

  void setGender(String gender);

  void setCountry(String country);

  void setCity(String city);

  void setPassword(String password);
}