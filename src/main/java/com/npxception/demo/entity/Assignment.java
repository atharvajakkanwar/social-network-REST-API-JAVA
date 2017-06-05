package com.npxception.demo.entity;

/**
 * Created by bryan on 6/2/2017.
 */
public class Assignment {
  private int id;
  private String course;
  private String content;

  public Assignment(int id, String course, String content) {
    this.id = id;
    this.course = course;
    this.content = content;
  }

  public Assignment(){}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
