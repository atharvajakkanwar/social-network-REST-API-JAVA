package com.npxception.demo.controller;

import com.npxception.demo.entity.Course;
import com.npxception.demo.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by bryan on 6/2/2017.
 */

@RestController
@RequestMapping("/courses")

public class CourseController {

  @Autowired
  private CourseService courseService;

  @RequestMapping(method = RequestMethod.GET)
  public Collection<Course> getAllCourse() {
    return courseService.getAllCourses();
  }

  @RequestMapping(value = "/id = {id}", method = RequestMethod.GET)
  public Course getCourseById(@PathVariable("id") int id) {
    return courseService.getCourseById(id);
  }

  @RequestMapping(value = "/id = {id}", params = "id", method = RequestMethod.DELETE)
  public void deleteCourseById(@PathVariable("id") int id) {
    courseService.removeCourseById(id);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void insertCourse(@RequestBody Course course) {
    courseService.insertCourse(course);
  }

  @RequestMapping(value = "/professor = {professor}", method = RequestMethod.GET)
  public Collection<Course> getCourseByProf(@PathVariable("professor") String professor) {
    return courseService.getCourseByProf(professor);
  }

  @RequestMapping(value = "/title = {title}", method = RequestMethod.GET)
  public Collection<Course> getCourseByTitle(@PathVariable("title") String title) {
    return courseService.getCourseByTitle(title);
  }

  @RequestMapping(value = "/size = {size}", method = RequestMethod.GET)
  public Collection<Course> getStudentsByName(@PathVariable("size") int size) {
    return courseService.getCourseBySize(size);
  }

}
