package com.npxception.demo.dao;

import com.npxception.demo.entity.Post;

import java.util.Collection;

public interface PostDao {
  Collection<Post> getAllPosts();

  Post getPostsById(int id);

  void removePostsById(int id);

  void updatePosts(Post assignment);

  void insertPostsToDb(Post assignment);

  Collection<Post> getPostsByContent(String content);

  Collection<Post> getPostsByAuthor(int authorID);

  Collection<Post> getPostsByLikes(int likes);

  Collection<Post> getPostsByLikedBy(int likedBy);

  Collection<Post> getPostsByTime(int time);

  Collection<Post> getAllPostsByUser(int user);





}
