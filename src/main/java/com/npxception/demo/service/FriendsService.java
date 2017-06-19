package com.npxception.demo.service;

import com.npxception.demo.dao.FriendsDao;
import com.npxception.demo.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.Collection;

/**
 * Created by RachelDi on 14/06/2017.
 */
@org.springframework.stereotype.Service
public class FriendsService {
  @Autowired
  @Qualifier("PostgreFriends")
  private FriendsDao dao;

  public Collection<User> listByUserId(int id) {
    return this.dao.listByUserId(id);
  }

  public void removeAllFriends(User user) {
    this.dao.removeAllFriends(user);
  }

  public void unFriend(User user1, User user2) {
    this.dao.unFriend(user1, user2);
  }

  public int countFriends(User user) {
    return this.dao.countFriends(user);
  }

  public void sendRequest(User user1, User user2) {
    this.dao.sendRequest(user1, user2);
  }

  public void acceptRequest(User user1, User user2) {
    this.dao.acceptRequest(user1, user2);
  }
}
