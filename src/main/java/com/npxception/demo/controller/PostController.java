package com.npxception.demo.controller;

import com.npxception.demo.entity.Post;
import com.npxception.demo.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


/**
 * Created by bryan on 6/2/2017.
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

  @RequestMapping(value = "/user={user}", method = RequestMethod.GET)
  public Collection<Post> getAllPostsByUser(@PathVariable int user) {
    return postService.getAllPostsByUser(user);
  }

  @RequestMapping(value = "/id = {id}", params = "id", method = RequestMethod.DELETE)
  public void deletePostsById(@PathVariable("id") int id) {
    postService.removePostsById(id);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void insertPost(@RequestBody Post assignment) {
    postService.insertPosts(assignment);
  }


  @RequestMapping(value = "/content = {content}", method = RequestMethod.GET)
  public Collection<Post> getPostsByContent(@PathVariable("content") String content) {
    return postService.getPostsByContent(content);
  }
  //test commit

}
