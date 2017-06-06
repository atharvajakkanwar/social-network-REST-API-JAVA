package com.npxception.demo.dao;

import com.npxception.demo.entity.Assignment;

import java.util.Collection;

public interface AssignmentDao {
  Collection<Assignment> getAllAssignment();

  Assignment getAssignmentById(int id);

  void removeAssignmentById(int id);

  void updateAssignment(Assignment assignment);

  void insertAssignmentToDb(Assignment assignment);

  Collection<Assignment> getAssignmentByCourse(String course);

  Collection<Assignment> getAssignmentByContent(String content);

}
