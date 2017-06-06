package com.npxception.demo.dao;

import com.npxception.demo.entity.Group;

import java.util.Collection;

/**
 * Created by bryan on 6/2/2017.
 */
public interface GroupDao {
  Collection<Group> getAllGroup();

  Group getGroupById(int id);

  void removeGroupById(int id);

  void updateGroup(Group course);

  void insertGroupToDb(Group course);


  Collection<Group> getGroupByGroupName(String groupName);




}
