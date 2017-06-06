package com.npxception.demo.service;

import com.npxception.demo.dao.GroupDao;
import com.npxception.demo.entity.Group;

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
  @Qualifier("fakeData")
  private GroupDao courseDao;

  public Collection<Group> getAllGroup() {
    return this.courseDao.getAllGroup();
  }

  public Group getGroupById(int id) {
    return this.courseDao.getGroupById(id);
  }

  public void removeGroupById(int id) {
    this.courseDao.removeGroupById(id);
  }

  public void updateGroup(Group course) {
    this.courseDao.updateGroup(course);
  }

  public void insertGroup(Group course) {
    this.courseDao.insertGroupToDb(course);
  }


  public Collection<Group> getGroupByGroupName(String title) {
    return this.courseDao.getGroupByGroupName(title);
  }



}
