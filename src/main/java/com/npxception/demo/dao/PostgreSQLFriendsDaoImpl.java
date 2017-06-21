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
import java.util.Collection;
import java.util.Properties;

/**
 * Created by RachelDi on 13/06/2017.
 */
@Repository("PostgreFriends")
public class PostgreSQLFriendsDaoImpl implements FriendsDao {

  final String LIST_BY_USER_ID = "SELECT u.* FROM users u," +
      "friends f  WHERE u.userid = f.useridtwo AND f.useridone = ? " +
      "AND (f.status = 1 OR f.status = 3)";
  final String REMOVE_ALL_FRIENDS = "DELETE FROM " +
    "friends f WHERE f.useridone = ? OR f.useridtwo = ?";
  final String UN_FRIENDS = "DELETE FROM friends WHERE useridone = ? AND useridtwo = ?";
  // 2 means request sent
  final String SEND_REQUEST = "INSERT INTO friends (useridone, useridtwo, status) " +
      "SELECT ?, ?, 2 FROM dual WHERE not exists (select * from friends " +
      "WHERE useridone = ? AND useridtwo = ?";
  final String BECOME_FRIEND = "UPDATE friends SET status = 1 WHERE useridone = ? " +
      "AND useridtwo = ?";
  final String GET_STATUS = "SELECT status FROM friends WHERE useridone = ? " +
      "AND useridtwo = ?";
  // 3 means block
  final String BLOCK_FRIEND = "UPDATE friends SET status = 3 WHERE useridone = ? " +
      "AND useridtwo = ?";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Collection<User> getAllFriends(int id) {
    return jdbcTemplate.query(LIST_BY_USER_ID, new UserRowMapper(), id);
  }

  @Override
  public void removeAllFriends(int id) {
    int[] ids = new int[2];
    ids[0] = id;
    ids[1] = id;
    setStatement(ids, REMOVE_ALL_FRIENDS);
  }


  @Override
  public void unFriend(int id1, int id2){
    int[] ids = swap(id1, id2) ;
    setStatement(ids, UN_FRIENDS);
  }

  @Override
  public int countFriends(int id) {
    return getAllFriends(id).size();
  }

  @Override
  public void sendRequest(int id1, int id2){
    int[] res = swap(id1, id2) ;
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
      int[] ids = swap(id1, id2) ;
      setStatement(ids, BECOME_FRIEND);
    }
  }

  @Override
  public void blockFriend(int id1, int id2) {
    swap(id1, id2);
    int currentStatus = jdbcTemplate.queryForObject(GET_STATUS,
        new Object[]{id1, id2}, Integer.class);
    if (currentStatus == 1) {
      int[] ids = swap(id1, id2) ;
      setStatement(ids, BLOCK_FRIEND);    }
  }

  /**
   * Since the friend table require user1's id is smaller than user2's id,
   * we need to swap the ids if user1's id is bigger than user2's id
   * Here is a simple swap method works on two integers
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
   * @param ids the ids as input
   * @param sql the sql command that needs to be set up
   * @return the prepared statement that can be used directly where we need 2 ids
   */
  public void setStatement(int[] ids, String sql){
    Connection con=null;
    PreparedStatement statement=null;
    try {
      con = getDBConnection();
      statement = con.prepareStatement(sql);
      for (int i = 0; i < ids.length; i++) {
      statement.setInt(i, ids[i]);
    }
      statement.executeUpdate();
      System.out.println("Record is updated to DBUSER table!");
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
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
    Driver driver = (Driver) Class.forName(driverClass).newInstance();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      Class.forName(driverClass);
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
    try {
      dbConnection = DriverManager.getConnection(jdbcUrl, user,password);
      return dbConnection;
    } catch (SQLException e) {

      System.out.println(e.getMessage());
    }
    return dbConnection;
  }
//
//  private static class getConnection{
//
//    public Connection getConnection() throws Exception {
//      String driverClass = null;
//      String jdbcUrl = null;
//      String user = null;
//      String password = null;
//      InputStream in = getClass().getClassLoader().getResourceAsStream("application.properties");
//      Properties properties = new Properties();
//      properties.load(in);
//      driverClass = properties.getProperty("spring.datasource.driver-class-name");
//      jdbcUrl = properties.getProperty("spring.datasource.url");
//      user = properties.getProperty("spring.datasource.username");
//      password = properties.getProperty("spring.datasource.password");
//      Driver driver = (Driver) Class.forName(driverClass).newInstance();
//      Properties info = new Properties();
//      info.put("user", user);
//      info.put("password", password);
//      Connection connection = driver.connect(jdbcUrl, info);
//      return connection;
//    }
//  }

  }
