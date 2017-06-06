package com.npxception.demo.dao;

import com.npxception.demo.entity.Group;

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
public class FakeGroupDaoImpl implements GroupDao {

  private static Map<Integer, Group> courses;

  static {

    courses = new HashMap<Integer, Group>() {

      {
        put(1, new Group(1, 1, "Computer Science", 200));
        put(2, new Group(2, 2, "Finance", 30));
        put(3, new Group(3, 3, "Finance", 21));
        put(4, new Group(4, 4, "Math", 30));
      }
    };
  }

  @Override
  public Collection<Group> getAllGroup() {
    return this.courses.values();
  }

  @Override
  public Group getGroupById(int id) {
    return this.courses.get(id);
  }

  @Override
  public void removeGroupById(int id) {
    this.courses.remove(id);
  }

  @Override
  public void updateGroup(Group course) {
//    Group c = courses.get(course.getGroupID());
//    c.setProfessor(course.getProfessor());
//    c.setTitle(course.getTitle());
//    c.setSize(course.getSize());
//    courses.put(course.getId(), course);

  }

  @Override
  public void insertGroupToDb(Group course) {
    this.courses.put(course.getGroupID(), course);

  }



  @Override
  public Collection<Group> getGroupByGroupName(String title) {
    HashMap<Integer, Group> courseByTitle = new HashMap<>();
    for (Map.Entry<Integer, Group> entry : courses.entrySet()) {
      if (entry.getValue().getGroupName().equals(title)) {
        courseByTitle.put(entry.getKey(), entry.getValue());
      }
    }
    return courseByTitle.values();

  }

//  @Override
//  public Collection<Group> getGroupBySize(int size) {
//    HashMap<Integer, Group> courseBySize = new HashMap<>();
//    for (Map.Entry<Integer, Group> entry : courses.entrySet()) {
//      if (entry.getValue().getSize() == size) {
//        courseBySize.put(entry.getKey(), entry.getValue());
//      }
//    }
//    return courseBySize.values();
//  }
}
