package com.npxception.demo.service;

import com.npxception.demo.dao.StudentDao;
import com.npxception.demo.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {

  @Autowired
  @Qualifier("fakeData")
  private StudentDao studentDao;

  public Collection<Student> getAllStudents() {
    return this.studentDao.getAllStudents();
  }

  public Student getStudentById(int id) {
    return this.studentDao.getStudentById(id);
  }


  public void removeStudentById(int id) {
    this.studentDao.removeStudentById(id);
  }

  public void updateStudent(Student student) {
    this.studentDao.updateStudent(student);
  }

  public void insertStudent(Student student) {
    this.studentDao.insertStudentToDb(student);
  }

  public Collection<Student> getStudentsByCourse(String course) {
    return this.studentDao.getStudentsByCourse(course);
  }

  public Collection<Student> getStudentsByName(String name) {
    return this.studentDao.getStudentsByName(name);
  }

  public Collection<Student> getStudentsByGender(String gender) {
    return this.studentDao.getStudentsByGender(gender);
  }

  public Collection<Student> getStudentsByAge(int age) {
    return this.studentDao.getStudentsByAge(age);
  }

  public void removeStudentByCourse(String course) {
    this.studentDao.removeStudentByCourse(course);
  }

  public Collection<Student> getStudentInCourseByName(String course, String name) {
    return this.studentDao.getStudentInCourseByName(course, name);
  }
}
