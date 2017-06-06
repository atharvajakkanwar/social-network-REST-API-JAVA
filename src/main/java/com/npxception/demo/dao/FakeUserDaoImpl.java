package com.npxception.demo.dao;

import com.npxception.demo.entity.User;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("fakeData")
public class FakeUserDaoImpl implements UserDao {

  private static Map<Integer, User> students;

  static {

    students = new HashMap<Integer, User>() {

      {
        put(1, new User(1, "A", "Said", "asaid@gmail.com",21, "M", "USA", "Seattle", "123456"));
        put(2, new User(2, "Alex", "U", "alexu@gmail.com",22, "M", "USA", "Seattle", "123456"));
        put(3, new User(3, "Anna", "McAnna", "annamcanna@gmail.com",23, "F", "XYZ", "QWE", "asdf"));
        put(4, new User(4, "Anna", "Said", "annasaid@gmail.com",21, "F", "XYZ", "Seattle", "qwer"));
      }
    };
  }

  @Override
  public Collection<User> getAllUser() {
    return this.students.values();
  }

  @Override
  public User getUserById(int id) {
    return this.students.get(id);
  }

  @Override
  public void removeUserById(int id) {
    this.students.remove(id);
  }

  @Override
  public void updateUser(User student) {
    User s = students.get(student.getId());
    s.setName(student.getFirstName());
    students.put(student.getId(), student);
  }

  @Override
  public void insertUserToDb(User student) {
    this.students.put(student.getId(), student);
  }


//  @Override
//  public Collection<User> getUserByCourse(String course) {
//    HashMap<Integer, User> studentsByCourse = new HashMap<>();
//    for (Map.Entry<Integer, User> entry : students.entrySet()) {
//      if (entry.getValue().getCourse().equals(course)) {
//        studentsByCourse.put(entry.getKey(), entry.getValue());
//      }
//    }
//    return studentsByCourse.values();
//  }

  @Override
  public Collection<User> getUserByName(String name) {
    HashMap<Integer, User> studentsByName = new HashMap<>();
    for (Map.Entry<Integer, User> entry : students.entrySet()) {
      if (entry.getValue().getFirstName().equals(name)) {
        studentsByName.put(entry.getKey(), entry.getValue());
      }
    }
    return studentsByName.values();
  }

  @Override
  public Collection<User> getUserByAge(int age) {
    HashMap<Integer, User> studentsByAge = new HashMap<>();
    for (Map.Entry<Integer, User> entry : students.entrySet()) {
      if (entry.getValue().getAge() == age) {
        studentsByAge.put(entry.getKey(), entry.getValue());
      }
    }
    return studentsByAge.values();
  }

  @Override
  public Collection<User> getUserByGender(String gender) {
    HashMap<Integer, User> studentsByGender = new HashMap<>();
    for (Map.Entry<Integer, User> entry : students.entrySet()) {
      if (entry.getValue().getGender().equals(gender)) {
        studentsByGender.put(entry.getKey(), entry.getValue());
      }
    }
    return studentsByGender.values();
  }

//  @Override
//  public void removeUserByCourse(String course) {
//    for (Map.Entry<Integer, User> entry : students.entrySet()) {
//      if (entry.getValue().getCourse().equals(course)) {
//        this.students.remove(entry.getKey());
//      }
//    }
//  }
//
//  @Override
//  public Collection<User> getUserInCourseByName(String course, String name) {
//    HashMap<Integer, User> studentsByCourseAndName = new HashMap<>();
//    for (Map.Entry<Integer, User> entry : students.entrySet()) {
//      if (entry.getValue().getCourse().equals(course)
//          && entry.getValue().getFirstName().equals(name)) {
//        studentsByCourseAndName.put(entry.getKey(), entry.getValue());
//      }
//    }
//    return studentsByCourseAndName.values();
//  }

}
