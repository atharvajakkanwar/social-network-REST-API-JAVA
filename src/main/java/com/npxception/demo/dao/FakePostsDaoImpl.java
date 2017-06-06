package com.npxception.demo.dao;

import com.npxception.demo.entity.Posts;

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
public class FakePostsDaoImpl implements PostsDao {

  private static Map<Integer, Posts> assignments;

  static {

    assignments = new HashMap<Integer, Posts>() {

      {
        put(1, new Posts(1, 1, "Hello World", 100, 1, "12:00"));
        put(2, new Posts(2, 2, "200", 123, 21, "12:09"));
        put(3, new Posts(3, 3, "再見", 3431, 432, "21:34"));
        put(4, new Posts(4, 4, "掰掰", 65456789, 7654, "12:53"));
        put(5, new Posts(5, 5, "200", 0, 0, "6:19"));

      }
    };
  }

  @Override
  public Collection<Posts> getAllPosts() {
    return this.assignments.values();
  }



  @Override
  public Posts getPostsById(int id) {
    return this.assignments.get(id);
  }

  @Override
  public void removePostsById(int id) {
    this.assignments.remove(id);

  }

  @Override
  public void updatePosts(Posts assignment) {
//    Posts a = assignments.get(assignment.getId());
//    a.setCourse(assignment.getCourse());
//    a.setContent(assignment.getContent());
//    assignments.put(assignment.getId(), assignment);

  }

  @Override
  public void insertPostsToDb(Posts assignment) {
    this.assignments.put(assignment.getId(), assignment);
  }



  @Override
  public Collection<Posts> getPostsByContent(String content) {
    HashMap<Integer, Posts> assignmentByContent = new HashMap<>();
    for (Map.Entry<Integer, Posts> entry : assignments.entrySet()) {
      if (entry.getValue().getContent().equals(content)) {
        assignmentByContent.put(entry.getKey(), entry.getValue());
      }
    }
    return assignmentByContent.values();
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
