package com.npxception.demo.dao;

import com.npxception.demo.entity.Course;
import com.npxception.demo.entity.Student;

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
public class FakeCourseDaoImpl implements CourseDao{

  private static Map<Integer, Course> courses;

  static {

    courses = new HashMap<Integer, Course>() {

      {
        put(1, new Course(1, "Bob", "Computer Science", 200));
        put(2, new Course(2, "Alex U", "Finance", 30));
        put(3, new Course(3, "Anna", "Finance", 21));
        put(4, new Course(4, "Anna", "Math", 30));
      }
    };
  }
  @Override
  public Collection<Course> getAllCourses() {
    return this.courses.values();
  }

  @Override
  public Course getCourseById(int id) {
    return this.courses.get(id);
  }

  @Override
  public void removeCourseById(int id) {
    this.courses.remove(id);
  }

  @Override
  public void updateCourse(Course course) {
    Course c = courses.get(course.getId());
    c.setProfessor(course.getProfessor());
    c.setTitle(course.getTitle());
    c.setSize(course.getSize());
    courses.put(course.getId(), course);

  }

  @Override
  public void insertCourseToDb(Course course) {
    this.courses.put(course.getId(), course);

  }

  @Override
  public Collection<Course> getCourseByProf(String prof) {
    HashMap<Integer, Course> courseByProf = new HashMap<>();
    for (Map.Entry<Integer, Course> entry : courses.entrySet()) {
      if (entry.getValue().getProfessor().equals(prof)) {
        courseByProf.put(entry.getKey(), entry.getValue());
      }
    }
    return courseByProf.values();
  }

  @Override
  public Collection<Course> getCourseByTitle(String title) {
    HashMap<Integer, Course> courseByTitle = new HashMap<>();
    for (Map.Entry<Integer, Course> entry : courses.entrySet()) {
      if (entry.getValue().getTitle().equals(title)) {
        courseByTitle.put(entry.getKey(), entry.getValue());
      }
    }
    return courseByTitle.values();

  }

  @Override
  public Collection<Course> getCourseBySize(int size) {
    HashMap<Integer, Course> courseBySize = new HashMap<>();
    for (Map.Entry<Integer, Course> entry : courses.entrySet()) {
      if (entry.getValue().getSize()== size) {
        courseBySize.put(entry.getKey(), entry.getValue());
      }
    }
    return courseBySize.values();
  }
}
