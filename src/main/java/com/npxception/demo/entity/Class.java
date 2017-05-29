package com.npxception.demo.entity;

/**
 * Created by bryan on 5/28/2017.
 */
public class Class {
  private int classID;
  private String name;
  private String building;
  private String professor;
  private int size;

  public Class(int classID, String name, String building, String professor, int size) {
    this.classID = classID;
    this.name = name;
    this.building = building;
    this.professor = professor;
    this.size = size;
  }

  public Class(){};

  public int getClassID() {
    return classID;
  }

  public void setClassID(int classID) {
    this.classID = classID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBuilding() {
    return building;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public String getProfessor() {
    return professor;
  }

  public void setProfessor(String professor) {
    this.professor = professor;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
}
