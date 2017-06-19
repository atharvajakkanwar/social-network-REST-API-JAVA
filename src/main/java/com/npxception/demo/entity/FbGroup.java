package com.npxception.demo.entity;

/**
 * Created by bryan on 6/2/2017.
 */
public class FbGroup {
  private int groupID;
  private int adminID;
  private String groupName;
  private int memberID;

  public FbGroup(int groupID, int adminID, String groupName, int memberID) {
    this.groupID = groupID;
    this.adminID = adminID;
    this.groupName = groupName;
    this.memberID = memberID;
  }

  public FbGroup() {
  }

  public int getGroupID() {
    return groupID;
  }

  public void setGroupID(int groupID) {
    this.groupID = groupID;
  }

  public int getAdminID() {
    return adminID;
  }

  public void setAdminID(int adminID) {
    this.adminID = adminID;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public int getMemberID() {
    return memberID;
  }

  public void setMemberID(int memberID) {
    this.memberID = memberID;
  }
}
