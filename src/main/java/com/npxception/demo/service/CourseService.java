package com.npxception.demo.service;

import com.npxception.demo.dao.CourseDao;
import com.npxception.demo.entity.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;


/**
 * Created by bryan on 6/2/2017.
 */

@Service
public class CourseService {

  @Autowired
  @Qualifier("fakeData")
  private CourseDao courseDao;

  public Collection<Course> getAllCourses() { return this.courseDao.getAllCourses();}
  public Course getCourseById(int id) {return this.courseDao.getCourseById(id);}
  public void removeCourseById(int id) {this.courseDao.removeCourseById(id);}
  public void updateCourse(Course course) {this.courseDao.updateCourse(course);}
  public void insertCourse(Course course) {this.courseDao.insertCourseToDb(course);}
  public Collection<Course> getCourseByProf(String prof) {
    return this.courseDao.getCourseByProf(prof);
  }

  public Collection<Course> getCourseByTitle(String title) {
    return this.courseDao.getCourseByTitle(title);
  }

  public Collection<Course> getCourseBySize(int size) {
    return this.courseDao.getCourseBySize(size);
  }

}
