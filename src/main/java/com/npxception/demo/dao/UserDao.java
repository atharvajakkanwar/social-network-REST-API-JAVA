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
  Collection<User> getUserByFirstName(String name);

  Collection<User> getUserByLastName(String name);

  // As in "First Last" nomenclature
  Collection<User> getUserByFullName(String name);

  // As in "first.last" nomenclature
  User getUserByUserName(String name);

  // Email
  User getUserByEmail(String email);

  // Age
  Collection<User> getUserByAge(int age);

  // Gender
  Collection<User> getUserByGender(String gender);

  // Country
  Collection<User> getUserByCountry(String country);

  // City
  Collection<User> getUserByCity(String city);

}
