package com.npxception.demo.dao;

import com.npxception.demo.entity.Course;

import java.util.Collection;

/**
 * Created by bryan on 6/2/2017.
 */
public interface CourseDao {
  Collection<Course> getAllCourses();

  Course getCourseById(int id);

  void removeCourseById(int id);

  void updateCourse(Course course);

  void insertCourseToDb(Course course);

  Collection<Course> getCourseByProf(String prof);

  Collection<Course> getCourseByTitle(String title);

  Collection<Course> getCourseBySize(int size);


}
