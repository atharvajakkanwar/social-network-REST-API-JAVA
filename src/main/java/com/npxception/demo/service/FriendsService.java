<<<<<<< HEAD
=======
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


  public void unFriend(int id, String username) {
    try {
      this.dao.unFriend(id, username);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int countFriends(int id) {
    return this.dao.countFriends(id);
  }


  public void sendRequest(int id, String username) {
    try {
      this.dao.sendRequest(id, username);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  public void becomeFriend(int id, String username) {
    this.dao.becomeFriend(id, username);
  }

  public void blockFriend(int id, String username) {
    this.dao.blockFriend(id, username);
  }

  public Collection<User> commonFriends(int id, String username) {
    return this.dao.commonFriends(id, username);
  }

  public Collection<User> getFriendsByName(String username, int id) {
    return this.dao.getFriendsByName(username, id);
  }

  public Collection<User> getInvitationList(int id) {
    return this.dao.getInvitationList(id);
  }

  public Collection<User> getBlockList(int id) {
    return this.dao.getBlockList(id);
  }
}
>>>>>>> 1466c5b71cfb0853faf883e085a642d3185663d9
