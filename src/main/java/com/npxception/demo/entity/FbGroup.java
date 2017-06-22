package com.npxception.demo.entity;

/**
 * Created by bryan on 6/2/2017.
 */
public class FbGroup {
  private int groupid;
  private int admin;
  private String name;


  /**
   * Constructor for a facebook group.
   * @param groupid The group's group id in the database
   * @param admin The group's admin id in the database
   * @param name The group's name in the database
   */
  public FbGroup(int groupid, int admin, String name) {
    this.groupid = groupid;
    this.admin = admin;
    this.name = name;
  }

  public FbGroup() {
  }

  /**
   * Getter for group's ID in the database.
   * @return group's ID in the database
   */
  public int getGroupID() {
    return groupid;
  }

  /**
   * Setter for group's ID in the database.
   * @param groupid The group's group id in the database
   */
  public void setGroupID(int groupid) {
    this.groupid = groupid;
  }

  /**
   * Getter for group's admin id in the database.
   * @return group's admin id in the database
   */
  public int getAdmin() {
    return admin;
  }

  /**
   * Setter for group's admin id in the database.
   * @param admin The group's admin id in the database
   */
  public void setAdmin(int admin) {
    this.admin = admin;
  }

  /**
   * Getter for group's name in the database.
   * @return group's name in the database
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for group's name in the database.
   * @param name The group's name in the database
   */
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FbGroup fbGroup = (FbGroup) o;

    if (groupid != fbGroup.groupid) return false;
    if (admin != fbGroup.admin) return false;
    return name != null ? name.equals(fbGroup.name) : fbGroup.name == null;
  }

  @Override
  public int hashCode() {
    int result = groupid;
    result = 31 * result + admin;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "FbGroup{" +
        "groupid=" + groupid +
        ", admin=" + admin +
        ", name='" + name + '\'' +
        '}';
  }
}