package com.npxception.demo.controller;

import com.npxception.demo.entity.Post;
import com.npxception.demo.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * Created by Atharva on 6/18/2017.
 */

@RestController
@RequestMapping("/posts")
@Api(description = "Post Controller")

public class PostController {

  @Autowired
  private PostService postService;

  @ApiOperation(value = "Return every post in the database")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of post"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(method = RequestMethod.GET)
  public Collection<Post> getAllPosts() {
    return postService.getAllPosts();
  }

  @ApiOperation(value = "Return post given ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved post"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/id={id}", method = RequestMethod.GET)
  public Post getPostsById(@ApiParam( value = "post ID", required = true)
                             @PathVariable int id) {
    return postService.getPostsById(id);
  }

  @ApiOperation(value = "Return every post in group given group name")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of posts"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/group={groupid}", method = RequestMethod.GET)
  public Collection<Post> getPostsFromGroup(@ApiParam(value = "group ID", required = true)
      @PathVariable int groupid) {
    return postService.getPostsFromGroup(groupid);
  }

  @ApiOperation(value = "Return every post in group given group name")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of posts"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/groupname={name}", method = RequestMethod.GET)
  public Collection<Post> getPostsFromGroup(@PathVariable String name) {
    return postService.getPostsFromGroup(name);
  }

  @ApiOperation(value = "Return every post written by author given author")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of groups"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/author={author}", method = RequestMethod.GET)
  public Collection<Post> getPostsByUser(@ApiParam(value = "Author", required = true)
                                           @PathVariable String author) {
    return postService.getPostsByUser(author);
  }



//  @RequestMapping(value = "/firstname={firstname}", method = RequestMethod.GET)
//  public Collection<Post> getPostsByUser(@PathVariable String firstName, @PathVariable int time) {
//    return postService.getPostsByUser(firstName, time);
//  }

  @ApiOperation(value = "Removes post from database given ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully removed post from database"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/id={id}", params = "id", method = RequestMethod.DELETE)
  public void removePostById(@PathVariable("id") int id) {
    postService.removePostsById(id);
  }
  //test commit from post branch

  @ApiOperation(value = "Create post")
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