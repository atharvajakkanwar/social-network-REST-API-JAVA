package com.npxception.demo.dao;

import com.npxception.demo.entity.Student;

import java.util.Collection;

public interface StudentDao {
  Collection<Student> getAllStudents();

  Student getStudentById(int id);

  void removeStudentById(int id);

  void updateStudent(Student student);

  void insertStudentToDb(Student student);

  Collection<Student> getStudentsByCourse(String course);

  Collection<Student> getStudentsByName(String name);

  Collection<Student> getStudentsByAge(int age);

  Collection<Student> getStudentsByGender(String gender);

  void removeStudentByCourse(String course);

  // get students in course by name, return a list of students
  Collection<Student> getStudentInCourseByName(String course, String name);

}
