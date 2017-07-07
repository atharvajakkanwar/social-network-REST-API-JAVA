package com.npxception.demo.dao;

import com.npxception.demo.entity.FbGroup;

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

public class PostgresSQLGroupDaoImpl implements GroupDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public PostgresSQLGroupDaoImpl() {

  }

  @Override
  public Collection<FbGroup> getAllGroup() {
    final String sql = "SELECT * FROM groups";
    List<FbGroup> groups = jdbcTemplate.query(sql, new PostgresSQLGroupDaoImpl.GroupRowMapper());

    return groups;
  }

  @Override
  public FbGroup getGroupById(int id) {
    final String sql = "SELECT * FROM groups WHERE groupid = ?";
    FbGroup group = jdbcTemplate.queryForObject(sql, new GroupRowMapper(), id);
    return group;
  }

  @Override
  public void removeGroupById(int id) {

  }


  @Override
  public void createGroup(FbGroup fbGroup) {
    final String sql = "INSERT INTO groups (groupID, name, admin) VALUES (?, ?, ?)";
    jdbcTemplate.update(sql, new Object[]{
        fbGroup.getGroupID(),
        fbGroup.getName(),
        fbGroup.getAdmin(),
    });


  }

  @Override
  public Collection<FbGroup> getGroupByName(String name) {
    final String sql = "SELECT * FROM groups WHERE name = ? ";
    List<FbGroup> groups = jdbcTemplate.query(sql, new GroupRowMapper(), name);
    return groups;
  }

  @Override
  public Collection<FbGroup> getGroupByAdmin(String name) {
    return null;
  }

  @Override
  public Collection<FbGroup> getGroupByAdmin(int admin) {
    final String sql = "SELECT * FROM groups WHERE admin = ? ";
    List<FbGroup> groups = jdbcTemplate.query(sql, new GroupRowMapper(), admin);
    return groups;
  }

  @Override
  public Collection<FbGroup> getAllGroupsForUser(int memberid) {
    final String sql = "SELECT * FROM membership WHERE memberid = ? ";
    List<FbGroup> groups = jdbcTemplate.query(sql, new GroupRowMapper(), memberid);
    return groups;

  }


  @Override
  public void addMemberToGroup(int memberid) {

  }

  @Override
  public void removeMemberFromGroup(int memberid) {

  }



  private static class GroupRowMapper implements RowMapper<FbGroup> {
    @Override
    public FbGroup mapRow(ResultSet resultSet, int i) throws SQLException {
      FbGroup group = new FbGroup();
      group.setGroupID(resultSet.getInt("groupid"));
      group.setName(resultSet.getString("name"));
      group.setAdmin(resultSet.getInt("admin"));
      return group;
    }
  }

}