package com.npxception.demo.controller;

import com.npxception.demo.entity.FbGroup;
import com.npxception.demo.exceptions.ResourceNotFoundException;
import com.npxception.demo.helperMethods.AccessManager;
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
 *
 * Represents a controller for the Group Service.
 */

@RestController
@RequestMapping("/{userid}/Fbgroup")
@Api(description = "Group Controller")

public class GroupController {

  private AccessManager accessManager;
  /*
  @RequestMapping(value = "/all",
      method = RequestMethod.GET)
  public Collection<User> getAllFriends(@ApiParam(value = "User ID", required = true)
                                        @PathVariable("user") int id, @RequestHeader("authorization") String token) {
    accessManager.checkUser(id, token);
    return service.getAllFriends(id);
  }
   */

  @Autowired
  private GroupService groupService;

  @ApiOperation(value = "Return every group in the database")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of groups"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
  })
  @RequestMapping(value= "/{userid}/groups", method = RequestMethod.GET)
  public Collection<FbGroup> getAllGroups(@ApiParam(value = "User ID", required = true)
                                            @PathVariable("userid") int userid,
                                          @RequestHeader("authorization") String token) {
    accessManager.checkUser(userid, token);
    return groupService.getAllGroup(userid);
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
                              @PathVariable("groupid") int groupid,
                              @PathVariable("userid") int userid) {
    return groupService.getGroupById(userid, groupid);
  }


  @ApiOperation(value = "Removes a group given ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully removed group"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/groupid/{groupid}",  method = RequestMethod.DELETE)
  public void removeGroupById(@ApiParam(value = "group ID", required = true)
                              @PathVariable("groupid") int groupid,
                              @PathVariable("userid") int userid) {
    groupService.removeGroupById(userid, groupid);
  }

  @ApiOperation(value = "Create a new group WHERE: id is not required")
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
                                            @PathVariable("name") String name,
                                            @PathVariable("userid") int userid) {
    try {
      return groupService.getGroupByName(userid, name);
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
                                             @PathVariable("admin") String admin,
                                             @PathVariable("userid") int userid) {
    Collection<FbGroup> result = groupService.getGroupByAdmin(userid, admin);
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
                                                 @PathVariable("memberid") int memberid,
                                                 @ApiParam(value = "User ID calling method", required = true)
                                                 @PathVariable("userid") int userid) {
    Collection<FbGroup> result = groupService.getAllGroupsForUser(userid, memberid);
    if (result.size() == 0) {
      throw new ResourceNotFoundException(Integer.toString(memberid));
    }
    return result;
  }
  
  @ApiOperation(value = "Sends a joining request to a group")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully sent a joining request to a group"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/join/groupid/{groupid}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void sendJoinRequest(@ApiParam(value = "group ID", required = true)
                               @PathVariable("groupid") int groupid,
                               @ApiParam(value = "Membership ID", required = true)
                               @PathVariable("memberid") int userid) {
    groupService.sendJoinRequest(groupid, userid);
  }

  @ApiOperation(value = "Adds member to group")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully added member to group"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/groupid/{groupid}/memberid/{memberid}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void addMemberToGroup(@ApiParam(value = "group ID", required = true)
                               @PathVariable("groupid") int groupid,
                               @ApiParam(value = "Membership ID", required = true)
                               @PathVariable("memberid") int memberid,
                               @ApiParam(value = "User ID calling method", required = true)
                               @PathVariable("userid") int userid) {
    groupService.addMemberToGroup(userid, groupid, memberid);

  }

  @ApiOperation(value = "Removes member from a group")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully removed member from group"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/groupid/{groupid}", method = RequestMethod.DELETE)
  public void removeMemberFromGroup(@ApiParam(value = "group ID", required = true)
                                    @PathVariable("groupid") int groupid,
                                    @ApiParam(value = "Membership ID", required = true)
                                    @PathVariable("memberid") int memberid,
                                    @ApiParam(value = "User ID calling method", required = true)
                                    @PathVariable("userid") int userid) {
    groupService.removeMemberFromGroup(userid, groupid, memberid);
  }
}