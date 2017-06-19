package com.npxception.demo.dao;

import com.npxception.demo.entity.FbGroup;


import java.util.Collection;

/**
 * Created by bryan on 6/2/2017.
 */
public interface GroupDao {
  Collection<FbGroup> getAllGroup();

  FbGroup getGroupById(int id);

  void removeGroupById(int id);

  void updateGroup(FbGroup course);

  void insertGroupToDb(FbGroup course);


  Collection<FbGroup> getGroupByGroupName(String groupName);




}
