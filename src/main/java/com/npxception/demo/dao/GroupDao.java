package com.npxception.demo.dao;

import com.npxception.demo.entity.FbGroup;


import java.util.Collection;

/**
 * DAO class for group.
 */
public interface GroupDao {

  /**
   * Returns every group in the database.
   *
   * @return every group in the database
   * @param userid
   */
  Collection<FbGroup> getAllGroup(int userid);

  /**
   * Given a ID return the corresponding group.
   *
   *
   * @param userid
   * @param id The group id in the group database
   * @return a group given an ID
   */
  FbGroup getGroupById(int userid, int id);

  /**
   * Given an ID removes the group from the database.
   *
   * @param userid
   * @param id The group id in the group database
   */
  void removeGroupById(int userid, int id);


  /**
   * Given a facebook group, adds the group into the database.
   *
   * @param fbGroup a facebook group that will be inputted into database.
   */
  void createGroup(FbGroup fbGroup);

  /**
   * Given a name, returns every group that has that name.
   *
   *
   * @param userid
   * @param name The group's name in the database
   * @return every group with the given name
   */
  Collection<FbGroup> getGroupByName(int userid, String name);

  /**
   * Given an admin, returns every group with that administrator.
   *
   *
   * @param userid
   * @param name The group's admin ID in the database
   * @returnevery group with the administrator
   */
  Collection<FbGroup> getGroupByAdmin(int userid, String name);

//  /**
//   * Given an admin, returns every group with that administrator.
//   * @param admin The group's admin ID in the database
//   * @return every group with the administrator
//   */
//  Collection<FbGroup> getGroupByAdmin(int admin);

  /**
   * Given a member ID, returns every group that the member is in.
   *
   *
   * @param userid
   * @param memberid The member's ID in the database
   * @return Every group taht the member is in
   */
  Collection<FbGroup> getAllGroupsForUser(int userid, int memberid);

  /**
   * Send a joinging group request to a group, after the
   * request is sent, the status is pending accepted(2).
   * @param groupid the id of the group
   * @param memberid the id of the user
   */

  void sendJoinRequest(int groupid, int memberid);
  
  /**
   * Given member ID, adds member to group.
   *
   * @param userid
   * @param memberid The group's ID in the database
   */
  void addMemberToGroup(int userid, int groupid, int memberid);

  /**
   * Given member ID, removes member from group.
   *
   * @param userid
   * @param memberid The group's ID in the database
   */
  void removeMemberFromGroup(int userid, int groupid, int memberid);


}