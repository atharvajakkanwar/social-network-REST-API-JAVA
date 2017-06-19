package com.npxception.demo.dao;

import com.npxception.demo.entity.FbGroup;

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
 * Created by Robert on 6/5/2017.
 */
@Repository("PostgresGroup")
public class PostgresSQLGroupDaoImpl implements GroupDao {


  @Autowired
  private JdbcTemplate jdbcTemplate;

  public PostgresSQLGroupDaoImpl() {

  }

  public Connection connect() throws SQLException {
    return DriverManager.getConnection("jdbc:postgresql://localhost/", "postgres", "postgres");
  }

  @Override
  public Collection<FbGroup> getAllGroup() {
    //SELECT column_name(s) FROM table_name
    final String sql = "SELECT * FROM groups";
    List<FbGroup> groups = jdbcTemplate.query(sql, new PostgresSQLGroupDaoImpl.GroupRowMapper());

    return groups;
  }

  @Override
  public FbGroup getGroupById(int id) {

    final String sql = "SELECT * FROM users WHERE userid = ?";
    FbGroup group = jdbcTemplate.queryForObject(sql, new GroupRowMapper(), id);

    return group;
  }

  @Override
  public void removeGroupById(int id) {

  }

  @Override
  public void updateGroup(FbGroup course) {

  }


  @Override
  public void insertGroupToDb(FbGroup group) {
    //INSERT INTO table_name (column1, column2, column3,...)
    //VALUES (value1, value2, value3,...)
    final String sql = "INSERT INTO groups (groupID, adminID, groupName, memberID) VALUES (?, ?, ?, ?)";

    jdbcTemplate.update(sql, new Object[]{
        group.getGroupID(),
        group.getAdminID(),
        group.getGroupName(),
        group.getMemberID(),
        "PASS"
    });

  }

  @Override
  public Collection<FbGroup> getGroupByGroupName(String groupName) {
    return null;
  }


  private static class GroupRowMapper implements RowMapper<FbGroup> {
    @Override
    public FbGroup mapRow(ResultSet resultSet, int i) throws SQLException {
      FbGroup group = new FbGroup();
      group.setGroupID(resultSet.getInt("groupid"));
      group.setAdminID(resultSet.getInt("adminid"));
      group.setGroupName(resultSet.getString("groupname"));
      group.setMemberID(resultSet.getInt("memberid"));
      return group;
    }
  }

}
