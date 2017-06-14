package com.npxception.demo.service;

import com.npxception.demo.dao.PostDao;
import com.npxception.demo.entity.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;


/**
 * Created by bryan on 6/2/2017.
 */

@Service
public class PostService {

  @Autowired
  @Qualifier("Postgre1")
  private PostDao postDao;

  public Collection<Post> getAllPosts() {
    return this.postDao.getAllPosts();
  }

  public Collection<Post> getAllPostsByUser(int user) {
    return this.postDao.getAllPostsByUser(user);
  }

  public Post getPostsById(int id) {
    return this.postDao.getPostsById(id);
  }

  public void removePostsById(int id) {
    this.postDao.removePostsById(id);
  }

  public void updatePosts (Post assignment) {
    this.postDao.updatePosts(assignment);
  }

  public void insertPosts(Post assignment) {
    this.postDao.insertPostsToDb(assignment);
  }

  public Collection<Post> getPostsByContent(String content) {
    return this.postDao.getPostsByContent(content);
  }


}
