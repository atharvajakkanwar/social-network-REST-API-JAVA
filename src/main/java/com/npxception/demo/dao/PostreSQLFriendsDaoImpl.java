package com.npxception.demo.dao;

import com.npxception.demo.entity.User;
import com.npxception.demo.mapper.UserRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by RachelDi on 13/06/2017.
 */
@Repository("PostgreFriends")
public class PostreSQLFriendsDaoImpl implements FriendsDao {

  final String LIST_BY_USER_ID = "SELECT u.* FROM users u," +
      "friends f  WHERE u.userid = f.userid_two_id AND f.userid_one_id = ? AND f.status = 1";
  final String REMOVE_ALL_FRIENDS = "DELETE f.* FROM users u, " +
      "friends f WHERE (u.userid = f.userid_one_id AND f.userid_one_id = ?)" +
      " OR (u.userid = f.userid_two_id AND f.userid_two_id = ?)";
  final String UN_FRIENDS = "UPDATE friends SET status = 0 WHERE userid_one_id = ? " +
      "AND userid_two_id = ?";
  // 3 means request sent
  final String SEND_REQUEST = "UPDATE friends SET status = 3 WHERE userid_one_id = ? " +
      "AND userid_two_id = ?";
  final String ACCEPT_REQUEST = "UPDATE friends SET status = 1 WHERE userid_one_id = ? " +
      "AND userid_two_id = ?";
  final String GET_STATUS = "SELETCT status FROM friends WHERE userid_one_id = ? " +
      "AND userid_two_id = ?";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Collection<User> listByUserId(int id) {
    return jdbcTemplate.query(LIST_BY_USER_ID, new UserRowMapper(), id);
  }

  @Override
  public void removeAllFriends(int id) {
    jdbcTemplate.update(REMOVE_ALL_FRIENDS, id);
  }

  @Override
  public void unFriend(int id1, int id2) {
    swap(id1, id2);
    jdbcTemplate.update(UN_FRIENDS, new Object[]{id1, id2});
  }

  @Override
  public int countFriends(int id) {
    return listByUserId(id).size();
  }

  @Override
  public void sendRequest(int id1, int id2) {
    swap(id1, id2);
    int currentStatus = jdbcTemplate.queryForObject(GET_STATUS,
        new Object[]{id1, id2}, Integer.class);
    if (currentStatus == 0) {
      jdbcTemplate.update(SEND_REQUEST, new Object[]{id1, id2});
    }
  }

  @Override
  public void acceptRequest(int id1, int id2) {
    swap(id1, id2);
    int currentStatus = jdbcTemplate.queryForObject(GET_STATUS,
        new Object[]{id1, id2}, Integer.class);
    if (currentStatus == 3) {
      jdbcTemplate.update(ACCEPT_REQUEST, new Object[]{id1, id2});
    }
  }

  public void swap(int id1, int id2) {
    id1 = id1 + id2;//id1 becomes the sum
    id2 = id1 - id2;// id2 becomes id1
    id1 = id1 - id2;//id1 becomes id2
  }
}
