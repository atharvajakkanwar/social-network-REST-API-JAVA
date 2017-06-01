package com.npxception.demo.dao;

import com.npxception.demo.entity.Student;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("fakeData")
public class  FakeStudentDaoImpl implements StudentDao {

  private static Map<Integer, Student> students;

  static {

    students = new HashMap<Integer, Student>() {

      {
        put(1, new Student(1, "Said", "Computer Science", "M", 21));
        put(2, new Student(2, "Alex U", "Finance", "M", 30));
        put(3, new Student(3, "Anna", "Finance", "F", 21));
        put(4, new Student(4, "Anna", "Math", "F", 21));
      }
    };
  }

  @Override
  public Collection<Student> getAllStudents() {
    return this.students.values();
  }

  @Override
  public Student getStudentById(int id) {
    return this.students.get(id);
  }

  @Override
  public void removeStudentById(int id) {
    this.students.remove(id);
  }

  @Override
  public void updateStudent(Student student) {
    Student s = students.get(student.getId());
    s.setCourse(student.getCourse());
    s.setName(student.getName());
    s.setCourse(student.getCourse());
    students.put(student.getId(), student);
  }

  @Override
  public void insertStudentToDb(Student student) {
    this.students.put(student.getId(), student);
  }


  @Override
  public Collection<Student> getStudentsByCourse(String course) {
    HashMap<Integer, Student> studentsByCourse = new HashMap<>();
    for (Map.Entry<Integer, Student> entry : students.entrySet()) {
      if (entry.getValue().getCourse().equals(course)) {
        studentsByCourse.put(entry.getKey(), entry.getValue());
      }
    }
    return studentsByCourse.values();
  }

  @Override
  public Collection<Student> getStudentsByName(String name) {
    HashMap<Integer, Student> studentsByName = new HashMap<>();
    for (Map.Entry<Integer, Student> entry: students.entrySet()) {
      if (entry.getValue().getName().equals(name)) {
        studentsByName.put(entry.getKey(),entry.getValue());
      }
    }
    return studentsByName.values();
  }

  @Override
  public Collection<Student> getStudentsByAge(int age) {
    HashMap<Integer, Student> studentsByAge = new HashMap<>();
    for (Map.Entry<Integer, Student> entry: students.entrySet()) {
      if (entry.getValue().getAge()== age) {
        studentsByAge.put(entry.getKey(),entry.getValue());
      }
    }
    return studentsByAge.values();
  }

  @Override
  public Collection<Student> getStudentsByGender(String gender) {
    HashMap<Integer, Student> studentsByGender = new HashMap<>();
    for (Map.Entry<Integer, Student> entry: students.entrySet()) {
      if (entry.getValue().getGender().equals(gender)) {
        studentsByGender.put(entry.getKey(),entry.getValue());
      }
    }
    return studentsByGender.values();
  }

  @Override
  public void removeStudentByCourse(String course) {
    for (Map.Entry<Integer, Student> entry : students.entrySet()) {
      if (entry.getValue().getCourse().equals(course)) {
        this.students.remove(entry.getKey());
      }
    }
  }

  @Override
  public Collection<Student> getStudentInCourseByName(String course, String name) {
    HashMap<Integer, Student> studentsByCourseAndName = new HashMap<>();
    for (Map.Entry<Integer, Student> entry : students.entrySet()) {
      if (entry.getValue().getCourse().equals(course)
          && entry.getValue().getName().equals(name)) {
        studentsByCourseAndName.put(entry.getKey(), entry.getValue());
      }
    }
    return studentsByCourseAndName.values();
  }

}
