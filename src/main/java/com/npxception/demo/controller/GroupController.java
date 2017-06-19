package com.npxception.demo.controller;

import com.npxception.demo.entity.FbGroup;
import com.npxception.demo.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by bryan on 6/2/2017.
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

  @RequestMapping(value = "/groupID={groupID}", method = RequestMethod.GET)
  public FbGroup getGroupById(@PathVariable("groupID") int id) {
    return groupService.getGroupById(id);
  }

  @RequestMapping(value = "/groupID = {groupID}", params = "groupID", method = RequestMethod.DELETE)
  public void deleteGroupById(@PathVariable("groupID") int groupid) {
    groupService.removeGroupById(groupid);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void insertGroup(@RequestBody FbGroup group) {
    groupService.insertGroup(group);
  }

//  @RequestMapping(value = "/professor = {professor}", method = RequestMethod.GET)
//  public Collection<Group> getGroupByProf(@PathVariable("professor") String professor) {
//    return groupService.getGroupByProf(professor);
//  }

  @RequestMapping(value = "/groupName={groupName}", method = RequestMethod.GET)
  public Collection<FbGroup> getGroupByGroupName(@PathVariable("groupName") String groupName) {
    return groupService.getGroupByGroupName(groupName);
  }

//  @RequestMapping(value = "/size = {size}", method = RequestMethod.GET)
//  public Collection<Group> getGroupByName(@PathVariable("size") int size) {
//    return groupService.getGroupBySize(size);
//  }

}
