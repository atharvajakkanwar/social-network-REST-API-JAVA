package com.npxception.demo.dao;

import com.npxception.demo.entity.User;

import java.util.Collection;

public interface UserDao {
  Collection<User> getAllUser();

  User getUserById(int id);

  void removeUserById(int id);

  void updateUser(User user);

  void insertUserToDb(User user);

  Collection<User> getUserByName(String name);

  Collection<User> getUserByAge(int age);

  Collection<User> getUserByGender(String gender);

  // need to add in getUser by country/city/firstname/lastname

}
