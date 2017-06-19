//package com.npxception.demo.controller;
//
//import com.npxception.demo.entity.Posts;
//import com.npxception.demo.service.PostsService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Collection;
//
///**
// * Created by bryan on 6/2/2017.
// */
//
//@RestController
//@RequestMapping("/posts")
//
//public class PostsController {
//
//  @Autowired
//  private PostsService assignmentService;
//
//  @RequestMapping(method = RequestMethod.GET)
//  public Collection<Posts> getAllAssingment() {
//    return assignmentService.getAllPosts();
//  }
//
//  @RequestMapping(value = "/id = {id}", method = RequestMethod.GET)
//  public Posts getAssignmentById(@PathVariable("id") int id) {
//    return assignmentService.getPostsById(id);
//  }
//
//  @RequestMapping(value = "/id = {id}", params = "id", method = RequestMethod.DELETE)
//  public void deleteAssignmentById(@PathVariable("id") int id) {
//    assignmentService.removePostsById(id);
//  }
//
//  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//  public void insertAssignment(@RequestBody Posts assignment) {
//    assignmentService.insertPosts(assignment);
//  }
//
//
//  @RequestMapping(value = "/content = {content}", method = RequestMethod.GET)
//  public Collection<Posts> getAssignmentByContent(@PathVariable("content") String content) {
//    return assignmentService.getPostsByContent(content);
//  }
//
//}
