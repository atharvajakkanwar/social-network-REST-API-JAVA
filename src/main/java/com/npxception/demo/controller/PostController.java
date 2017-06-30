package com.npxception.demo.controller;

import com.npxception.demo.entity.Post;
import com.npxception.demo.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


/**
 * Created by Atharva on 6/18/2017.
 */

@RestController
@RequestMapping("/posts")

public class PostController {

  @Autowired
  private PostService postService;

  @RequestMapping(method = RequestMethod.GET)
  public Collection<Post> getAllPosts() {
    return postService.getAllPosts();
  }

  @RequestMapping(value = "/id={id}", method = RequestMethod.GET)
  public Post getPostsById(@PathVariable int id) {
    return postService.getPostsById(id);
  }

  @RequestMapping(value = "/group={groupid}", method = RequestMethod.GET)
  public Collection<Post> getPostsFromGroup(@PathVariable int groupid) {
    return postService.getPostsFromGroup(groupid);
  }

  @RequestMapping(value = "/groupname={name}", method = RequestMethod.GET)
  public Collection<Post> getPostsFromGroup(@PathVariable String name) {
    return postService.getPostsFromGroup(name);
  }

  @RequestMapping(value = "/author={author}", method = RequestMethod.GET)
  public Collection<Post> getPostsByUser(@PathVariable String author) {
    return postService.getPostsByUser(author);
  }



//  @RequestMapping(value = "/firstname={firstname}", method = RequestMethod.GET)
//  public Collection<Post> getPostsByUser(@PathVariable String firstName, @PathVariable int time) {
//    return postService.getPostsByUser(firstName, time);
//  }

  @RequestMapping(value = "/id={id}", params = "id", method = RequestMethod.DELETE)
  public void removePostById(@PathVariable("id") int id) {
    postService.removePostsById(id);
  }
  //test commit

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void createPost(@RequestBody Post assignment) {
    postService.createPost(assignment);
  }


}