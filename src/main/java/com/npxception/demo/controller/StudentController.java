package com.npxception.demo.controller;

import com.npxception.demo.entity.Student;
import com.npxception.demo.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/students")


public class StudentController {

  @Autowired
  private StudentService studentService;

  @RequestMapping(method = RequestMethod.GET)
  public Collection<Student> getAllStudents() {
    return studentService.getAllStudents();
  }

  @RequestMapping(value = "/id = {id}", method = RequestMethod.GET)
  public Student getStudentById(@PathVariable("id") int id) {
    return studentService.getStudentById(id);
  }

  @RequestMapping(value = "/id = {id}", params = "id", method = RequestMethod.DELETE)
  public void deleteStudentById(@PathVariable("id") int id) {
    studentService.removeStudentById(id);
  }

  @RequestMapping(method = RequestMethod.PUT, params = "id", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void deleteStudentById(@RequestBody Student student) {
    studentService.updateStudent(student);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void insertStudent(@RequestBody Student student) {
    studentService.insertStudent(student);
  }

  @RequestMapping(value = "/course = {course}", method = RequestMethod.GET)
  public Collection<Student> getStudentsByCourse(@PathVariable("course") String course) {
    return studentService.getStudentsByCourse(course);
  }

  @RequestMapping(value = "/name = {name}", method = RequestMethod.GET)
  public Collection<Student> getStudentsByName(@PathVariable("name") String name) {
    return studentService.getStudentsByName(name);
  }

  @RequestMapping(value = "/gender = {gender}", method = RequestMethod.GET)
  public Collection<Student> getStudentsByGender(@PathVariable("gender") String gender) {
    return studentService.getStudentsByGender(gender);
  }

  @RequestMapping(value = "/age = {age}", method = RequestMethod.GET)
  public Collection<Student> getStudentByCourse(@PathVariable("age") int age) {
    return studentService.getStudentsByAge(age);
  }

  @RequestMapping(value = "/course = {course}", method = RequestMethod.DELETE)
  public void deleteStudentByCourse(@PathVariable("course") String course) {
    studentService.removeStudentByCourse(course);
  }

  @RequestMapping(method = RequestMethod.PUT, params = "course", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void deleteStudentByCourse(@RequestBody Student student) {
    studentService.updateStudent(student);
  }

  @RequestMapping(value = "/{course}/{name}", method = RequestMethod.GET)
  public Collection<Student> getStudentInCourseByName(@PathVariable("course") String course,
                                                      @PathVariable("name") String name) {
    return studentService.getStudentInCourseByName(course, name);
  }
}
