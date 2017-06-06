package com.npxception.demo.dao;

import com.npxception.demo.entity.User;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Qualifier("mongoData")
public class MongoStudentDaoImpl implements UserDao {


  @Override
  public Collection<User> getAllUser() {
    return null;
  }

  @Override
  public User getUserById(int id) {
    return null;
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
  public Collection<User> getUserByName(String name) {
    return null;
  }

  @Override
  public Collection<User> getUserByAge(int age) {
    return null;
  }

  @Override
  public Collection<User> getUserByGender(String gender) {
    return null;
  }
}
