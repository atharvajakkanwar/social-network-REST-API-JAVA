package com.npxception.demo.dao;

import com.npxception.demo.helperMethods.Usernames;
import com.npxception.demo.entity.User;
import com.npxception.demo.mapper.UserRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by RachelDi on 13/06/2017.
 */
@Repository("PostgreFriends")
public class PostgreSQLFriendsDaoImpl implements FriendsDao {

  final String GET_ALL_FRIENDS = "SELECT u.* FROM users u," +
      "friends f  WHERE u.userid = f.useridtwo AND f.useridone = ? " +

      "AND (f.status = 1 OR f.status = 4)";

  final String REMOVE_ALL_FRIENDS = "DELETE FROM " +
      "friends f WHERE f.useridone = ? OR f.useridtwo = ?";

  final String UN_FRIENDS = "DELETE FROM friends WHERE useridone = ? AND useridtwo = ?";

  // to avoid more extra space in friends table, I tried to distinguish between sending
  // others invitations and being invited, block a friend and being blocked
  // 2 means request sent, 3 means got invited
  final String SEND_REQUEST = "INSERT INTO friends (useridone, useridtwo, status) " +

      "SELECT ?, ?, ? WHERE NOT EXISTS (SELECT * FROM friends " +
      "WHERE (useridone = ? AND useridtwo = ?))";
  final String BECOME_FRIEND = "UPDATE friends SET status = 1 WHERE useridone = ? " +
      "AND useridtwo = ?";

  final String GET_STATUS = "SELECT status FROM friends WHERE useridone = ? " +
      "AND useridtwo = ?";

  // 4 means block a friend, 5 means being blocked
  final String BLOCK_FRIEND = "UPDATE friends SET status = ? WHERE useridone = ? " +
      "AND useridtwo = ?";

  final String GET_FRIEND_BY_NAME = "SELECT u.* FROM users u, friends f " +

      "WHERE u.userid = f.useridtwo AND f.useridone = ? AND (u.firstname LIKE ? OR u.lastname LIKE ?)";

  final String GET_INVITATION_LIST = "SELECT u.* FROM users u, friends f " +
      "WHERE u.userid = f.useridtwo AND f.useridone = ? AND f.status = ?";

  final String GET_BLOCK_LIST = "SELECT u.* FROM users u, friends f " +
      "WHERE u.userid = f.useridtwo AND f.useridone = ? AND f.status = ?";


  @Autowired
  private JdbcTemplate jdbcTemplate;
  private Usernames unames;

  @Override
  public Collection<User> getAllFriends(int id) {
    return jdbcTemplate.query(GET_ALL_FRIENDS, new UserRowMapper(), id);
  }

  @Override
  public Collection<User> commonFriends(int id, String username) {
    String names[] = unames.splitName(username);
    int id2 = unames.getIdByname(names);
    Collection<User> res = new HashSet<>();
    Collection<User> list1 = jdbcTemplate.query(GET_ALL_FRIENDS, new UserRowMapper(), id);
    Collection<User> list2 = jdbcTemplate.query(GET_ALL_FRIENDS, new UserRowMapper(), id2);
    for (User user1 : list1) {
      for (User user2 : list2) {
        if (user1.getId() == user2.getId()) {
          res.add(user1);
        }
      }
    }
    return res;
  }

  @Override
  public Collection<User> getFriendsByName(String username, int id) {
    String[] names = unames.splitName(username);
    Collection<User> result = jdbcTemplate.query(GET_FRIEND_BY_NAME, new UserRowMapper(),
        id, names[0], names[0]);
    Collection<User> result2 = jdbcTemplate.query(GET_FRIEND_BY_NAME, new UserRowMapper(),
        id, names[1], names[1]);
    for (User user : result2){
      result.add(user);
    }
    return result;
  }




  public Collection<User> getInvitationList(int id) {
    return jdbcTemplate.query(GET_INVITATION_LIST, new UserRowMapper(), id);
  }

  @Override
  public Collection<User> getBlockList(int id) {
    return jdbcTemplate.query(GET_BLOCK_LIST, new UserRowMapper(), id);
  }

  @Override
  public void removeAllFriends(int id) {
    jdbcTemplate.update(REMOVE_ALL_FRIENDS, new Object[]{id, id});
  }


  public void unFriend(int id, String username) {
    String names[] = unames.splitName(username);
    int id2 = unames.getIdByname(names);
    jdbcTemplate.update(UN_FRIENDS, new Object[]{id, id2});
  }

  @Override
  public int countFriends(int id) {
    return getAllFriends(id).size();
  }

  @Override

  public void sendRequest(int id, String username) {
      String names[] = unames.splitName(username);
      int id2 = unames.getIdByname(names);
    if (id < id2) {
      jdbcTemplate.update(SEND_REQUEST, new Object[]{id, id2, 2, id, id2,});
    } else {
      jdbcTemplate.update(SEND_REQUEST, new Object[]{id2, id, 3, id2, id});
    }
  }

  @Override

  public void becomeFriend(int id, String username) {
    String names[] = unames.splitName(username);
    int id2 = unames.getIdByname(names);
    int currentStatus;
    if (id < id2) {
      currentStatus = jdbcTemplate.queryForObject(GET_STATUS,
          new Object[]{id, id2}, Integer.class);
      // if got invited or block the other
      if ((currentStatus == 3 || currentStatus == 4)) {
        jdbcTemplate.update(BECOME_FRIEND, new Object[]{id, id2});
      }
    } else {
      currentStatus = jdbcTemplate.queryForObject(GET_STATUS,
          new Object[]{id2, id}, Integer.class);
      System.out.print(currentStatus);
      // this statement means, if id1 is blocked by id2, then id2 has the
      // right to unblock
      if ((currentStatus == 2 || currentStatus == 5)) {
        jdbcTemplate.update(BECOME_FRIEND, new Object[]{id2, id});
      }
    }
  }


  @Override
  public void blockFriend(int id, String username) {
    String names[] = unames.splitName(username);
    int id2 = unames.getIdByname(names);
    int currentStatus;
    if (id < id2) {
      currentStatus = jdbcTemplate.queryForObject(GET_STATUS,
          new Object[]{id, id2}, Integer.class);
      if (currentStatus == 1) {
        jdbcTemplate.update(BLOCK_FRIEND, new Object[]{4, id, id2});
      }
    } else {
      currentStatus = jdbcTemplate.queryForObject(GET_STATUS,
          new Object[]{id2, id}, Integer.class);
      if (currentStatus == 1) {
        jdbcTemplate.update(BLOCK_FRIEND, new Object[]{5, id2, id});
      }
    }
  }

  /**
   * Since the friend table require user1's id is smaller than user2's id,
   * we need to swap the ids if user1's id is bigger than user2's id
   * Here is a simple swap method works on two integers
   *
   * @param a the first integer
   * @param b the second integer
   * @return an array of new a and b
   **/

//  public int[] swap(int a, int b) {
//    int[] result = new int[2];
//    if (a >= b) {
//      a = a + b;//id1 becomes the sum
//      b = a - b;//id2 becomes id1
//      a = a - b;//id1 becomes id2
//    }
//    result[0] = a;
//    result[1] = b;
//    return result;
//  }
}