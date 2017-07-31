package com.npxception.demo.dao;

import com.npxception.demo.entity.FbGroup;
import com.npxception.demo.entity.User;
import com.npxception.demo.exceptions.AuthenticationException;
import com.npxception.demo.helperMethods.UserInformation;
import com.npxception.demo.helperMethods.UserRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;


/**
 * Concrete class for group dao.
 */
@Repository("PostgresGroupRepo")
public class PostgreSQLGroupDaoImpl implements GroupDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private UserDao userDao;



  @Override
  public Collection<FbGroup> getAllGroup() {
    final String sql = "SELECT * FROM groups";
    List<FbGroup> groups = jdbcTemplate.query(sql, new GroupRowMapper());
    return groups;
  }

  @Override
  public FbGroup getGroupById(int id) {
//    new UserInformation().checkUser(id);
    final String sql = "SELECT * FROM groups WHERE groupid = ?";
    FbGroup group = jdbcTemplate.queryForObject(sql, new GroupRowMapper(), id);
    return group;
  }

  @Override
  public void removeGroupById(int id) {
    final String sql = "DELETE FROM groups WHERE groupid = ?";
    jdbcTemplate.update(sql, id);
  }

  @Override
  public void createGroup(FbGroup fbGroup) {
    final String sql = "INSERT INTO groups (groupname, groupadmin) VALUES ( ?, ?)";

    jdbcTemplate.update(sql, new Object[]{
        fbGroup.getName(),
        fbGroup.getAdmin(),
    });
  }

  @Override
  public Collection<FbGroup> getGroupByName(String name) {

    final String sql = "SELECT * FROM groups WHERE groupname = ? ";
    List<FbGroup> groups = jdbcTemplate.query(sql, new GroupRowMapper(), name);
    return groups;
  }

  @Override
  public Collection<FbGroup> getGroupByAdmin(String name) {
    final String sql = "SELECT * FROM groups WHERE groupadmin = ? ";
    List<FbGroup> groups = jdbcTemplate.query(sql, new GroupRowMapper(), name);
    return groups;
  }

//  @Override
//  public Collection<FbGroup> getGroupByAdmin(int admin) {
//    User user = userDao.getUserById(admin);
//    String fullName = user.getFirstName() + " " + user.getLastName();
//    final String sql = "SELECT * FROM groups WHERE admin = ? ";
//    List<FbGroup> groups = jdbcTemplate.query(sql, new GroupRowMapper(), fullName);
//    return groups;
//  }

//  @Override
//  public Collection<FbGroup> getGroupByAdmin(int admin) {
//    User user = userDao.getUserById(admin);
//    String fullName = user.getFirstName() + " " + user.getLastName();
//    final String sql = "SELECT * FROM groups WHERE admin = ? ";
//    List<FbGroup> groups = jdbcTemplate.query(sql, new GroupRowMapper(), fullName);
//    return groups;
//  }

  @Override
  public Collection<FbGroup> getAllGroupsForUser(int memberid) {
   // new UserInformation().checkUser(memberid);
    final String sql = "SELECT g.* FROM groups g, membership m " +
        "WHERE g.groupid = m.groupid AND memberid = ? ";
    List<FbGroup> groups = jdbcTemplate.query(sql, new GroupRowMapper(), memberid);
    return groups;
  }


  @Override
  public void addMemberToGroup(int groupid, int memberid) {
    // change status from 3 to 1
    String sql = "UPDATE membership SET status = 1 WHERE groupid = ? AND memberid = ?";
    jdbcTemplate.update(sql,new Object[]{groupid, memberid});
  }

  @Override
  public void sendJoinRequest(int groupid, int memberid){
    String sql = "INSERT INTO membership(groupid, memberid, status) SELECT ?,?,? " +
        "WHERE NOT EXISTS (SELECT * FROM membership WHERE (groupid = ? AND memberid = ?))";
    jdbcTemplate.update(sql, new Object[]{groupid, memberid, 2, groupid, memberid});
  }


  @Override
  public void removeMemberFromGroup(int groupid, int memberid) {
    // remove
    final String sql = "DELETE FROM membership" +
        " WHERE  groupid = ? AND memberid = ?";
    jdbcTemplate.update(sql, new Object[]{groupid, memberid});
  }


  private static class GroupRowMapper implements RowMapper<FbGroup> {

    @Override
    public FbGroup mapRow(ResultSet resultSet, int i) throws SQLException {
      FbGroup group = new FbGroup();
      group.setGroupID(resultSet.getInt("groupid"));
      group.setName(resultSet.getString("groupname"));
      group.setAdmin(resultSet.getString("groupadmin"));
      return group;
    }
  }

  public void checkUser(int id){
    String sql0 = "SELECT * FROM users WHERE userid = ?";
    User user = jdbcTemplate.queryForObject(sql0, new Object[]{id}, new UserRowMapper());
    String email = user.getEmail();
    String password = user.getPassword();
    String sql1 = "SELECT email FROM loginfo";
    String sql2 = "SELECT password FROM loginfo";
    String loginEmail = jdbcTemplate.queryForObject(sql1, new Object[]{}, String.class);
    String loginPass = jdbcTemplate.queryForObject(sql2, new Object[]{}, String.class);
    if ((!email.equals(loginEmail)) || (!password.equals(loginPass))){
      throw new AuthenticationException(id);
    }
  }
}