package com.npxception.demo.dao;

import com.npxception.demo.entity.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("fakeData")
public class FakeStudentDaoImpl implements StudentDao {

    private static Map<Integer, Student> students;

    static {

        students = new HashMap<Integer, Student>(){

            {
                put(1, new Student(1, "Said", "Computer Science"));
                put(2, new Student(2, "Alex U", "Finance"));
                put(3, new Student(3, "Anna", "Finance"));
            }
        };
    }

    @Override
    public Collection<Student> getAllStudents(){
        return this.students.values();
    }

    @Override
    public Student getStudentById(int id){
        return this.students.get(id);
    }

    @Override
    public void removeStudentById(int id) {
        this.students.remove(id);
    }

    @Override
    public void updateStudent(Student student){
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
    public Student getStudentByCourse(String course) {
        for (Map.Entry<Integer, Student> entry : students.entrySet()){
            if (entry.getValue().getCourse().equals(course)){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void removeStudentByCourse(String course) {
        for (Map.Entry<Integer, Student> entry : students.entrySet()){
            if (entry.getValue().getCourse().equals(course)){
                this.students.remove(entry.getKey());
            }
        }
    }
}
