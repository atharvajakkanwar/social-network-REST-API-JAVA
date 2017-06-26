package com.npxception.demo.dao;

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
      "SELECT ?, ?, ? FROM friends WHERE not exists (select * from friends " +
      "WHERE useridone = ? AND useridtwo = ?";

  final String BECOME_FRIEND = "UPDATE friends SET status = 1 WHERE useridone = ? " +
      "AND useridtwo = ?";

  final String GET_STATUS = "SELECT status FROM friends WHERE useridone = ? " +
      "AND useridtwo = ?";

  // 4 means block a friend, 5 means being blocked
  final String BLOCK_FRIEND = "UPDATE friends SET status = ? WHERE useridone = ? " +
      "AND useridtwo = ?";

  final String GET_FRIEND_BY_NAME = "SELECT u.* FROM users u, friends f " +
      "WHERE u.userid = f.useridtwo AND u.lastname =" + "'" + "?" + "'"
      + "OR u.lastname =" + "'" + "?" + "'";

  final String GET_INVITATION_LIST = "SELECT u.* FROM users u, friends f " +
      "WHERE u.userid = f.useridtwo AND f.useridone = ? AND f.status = 3";

  final String GET_BLOCK_LIST = "SELECT u.* FROM users u, friends f " +
      "WHERE u.userid = f.useridtwo AND f.useridone = ? AND f.status = 4";

  @Autowired
  private JdbcTemplate jdbcTemplate;


  @Override
  public Collection<User> getAllFriends(int id) {
    return jdbcTemplate.query(GET_ALL_FRIENDS, new UserRowMapper(), id);
  }

  @Override
  public Collection<User> commonFriends(int id1, int id2) {
    Collection<User> res = new HashSet<>();
    Collection<User> list1 = jdbcTemplate.query(GET_ALL_FRIENDS, new UserRowMapper(), id1);
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
  public Collection<User> getFriendsByName(String name, int id) {
    return jdbcTemplate.query(GET_FRIEND_BY_NAME, new UserRowMapper(), name, id);
  }

  @Override
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

  @Override
  public void unFriend(int id1, int id2) {
    swap(id1, id2);
    jdbcTemplate.update(UN_FRIENDS, new Object[]{id1, id2});
    System.out.print(countFriends(id1));
  }

  @Override
  public int countFriends(int id) {
    return getAllFriends(id).size();
  }

  @Override
  public void sendRequest(int id1, int id2) {
    if (id1 < id2) {
      jdbcTemplate.update(SEND_REQUEST, new Object[]{id1, id2, 2, id1, id2});
    } else {
      swap(id1, id2);
      jdbcTemplate.update(SEND_REQUEST, new Object[]{id1, id2, 3, id1, id2});

    }
  }

  @Override
  public void becomeFriend(int id1, int id2) {
    int currentStatus = jdbcTemplate.queryForObject(GET_STATUS,
        new Object[]{id1, id2}, Integer.class);
    //if got invited or block the other
    if ((currentStatus == 3 || currentStatus == 4) && id1 < id2) {
      jdbcTemplate.update(BECOME_FRIEND, new Object[]{id1, id2});
      // this statement means, if id1 is blocked by id2, then id2 has the
      // right to unblock
    } else if ((currentStatus == 2 || currentStatus == 5) && id1 > id2) {
      // need to swap because the table requires id1<id2
      swap(id1, id2);
      jdbcTemplate.update(BECOME_FRIEND, new Object[]{id1, id2});
    }
  }

  @Override
  public void blockFriend(int id1, int id2) {
    swap(id1, id2);
    int currentStatus = jdbcTemplate.queryForObject(GET_STATUS,
        new Object[]{id1, id2}, Integer.class);
    if (currentStatus == 1) {
      if (id1 < id2) {
        jdbcTemplate.update(BLOCK_FRIEND, new Object[]{4, id1, id2});
      } else {
        swap(id1, id2);
        jdbcTemplate.update(BLOCK_FRIEND, new Object[]{5, id1, id2});
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
   */

  public void swap(int a, int b) {
    if (a >= b) {
      a = a + b;//id1 becomes the sum
      b = a - b;//id2 becomes id1
      a = a - b;//id1 becomes id2
    }
  }
}