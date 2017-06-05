package com.npxception.demo.dao;

import com.npxception.demo.entity.Student;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
@Qualifier("mongoData")
public class MongoStudentDaoImpl implements StudentDao {


  @Override
  public Collection<Student> getAllStudents() {
    return new ArrayList<Student>() {
      {
        add(new Student(1, "Mario", "Nothing", "M", 5));
      }
    };
  }

  @Override
  public Student getStudentById(int id) {
    return null;
  }

  @Override
  public void removeStudentById(int id) {

  }

  @Override
  public void updateStudent(Student student) {

  }

  @Override
  public void insertStudentToDb(Student student) {

  }

  @Override
  public Collection<Student> getStudentsByCourse(String course) {
    return null;
  }

  @Override
  public Collection<Student> getStudentsByName(String name) {
    return null;
  }

  @Override
  public Collection<Student> getStudentsByAge(int age) {
    return null;
  }

  @Override
  public Collection<Student> getStudentsByGender(String gender) {
    return null;
  }

  @Override
  public void removeStudentByCourse(String course) {

  }

  @Override
  public Collection<Student> getStudentInCourseByName(String course, String name) {
    return null;
  }
}
