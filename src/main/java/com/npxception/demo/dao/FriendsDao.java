package com.npxception.demo.dao;

import com.npxception.demo.entity.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by RachelDi on 13/06/2017.
 */
public interface FriendsDao {

  Collection<User> listByUserId(int id);

  void removeAllFriends(User user);

  void unFriend(User user1, User user2);

  int countFriends(User user);

  void sendRequest(User user1, User user2);

  void acceptRequest(User user1, User user2);
}