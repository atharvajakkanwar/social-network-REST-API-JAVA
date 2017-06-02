package com.npxception.demo.entity;

/**
 * Created by bryan on 6/2/2017.
 */
public class Course {
  private int id;
  private String professor;
  private String title;
  private int size;

  public Course(int id, String professor, String title, int size) {
    this.id = id;
    this.professor = professor;
    this.title = title;
    this.size = size;
  }

  public Course() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getProfessor() {
    return professor;
  }

  public void setProfessor(String professor) {
    this.professor = professor;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
}
