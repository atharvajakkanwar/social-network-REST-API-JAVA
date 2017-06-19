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

  public void removeAllFriends(int id) {
    this.dao.removeAllFriends(id);
  }

  public void unFriend(int id1, int id2) {
    this.dao.unFriend(id1, id2);
  }

  public int countFriends(int id) {
    return this.dao.countFriends(id);
  }

  public void sendRequest(int id1, int id2) {
    this.dao.sendRequest(id1, id2);
  }

  public void acceptRequest(int id1, int id2) {
    this.dao.acceptRequest(id1, id2);
  }
}
