package com.npxception.demo.dao;

import com.npxception.demo.entity.Post;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bryan on 6/2/2017.
 */

@Repository
@Qualifier("fakeData")
public class FakePostDaoImpl implements PostDao {

  private static Map<Integer, Post> assignments;

  static {

    assignments = new HashMap<Integer, Post>() {

      {
        put(1, new Post(1, 1, "Hello World", 100, 1, 12));
        put(2, new Post(2, 2, "200", 123, 21, 12));
        put(3, new Post(3, 3, "再見", 3431, 432, 12));
        put(4, new Post(4, 4, "掰掰", 65456789, 7654, 10));
        put(5, new Post(5, 5, "200", 0, 0, 6));

      }
    };
  }

  @Override
  public Collection<Post> getAllPosts() {
    return this.assignments.values();
  }



  @Override
  public Post getPostsById(int id) {
    return this.assignments.get(id);
  }

  @Override
  public void removePostsById(int id) {
    this.assignments.remove(id);

  }

  @Override
  public void updatePosts(Post assignment) {
//    Post a = assignments.get(assignment.getId());
//    a.setCourse(assignment.getCourse());
//    a.setContent(assignment.getContent());
//    assignments.put(assignment.getId(), assignment);

  }

  @Override
  public void insertPostsToDb(Post assignment) {
    this.assignments.put(assignment.getId(), assignment);
  }



  @Override
  public Collection<Post> getPostsByContent(String content) {
    HashMap<Integer, Post> assignmentByContent = new HashMap<>();
    for (Map.Entry<Integer, Post> entry : assignments.entrySet()) {
      if (entry.getValue().getContent().equals(content)) {
        assignmentByContent.put(entry.getKey(), entry.getValue());
      }
    }
    return assignmentByContent.values();
  }

  @Override
  public Collection<Post> getPostsByAuthor(int authorID) {
    return null;
  }

  @Override
  public Collection<Post> getPostsByLikes(int likes) {
    return null;
  }

  @Override
  public Collection<Post> getPostsByLikedBy(int likedBy) {
    return null;
  }

  @Override
  public Collection<Post> getPostsByTime(int time) {
    return null;
  }

  @Override
  public Collection<Post> getAllPostsByUser(int user) {
    return null;
  }
}
