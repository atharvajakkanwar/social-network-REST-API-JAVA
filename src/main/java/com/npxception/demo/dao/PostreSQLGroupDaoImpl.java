package com.npxception.demo.dao;

import com.npxception.demo.entity.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by RachelDi on 18/06/2017.
 */
@Repository("PostgreGroup")
public class PostreSQLGroupDaoImpl implements GroupDao{

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Collection<Group> getAllGroup() {
    return null;
  }

  @Override
  public Group getGroupById(int id) {
    return null;
  }

  @Override
  public void removeGroupById(int id) {

  }

  @Override
  public void updateGroup(Group course) {

  }

  @Override
  public void insertGroupToDb(Group course) {

  }

  @Override
  public Collection<Group> getGroupByGroupName(String groupName) {
    return null;
  }
}
