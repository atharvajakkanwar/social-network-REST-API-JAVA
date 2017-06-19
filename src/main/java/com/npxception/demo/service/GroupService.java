package com.npxception.demo.service;

import com.npxception.demo.dao.GroupDao;
import com.npxception.demo.entity.FbGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;


/**
 * Created by bryan on 6/2/2017.
 */

@Service
public class GroupService {

  @Autowired
  @Qualifier("PostgresGroup")
  private GroupDao courseDao;

  public Collection<FbGroup> getAllGroup() {
    return this.courseDao.getAllGroup();
  }

  public FbGroup getGroupById(int id) {
    return this.courseDao.getGroupById(id);
  }

  public void removeGroupById(int id) {
    this.courseDao.removeGroupById(id);
  }

  public void updateGroup(FbGroup course) {
    this.courseDao.updateGroup(course);
  }

  public void insertGroup(FbGroup course) {
    this.courseDao.insertGroupToDb(course);
  }


  public Collection<FbGroup> getGroupByGroupName(String title) {
    return this.courseDao.getGroupByGroupName(title);
  }



}
