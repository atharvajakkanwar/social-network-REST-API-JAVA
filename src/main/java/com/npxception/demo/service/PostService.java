package com.npxception.demo.service;

import com.npxception.demo.dao.PostDao;
import com.npxception.demo.entity.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;


/**
 * Created by Atharva on 6/18/2017.
 */

@Service
public class PostService {

  @Autowired
  @Qualifier("PostgresPostRepo")
  private PostDao postDao;

  public Collection<Post> getAllPosts() {
    return this.postDao.getAllPosts();
  }

  public Collection<Post> getPostsByUser(int userId) { return this.postDao.getPostsByUser(userId); }

  public Collection<Post> getPostsByUser(String firstName) { return this.postDao.getPostsByUser(firstName); }

//  public Collection<Post> getPostsByUser(String firstName, int time) { return this.postDao.getPostsByUser(firstName, time); }

  public Collection<Post> getPostsFromGroup(int groupId) {return this.postDao.getPostsFromGroup(groupId); }

  public Collection<Post> getPostsFromGroup(String name) {return this.postDao.getPostsFromGroup(name); }

  public Collection<Post> getPostsByUserFromGroup(int userId, int groupId) {return this.postDao.getPostsByUserFromGroup(userId, groupId); }

  public Post getPostsById(int id) {
    return this.postDao.getPostsById(id);
  }

  public void removePostsById(int id) {
    this.postDao.removePostsById(id);
  }

  public void updatePosts (Post assignment) {
    this.postDao.updatePosts(assignment);
  }

  public void createPost(Post assignment) {


    this.postDao.createPost(assignment);
  }

  public Collection<Post> getPostsByContent(String content) {
    return this.postDao.getPostsByContent(content);
  }


}