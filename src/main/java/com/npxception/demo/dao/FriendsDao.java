package com.npxception.demo.dao;

import com.npxception.demo.entity.User;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by RachelDi on 13/06/2017.
 */
public interface FriendsDao {

  /**
   * Get a list of all the friends of a user
   * @param id the user id
   * @return all the friends of the user
   */
  Collection<User> getAllFriends(int id);

  /**
   * Remove all friends of a user
   * @param id the user id
   */
  void removeAllFriends(int id);

  /**
   * Remove a friend of a user from the friend list
   * @param id1 the user
   * @param id2 the friend
   * @throws SQLException
   */
  void unFriend(int id1, int id2) throws SQLException;

  /**
   * Count all the friends that a user has
   * @param id the user id
   * @return the number of the friends of a user
   */
  int countFriends(int id);

  /**
   * Sending a friend request to a user2 from user1, put them on the friends table
   * @param id1 the id of user1
   * @param id2 the id of user2
   * @throws SQLException
   */
  void sendRequest(int id1, int id2) throws SQLException;

  /**
   * Make user2 become the friend of user1
   * @param id1 id of user1
   * @param id2 id of user2
   */
  void becomeFriend(int id1, int id2);

  /**
   * Block user2, who is friend of user1, by user1
   * @param id1 the id of user1
   * @param id2 the id of user2
   */
  void blockFriend(int id1, int id2);

}