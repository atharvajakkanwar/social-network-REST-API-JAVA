<<<<<<< HEAD
=======
package com.npxception.demo.controller;

import com.npxception.demo.entity.FbGroup;
import com.npxception.demo.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Controller for group.
 */

@RestController
@RequestMapping("/Fbgroup")

public class GroupController {

  @Autowired
  private GroupService groupService;


  @RequestMapping(method = RequestMethod.GET)
  public Collection<FbGroup> getAllGroups() {
    return groupService.getAllGroup();
  }


  @RequestMapping(value = "/groupid={groupid}", method = RequestMethod.GET)
  public FbGroup getGroupById(@PathVariable("groupid") int groupid) {
    return groupService.getGroupById(groupid);
  }


  @RequestMapping(value = "/groupid={groupid}", params = "groupid", method = RequestMethod.DELETE)
  public void removeGroupById(@PathVariable("groupid") int groupid) {
    groupService.removeGroupById(groupid);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void createGroup(@RequestBody FbGroup group) {
    groupService.createGroup(group);
  }





  @RequestMapping(value = "/name={name}", method = RequestMethod.GET)
  public Collection<FbGroup> getGroupByName(@PathVariable("name") String name) {
    return groupService.getGroupByName(name);
  }



//  public void updateGroup(FbGroup fbGroup) {
//
//  }


  @RequestMapping(value = "/admin={admin}", method = RequestMethod.GET)
  public Collection<FbGroup> getGroupByAdmin(@PathVariable("admin") int admin) {
    return groupService.getGroupByAdmin(admin);
  }

  @RequestMapping(value = "/memberid={memberid}", method = RequestMethod.GET)
  public Collection<FbGroup> getAllGroupsForUser(@PathVariable("memberid") int memberid) {
    return groupService.getAllGroupsForUser(memberid);
  }



//  void updateNameOfGroup(String name);


//  void updateAdminOfGroup(int admin);


  @RequestMapping(value = "/memberid={memberid}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void addMemberToGroup(@RequestBody int memberid) {
    groupService.addMemberToGroup(memberid);
  }

  @RequestMapping(value = "/memberid={memberid}", params = "memberid", method = RequestMethod.DELETE)
  public void removeMemberFromGroup(@PathVariable("memberid") int memberid) {
    groupService.removeMemberFromGroup(memberid);
  }



}
>>>>>>> 1466c5b71cfb0853faf883e085a642d3185663d9
