package com.npxception.demo.service;

import com.npxception.demo.dao.AssignmentDao;
import com.npxception.demo.entity.Assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;


/**
 * Created by bryan on 6/2/2017.
 */

@Service
public class AssignmentService {

  @Autowired
  @Qualifier("fakeData")
  private AssignmentDao assignmentDao;

  public Collection<Assignment> getAllAssignment() {
    return this.assignmentDao.getAllAssignment();
  }

  public Assignment getAssignmentById(int id) {
    return this.assignmentDao.getAssignmentById(id);
  }

  public void removeAssignmentById(int id) {
    this.assignmentDao.removeAssignmentById(id);
  }

  public void updateAssignment(Assignment assignment) {
    this.assignmentDao.updateAssignment(assignment);
  }

  public void insertAssignment(Assignment assignment) {
    this.assignmentDao.insertAssignmentToDb(assignment);
  }

  public Collection<Assignment> getAssignmentByCourse(String course) {
    return this.assignmentDao.getAssignmentByCourse(course);
  }

  public Collection<Assignment> getAssignmentByContent(String content) {
    return this.assignmentDao.getAssignmentByContent(content);
  }


}
