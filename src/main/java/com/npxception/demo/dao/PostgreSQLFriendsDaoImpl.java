package com.npxception.demo.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Driver;
import java.sql.DriverManager;

import com.npxception.demo.entity.User;
import com.npxception.demo.mapper.UserRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;

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
      "SELECT ?, ?, 2 FROM dual WHERE not exists (select * from friends " +
      "WHERE useridone = ? AND useridtwo = ?";

  final String BECOME_FRIEND = "UPDATE friends SET status = 1 WHERE useridone = ? " +
      "AND useridtwo = ?";

  final String GET_STATUS = "SELECT status FROM friends WHERE useridone = ? " +
      "AND useridtwo = ?";

  // 4 means block a friend, 5 means being blocked
  final String BLOCK_FRIEND = "UPDATE friends SET status = 4 WHERE useridone = ? " +
      "AND useridtwo = ?";

  final String GET_FRIEND_BY_NAME = "SELECT u.* FROM users u, friends f " +
      "WHERE u.userid = f.useridtwo AND f.useridone = ? AND (u.firstname = ? OR u.lastname = ?)";

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
    for (User user : list1) {
      if (list2.contains(user)) {
        res.add(user);
      }
    }
    return res;
  }

  @Override
  public Collection<User> getFriendsByName(String name, int id) {
    return jdbcTemplate.query(GET_FRIEND_BY_NAME, new UserRowMapper(), id, name, name);
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
    int[] ids = new int[2];
    ids[0] = id;
    ids[1] = id;
    setStatement(ids, REMOVE_ALL_FRIENDS);
  }

  @Override
  public void unFriend(int id1, int id2) {
    int[] ids = swap(id1, id2);
    setStatement(ids, UN_FRIENDS);
  }

  @Override
  public int countFriends(int id) {
    return getAllFriends(id).size();
  }

  @Override
  public void sendRequest(int id1, int id2) {
    int[] res = swap(id1, id2);
    int[] ids = new int[4];
    ids[0] = res[0];
    ids[1] = res[1];
    ids[2] = res[0];
    ids[3] = res[1];
    setStatement(ids, SEND_REQUEST);
    //   jdbcTemplate.update(SEND_REQUEST, setStatement(id1, id2, SEND_REQUEST));
  }

  @Override
  public void becomeFriend(int id1, int id2) {
    swap(id1, id2);
    int currentStatus = jdbcTemplate.queryForObject(GET_STATUS,
        new Object[]{id1, id2}, Integer.class);
    if (currentStatus == 2) {
      int[] ids = swap(id1, id2);
      setStatement(ids, BECOME_FRIEND);
    }
  }

  @Override
  public void blockFriend(int id1, int id2) {
    swap(id1, id2);
    int currentStatus = jdbcTemplate.queryForObject(GET_STATUS,
        new Object[]{id1, id2}, Integer.class);
    if (currentStatus == 1) {
      int[] ids = swap(id1, id2);
      setStatement(ids, BLOCK_FRIEND);
    }
  }

  /**
   * Since the friend table require user1's id is smaller than user2's id,
   * we need to swap the ids if user1's id is bigger than user2's id
   * Here is a simple swap method works on two integers
   *
   * @param a the first integer
   * @param b the second integer
   * @return an array that contains the two integers
   */
  public int[] swap(int a, int b) {
    int[] res = new int[2];
    if (a <= b) {
      a = a + b;//id1 becomes the sum
      b = a - b;// id2 becomes id1
      a = a - b;//id1 becomes id2
    }
    res[0] = a;
    res[1] = b;
    return res;
  }

  /**
   * set up statements for sql commands
   *
   * @param ids the ids as input
   * @param sql the sql command that needs to be set up
   * @return the prepared statement that can be used directly where we need 2 ids
   */
  public void setStatement(int[] ids, String sql) {
    Connection con = null;
    PreparedStatement statement = null;
    try {
      con = getDBConnection();
      statement = con.prepareStatement(sql);
      for (int i = 1; i <= ids.length; i++) {
        statement.setInt(i, ids[i - 1]);
      }
      statement.executeUpdate();
      System.out.println("data is updated!");
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (statement != null) {
        try {
          statement.close();
          if (con != null) {
            con.close();
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }


  private Connection getDBConnection() {
    Connection dbConnection = null;
    String driverClass = null;
    String jdbcUrl = null;
    String user = null;
    String password = null;
    InputStream in = getClass().getClassLoader().getResourceAsStream("application.properties");
    Properties properties = new Properties();
    try {
      properties.load(in);
      driverClass = properties.getProperty("spring.datasource.driver-class-name");
      jdbcUrl = properties.getProperty("spring.datasource.url");
      user = properties.getProperty("spring.datasource.username");
      password = properties.getProperty("spring.datasource.password");
      //Driver driver = (Driver) Class.forName(driverClass).newInstance();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      Class.forName(driverClass);
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
    try {
      dbConnection = DriverManager.getConnection(jdbcUrl, user, password);
      return dbConnection;
    } catch (SQLException e) {

      System.out.println(e.getMessage());
    }
    return dbConnection;
  }
}