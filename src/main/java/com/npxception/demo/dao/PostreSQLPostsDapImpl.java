package com.npxception.demo.dao;

import com.npxception.demo.entity.Posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by RachelDi on 18/06/2017.
 */
@Repository("PostgrePosts")
public class PostreSQLPostsDapImpl implements PostsDao{

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Collection<Posts> getAllPosts() {
    return null;
  }

  @Override
  public Posts getPostsById(int id) {
    return null;
  }

  @Override
  public void removePostsById(int id) {

  }

  @Override
  public void updatePosts(Posts assignment) {

  }

  @Override
  public void insertPostsToDb(Posts assignment) {

  }

  @Override
  public Collection<Posts> getPostsByContent(String content) {
    return null;
  }

  @Override
  public Collection<Posts> getPostsByAuthor(int authorID) {
    return null;
  }

  @Override
  public Collection<Posts> getPostsByLikes(int likes) {
    return null;
  }

  @Override
  public Collection<Posts> getPostsByLikedBy(int likedBy) {
    return null;
  }

  @Override
  public Collection<Posts> getPostsByTime(int time) {
    return null;
  }
}
