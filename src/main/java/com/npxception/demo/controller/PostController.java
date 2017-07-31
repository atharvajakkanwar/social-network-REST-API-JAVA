package com.npxception.demo.controller;

import com.npxception.demo.config.RedirectLoginSuccessHandler;
import com.npxception.demo.entity.Post;
import com.npxception.demo.exceptions.ResourceNotFoundException;
import com.npxception.demo.helperMethods.UserInformation;
import com.npxception.demo.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Represents a controller for the Posts service.
 * Created by Atharva on 6/18/2017.
 */

@RestController
@RequestMapping("/post")
@Api(description = "Post Controller")

public class PostController {

  @Autowired
  private PostService postService;

  @ApiOperation(value = "Return post given ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved post"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
  })
  @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
  public Post getPostsById(@ApiParam(value = "post ID", required = true)
                           @PathVariable int id) {
    try {
      return postService.getPostsById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException(Integer.toString(id));
    }
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
                                            @PathVariable int groupid) {
    Collection<Post> result = postService.getPostsFromGroup(groupid);
    if (result.size() == 0) {
      throw new ResourceNotFoundException(Integer.toString(groupid));
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
  @RequestMapping(value = "/groupname/{name}", method = RequestMethod.GET)
  public Collection<Post> getPostsFromGroup(@PathVariable String name) {
    Collection<Post> result = postService.getPostsFromGroup(name);
    if (result.size() == 0) {
      throw new ResourceNotFoundException(name);
    }
    return result;
  }

//
//  @ApiOperation(value = "Return every post written by author given author")
//  @ApiResponses(value = {
//      @ApiResponse(code = 200, message = "Successfully retrieved list of groups"),
//      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
//  })
//  @RequestMapping(value = "/author/{author}", method = RequestMethod.GET)
//  public Collection<Post> getPostsByUser(@ApiParam(value = "Author", required = true)
//                                         @PathVariable String author) {
//    Collection<Post> result = postService.getPostsByUser(author);
//    if (result.size() == 0) {
//      throw new ResourceNotFoundException(author);
//    }
//    return result;
//  }

  @ApiOperation(value = "Removes post from database given ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully removed post from database"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @RequestMapping(value = "/d/id/{id}", method = RequestMethod.DELETE)
    public void removePostById(@PathVariable("id") int id) {
      postService.removePostsById(id);
    }

    @ApiOperation(value = "Create post WHERE: id is not required")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created post"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createPost(@ApiParam(value = "Post", required = true)
    @RequestBody Post assignment) {
      postService.createPost(assignment);
    }


}