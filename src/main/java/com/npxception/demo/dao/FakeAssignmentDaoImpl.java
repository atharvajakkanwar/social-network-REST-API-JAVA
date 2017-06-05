package com.npxception.demo.dao;

import com.npxception.demo.entity.Assignment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bryan on 6/2/2017.
 */

@Repository
@Qualifier("fakeData")
public class FakeAssignmentDaoImpl implements AssignmentDao {

  private static Map<Integer, Assignment> assignments;

  static {

    assignments = new HashMap<Integer, Assignment>() {

      {
        put(1, new Assignment(1, "Computer Science", "Hello World"));
        put(2, new Assignment(2, "Finance", "200"));
        put(3, new Assignment(3, "Chinese", "再見"));
        put(4, new Assignment(4, "Chinese", "掰掰"));
        put(5, new Assignment(5, "Math", "200"));

      }
    };
  }

  @Override
  public Collection<Assignment> getAllAssignment() {
    return this.assignments.values();
  }

  @Override
  public Assignment getAssignmentById(int id) {
    return this.assignments.get(id);
  }

  @Override
  public void removeAssignmentById(int id) {
    this.assignments.remove(id);

  }

  @Override
  public void updateAssignment(Assignment assignment) {
    Assignment a = assignments.get(assignment.getId());
    a.setCourse(assignment.getCourse());
    a.setContent(assignment.getContent());
    assignments.put(assignment.getId(), assignment);

  }

  @Override
  public void insertAssignmentToDb(Assignment assignment) {
    this.assignments.put(assignment.getId(), assignment);
  }

  @Override
  public Collection<Assignment> getAssignmentByCourse(String course) {
    HashMap<Integer, Assignment> assignmentByCourse = new HashMap<>();
    for (Map.Entry<Integer, Assignment> entry : assignments.entrySet()) {
      if (entry.getValue().getCourse().equals(course)) {
        assignmentByCourse.put(entry.getKey(), entry.getValue());
      }
    }
    return assignmentByCourse.values();
  }

  @Override
  public Collection<Assignment> getAssignmentByContent(String content) {
    HashMap<Integer, Assignment> assignmentByContent = new HashMap<>();
    for (Map.Entry<Integer, Assignment> entry : assignments.entrySet()) {
      if (entry.getValue().getContent().equals(content)) {
        assignmentByContent.put(entry.getKey(), entry.getValue());
      }
    }
    return assignmentByContent.values();
  }
}
