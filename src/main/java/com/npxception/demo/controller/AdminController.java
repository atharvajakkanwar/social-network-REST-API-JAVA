package com.npxception.demo.controller;

import com.npxception.demo.entity.FbGroup;
import com.npxception.demo.entity.Post;
import com.npxception.demo.entity.User;
import com.npxception.demo.exceptions.ResourceNotFoundException;
import com.npxception.demo.service.GroupService;
import com.npxception.demo.service.PostService;
import com.npxception.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Created by RachelDi on 27/07/2017.
 */
@RestController
@RequestMapping("/")
@Api(description = "Admin Controller")
public class AdminController {
  @Autowired
  private PostService postService;

  @Autowired
  private UserService userService;

  @Autowired
  private GroupService groupService;

  @ApiOperation(value = "Gets all User")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
  })
  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public Collection<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @ApiOperation(value = "Return every post in the database")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of post"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
  })
  @RequestMapping(value = "/posts", method = RequestMethod.GET)
  public Collection<Post> getAllPosts() {
    return postService.getAllPosts();
  }

  @ApiOperation(value = "Return every post in group given group name")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of posts"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/groupname/{name}", method = RequestMethod.GET)
  public Collection<Post> getPostsFromGroup(@PathVariable("user") int id,
                                            @PathVariable("name") String name,
                                            @RequestHeader("authorization") String token) {
    Collection<Post> result = postService.getPostsFromGroup(name);
    if (result.size() == 0) {
      throw new ResourceNotFoundException(name);
    }
    return result;
  }

  @ApiOperation(value = "Return every post in group given group name")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of posts"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })

  @RequestMapping(value = "/group/{groupid}", method = RequestMethod.GET)
  public Collection<Post> getPostsFromGroup(@ApiParam(value = "group ID", required = true)
                                            @PathVariable("user") int id,
                                            @PathVariable("groupid") int groupid,
                                            @RequestHeader("authorization") String token) {
    Collection<Post> result = postService.getPostsFromGroup(groupid);
    if (result.size() == 0) {
      throw new ResourceNotFoundException(Integer.toString(groupid));
    }
    return result;
  }

  @ApiOperation(value = "Return every group in the database")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of groups"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
  })
  @RequestMapping(value = "/groups", method = RequestMethod.GET)
  public Collection<FbGroup> getAllGroups() {
    return groupService.getAllGroup();
  }

}
