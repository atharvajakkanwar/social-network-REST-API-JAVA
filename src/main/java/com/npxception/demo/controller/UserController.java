package com.npxception.demo.controller;

import com.npxception.demo.entity.User;
import com.npxception.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")


public class UserController {

  @Autowired
  private UserService studentService;

  @RequestMapping(method = RequestMethod.GET)
  public Collection<User> getAllStudents() {
    return studentService.getAllUser();
  }

  @RequestMapping(value = "/id = {id}", method = RequestMethod.GET)
  public User getStudentById(@PathVariable("id") int id) {
    return studentService.getUserById(id);
  }

  @RequestMapping(value = "/id = {id}", params = "id", method = RequestMethod.DELETE)
  public void deleteStudentById(@PathVariable("id") int id) {
    studentService.removeUserById(id);
  }

  @RequestMapping(method = RequestMethod.PUT, params = "id", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void deleteStudentById(@RequestBody User student) {
    studentService.updateUser(student);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void insertStudent(@RequestBody User student) {
    studentService.insertUser(student);
  }

//  @RequestMapping(value = "/course = {course}", method = RequestMethod.GET)
//  public Collection<User> getStudentsByCourse(@PathVariable("course") String course) {
//    return studentService.getUserByCourse(course);
//  }

  @RequestMapping(value = "/name = {name}", method = RequestMethod.GET)
  public Collection<User> getStudentsByName(@PathVariable("name") String name) {
    return studentService.getUserByName(name);
  }

  @RequestMapping(value = "/gender = {gender}", method = RequestMethod.GET)
  public Collection<User> getStudentsByGender(@PathVariable("gender") String gender) {
    return studentService.getUserByGender(gender);
  }

  @RequestMapping(value = "/age = {age}", method = RequestMethod.GET)
  public Collection<User> getStudentByCourse(@PathVariable("age") int age) {
    return studentService.getUserByAge(age);
  }

//  @RequestMapping(value = "/course = {course}", method = RequestMethod.DELETE)
//  public void deleteStudentByCourse(@PathVariable("course") String course) {
//    studentService.removeUserByCourse(course);
//  }

  @RequestMapping(method = RequestMethod.PUT, params = "course", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void deleteStudentByCourse(@RequestBody User student) {
    studentService.updateUser(student);
  }

//  @RequestMapping(value = "/{course}/{name}", method = RequestMethod.GET)
//  public Collection<User> getStudentInCourseByName(@PathVariable("course") String course,
//                                                      @PathVariable("name") String name) {
//    return studentService.getUserInCourseByName(course, name);
//  }
}
