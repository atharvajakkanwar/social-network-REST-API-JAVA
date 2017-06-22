package com.npxception.demo.service;

import com.npxception.demo.dao.FriendsDao;
import com.npxception.demo.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by RachelDi on 14/06/2017.
 */
@org.springframework.stereotype.Service
public class FriendsService {
  @Autowired
  @Qualifier("PostgreFriends")
  private FriendsDao dao;

  public Collection<User> getAllFriends(int id) {
    return this.dao.getAllFriends(id);
  }

  public void removeAllFriends(int id) {
    this.dao.removeAllFriends(id);
  }

  public void unFriend(int id1, int id2) {
    try {
      this.dao.unFriend(id1, id2);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int countFriends(int id) {
    return this.dao.countFriends(id);
  }

  public void sendRequest(int id1, int id2) {
    try {
      this.dao.sendRequest(id1, id2);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void becomeFriend(int id1, int id2) {
    this.dao.becomeFriend(id1, id2);
  }

  public void blockFriend(int id1, int id2) {
    this.dao.blockFriend(id1, id2);
  }
}