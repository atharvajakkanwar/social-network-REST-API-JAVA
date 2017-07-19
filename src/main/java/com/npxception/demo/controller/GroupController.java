package com.npxception.demo.controller;

import com.npxception.demo.entity.FbGroup;
import com.npxception.demo.exceptions.ResourceNotFoundException;
import com.npxception.demo.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Represents a controller for the Group Service.
 */

@RestController
@RequestMapping("/Fbgroup")
@Api(description = "Group Controller")

public class GroupController {

  @Autowired
  private GroupService groupService;

  @ApiOperation(value = "Return every group in the database")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of groups"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
  })
  @RequestMapping(method = RequestMethod.GET)
  public Collection<FbGroup> getAllGroups() {
    return groupService.getAllGroup();
  }


  @ApiOperation(value = "Return group given ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved group"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/groupid/{groupid}", method = RequestMethod.GET)
  public FbGroup getGroupById(@ApiParam(value = "group ID", required = true)
                              @PathVariable("groupid") int groupid) {
    return groupService.getGroupById(groupid);
  }


  @ApiOperation(value = "Removes a group given ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully removed group"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/groupid/{groupid}", params = "groupid", method = RequestMethod.DELETE)
  public void removeGroupById(@ApiParam(value = "group ID", required = true)
                              @PathVariable("groupid") int groupid) {
    groupService.removeGroupById(groupid);
  }

  @ApiOperation(value = "Create a new group")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully created group"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void createGroup(@ApiParam(value = "Group", required = true)
                          @RequestBody FbGroup group) {
    groupService.createGroup(group);
  }


  @ApiOperation(value = "Returns every group given name")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of groups"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
  public Collection<FbGroup> getGroupByName(@ApiParam(value = "Group name", required = true)
                                            @PathVariable("name") String name) {
    try {
      return groupService.getGroupByName(name);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException(name);
    }
  }


  @ApiOperation(value = "Returns every group given Admin ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of groups"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/admin/{admin}", method = RequestMethod.GET)
  public Collection<FbGroup> getGroupByAdmin(@ApiParam(value = "Group Admin", required = true)
                                             @PathVariable("admin") String admin) {
    Collection<FbGroup> result = groupService.getGroupByAdmin(admin);
    if (result.size() == 0) {
      throw new ResourceNotFoundException(admin);
    }
    return result;
  }

  @ApiOperation(value = "Returns every group member is in given ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of groups"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/memberid/{memberid}", method = RequestMethod.GET)
  public Collection<FbGroup> getAllGroupsForUser(@ApiParam(value = "Membership ID", required = true)
                                                 @PathVariable("memberid") int memberid) {
    Collection<FbGroup> result = groupService.getAllGroupsForUser(memberid);
    if (result.size() == 0) {
      throw new ResourceNotFoundException(Integer.toString(memberid));
    }
    return result;
  }

  @ApiOperation(value = "Adds member to group")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully added member to group"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/memberid/{memberid}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void addMemberToGroup(@ApiParam(value = "group ID", required = true)
                               @PathVariable("groupid") int groupid,
                               @ApiParam(value = "Membership ID", required = true)
                               @PathVariable("memberid") int memberid) {
    groupService.addMemberToGroup(groupid, memberid);

  }

  @ApiOperation(value = "Removes member from a group")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully removed member from group"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/groupid/{groupid}/memberid/{memberid}",
      params = "memberid", method = RequestMethod.DELETE)
  public void removeMemberFromGroup(@ApiParam(value = "group ID", required = true)
                                    @PathVariable("groupid") int groupid,
                                    @ApiParam(value = "Membership ID", required = true)
                                    @PathVariable("memberid") int memberid) {
    groupService.removeMemberFromGroup(groupid, memberid);
  }
}