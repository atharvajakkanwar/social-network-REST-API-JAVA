package com.npxception.demo.dao;

import com.npxception.demo.entity.User;
import com.npxception.demo.mapper.UserRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by RachelDi on 13/06/2017.
 */
@Repository("PostgreFriends")
public class PostgreSQLFriendsDaoImpl implements FriendsDao {

  final String LIST_BY_USER_ID = "SELECT u.* FROM users u," +
      "friends f  WHERE u.userid = f.userid-two AND f.userid-one = ? AND f.status = 1";
  final String REMOVE_ALL_FRIENDS = "DELETE f.* FROM users u, " +
      "friends f WHERE (u.userid = f.userid-one AND f.userid-one = ?)" +
      " OR (u.userid = f.userid-two AND f.userid-two = ?)";
  final String UN_FRIENDS = "DELETE FROM friends WHERE userid-one = ? AND userid-two = ?";
  // 2 means request sent
  final String SEND_REQUEST = "INSERT INTO friends (userid-one, userid-two, status) " +
      "SELECT ?, ?, 2 FROM dual WHERE not exists (select * from friends " +
      "WHERE userid-one = ? AND userid-two = ?";
  final String BECOME_FRIEND = "UPDATE friends SET status = 1 WHERE userid-one = ? " +
      "AND userid-two = ?";
  final String GET_STATUS = "SELECT status FROM friends WHERE userid-one = ? " +
      "AND userid-two = ?";
  // 3 means block
  final String BLOCK_FRIEND = "UPDATE friends SET status = 3 WHERE userid-one = ? " +
      "AND userid-two = ?";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Collection<User> getAllFriends(int id) {
    return jdbcTemplate.query(LIST_BY_USER_ID, new UserRowMapper(), id);
  }

  @Override
  public void removeAllFriends(int id) {
    jdbcTemplate.update(REMOVE_ALL_FRIENDS, id);
  }

  @Override
  public void unFriend(int id1, int id2){
    swap(id1, id2);
    jdbcTemplate.update(UN_FRIENDS, setStatement(id1, id2, UN_FRIENDS));
    // jdbcTemplate.update(UN_FRIENDS, new Object[]{id1, id2});
  }

  @Override
  public int countFriends(int id) {
    return getAllFriends(id).size();
  }

  @Override
  public void sendRequest(int id1, int id2){
    swap(id1, id2);
    jdbcTemplate.update(SEND_REQUEST, setStatement(id1, id2, SEND_REQUEST));
  }

  @Override
  public void becomeFriend(int id1, int id2) {
    swap(id1, id2);
    int currentStatus = jdbcTemplate.queryForObject(GET_STATUS,
        new Object[]{id1, id2}, Integer.class);
    if (currentStatus == 2) {
      jdbcTemplate.update(BECOME_FRIEND, setStatement(id1, id2, BECOME_FRIEND));
    }
  }

  @Override
  public void blockFriend(int id1, int id2) {
    swap(id1, id2);
    int currentStatus = jdbcTemplate.queryForObject(GET_STATUS,
        new Object[]{id1, id2}, Integer.class);
    if (currentStatus == 1) {
      jdbcTemplate.update(BLOCK_FRIEND, setStatement(id1, id2, BLOCK_FRIEND));
    }
  }

  /**
   * Since the friend table require user1's id is smaller than user2's id,
   * we need to swap the ids if user1's id is bigger than user2's id
   * Here is a simple swap method works on two integers
   * @param a the first integer
   * @param b the second integer
   */
  public void swap(int a, int b) {
    a = a + b;//id1 becomes the sum
    b = a - b;// id2 becomes id1
    a = a - b;//id1 becomes id2
  }

  /**
   * set up statements for sql commands
   * @param id1 the id of user1
   * @param id2 the id of user2
   * @param sql the sql command that needs to be set up
   * @return the prepared statement that can be used directly where we need 2 ids
   */
  public PreparedStatement setStatement(int id1, int id2, String sql){
    Connection con=null;
    PreparedStatement statement=null;
    try {
      statement = con.prepareStatement(sql);
      statement.setInt(1, id1);
      statement.setInt(2, id2);
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return statement;
  }
}
