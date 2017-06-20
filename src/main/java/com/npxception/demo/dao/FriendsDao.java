package com.npxception.demo.dao;

import com.npxception.demo.entity.User;

import java.util.Collection;

/**
 * Created by RachelDi on 13/06/2017.
 */
public interface FriendsDao {

  Collection<User> listByUserId(int id);

  void removeAllFriends(int id);

  void unFriend(int id1, int id2);

  int countFriends(int id);

  void sendRequest(int id1, int id2);

  void acceptRequest(int id1, int id2);
}