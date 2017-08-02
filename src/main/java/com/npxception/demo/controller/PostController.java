package com.npxception.demo.controller;

import com.npxception.demo.entity.DBPost;
import com.npxception.demo.entity.Post;
import com.npxception.demo.exceptions.ResourceNotFoundException;
import com.npxception.demo.helperMethods.AccessManager;
import com.npxception.demo.service.PostService;

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
 * Represents a controller for the Posts service.
 * Created by Atharva on 6/18/2017.
 */

@RestController
@RequestMapping("/{user}/post")
@Api(description = "Post Controller")

public class PostController {

  @Autowired
  private PostService postService;

  @Autowired
  AccessManager accessManager = new AccessManager();

  @ApiOperation(value = "Return every post in the database")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of post"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
  })
  @RequestMapping(value = "/posts", method = RequestMethod.GET)
  public Collection<DBPost> getAllPosts() {
    return postService.getAllPosts();
  }


  @ApiOperation(value = "Return post given ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved post"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
  })
  @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
  public DBPost getPostsById(@ApiParam(value = "post ID", required = true)
                           @PathVariable("user") int id1,
                             @PathVariable("id") int id,
                             @RequestHeader("authorization") String token) {
    try {
      accessManager.checkUser(id1, token);
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
  @RequestMapping(value = "/group/{groupid}/member/{memberid}", method = RequestMethod.GET)
  public Collection<DBPost> getPostsByUserFromGroup(@ApiParam(value = "group ID", required = true)
                                                  @PathVariable("user") int id1,
                                                  @PathVariable("memberid") int id2,
                                                  @PathVariable("groupid") int groupid,
                                                  @RequestHeader("authorization") String token) {
    accessManager.checkUser(id1, token);
    Collection<DBPost> result = postService.getPostsByUserFromGroup(id1, id2, groupid);
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
  @RequestMapping(value = "/groupname/{name}/member/{memberid}", method = RequestMethod.GET)
  public Collection<DBPost> getPostsByUserFromGroupName(@PathVariable("user") int id,
                                                      @PathVariable("memberid") int id2,
                                                      @PathVariable("name") String name,
                                                      @RequestHeader("authorization") String token) {
    accessManager.checkUser(id, token);
    Collection<DBPost> result = postService.getPostsByUserFromGroupName(id, id2, name);
    if (result.size() == 0) {
      throw new ResourceNotFoundException(name);
    }
    return result;
  }

  @RequestMapping(value = "/update/postid/{postid}", method = RequestMethod.PUT,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updatePost(@PathVariable("user") int id,
                         @PathVariable("postid") int postid,
                         @RequestHeader("authorization") String token,
                         @RequestBody Post post) {
    accessManager.checkUser(id, token);
    postService.updatePosts(id, post);
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
  @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
  public void removePostById(@PathVariable("user") int id1,
                             @PathVariable("id") int id,
                             @RequestHeader("authorization") String token) {
    accessManager.checkUser(id1, token);
    postService.removePostsById(id1, id);
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
                         @RequestHeader("authorization") String token,
                         @RequestBody Post assignment) {
    postService.createPost(assignment);
  }

  @RequestMapping(value = "/wall", method = RequestMethod.GET)
  public Collection<DBPost> getPostUserMainPage(@PathVariable("user") int id,
                                              @RequestHeader("authorization") String token) {
    accessManager.checkUser(id, token);
    return postService.getPostUserMainPage(id);
  }
}