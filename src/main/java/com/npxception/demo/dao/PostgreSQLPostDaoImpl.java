package com.npxception.demo.dao;

import com.npxception.demo.entity.FbGroup;
import com.npxception.demo.entity.Post;
import com.npxception.demo.entity.User;
import com.npxception.demo.helperMethods.UserRowMapper;
import com.npxception.demo.service.FriendsService;
import com.npxception.demo.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Atharva on 6/18/2017.
 */

@Repository("PostgresPostRepo")
public class PostgreSQLPostDaoImpl implements PostDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private FriendsService friendsService;

  @Autowired
  private GroupService groupService;

  @Override
  public Collection<Post> getAllPosts() {
    final String sql = "SELECT * FROM posts";
    List<Post> posts = jdbcTemplate.query(sql, new PostRowMapper());
    return posts;
  }

  @Override
  public Collection<Post> getPostsByUser(int userId) {
    final String sql1 = "SELECT * FROM users WHERE userid = ? ";
    User user = jdbcTemplate.queryForObject(sql1, new UserRowMapper(), userId);
    String author = user.getFirstName() + " " + user.getLastName();
    final String sql2 = "SELECT * FROM posts WHERE author = ? ORDER BY time";
    List<Post> posts = jdbcTemplate.query(sql2, new PostRowMapper(), author);
    return posts;
  }

//  @Override
//  public Collection<Post> getPostsByUser(String name) {
//    final String sql = "SELECT p.* FROM posts p, users u WHERE author " +
//        " ORDER BY time";
//    List<Post> posts = jdbcTemplate.query(sql, new PostRowMapper(), firstName);
//    return posts;
//  }

  @Override
  public Collection<Post> getPostsByUserFromGroup(int userId, int groupId) {
    final String sql1 = "SELECT * FROM users WHERE userid = ? ";
    User user = jdbcTemplate.queryForObject(sql1, new UserRowMapper(), userId);
    String author = user.getFirstName() + " " + user.getLastName();
    final String sql2 = "SELECT * FROM posts WHERE author = ? " +
        "AND visibility = ? ORDER BY time";
    List<Post> posts = jdbcTemplate.query(sql2, new PostRowMapper(), new Object[]{author, groupId});
    return posts;
  }

//  @Override
//  public Collection<Post> getPostsByUser(String firstName, int time) {
//    return null;
//  }

  @Override
  public Post getPostsById(int id) {
    final String sql = "SELECT * FROM posts WHERE id = ?";
    Post post = jdbcTemplate.queryForObject(sql, new PostRowMapper(), id);
    return post;
  }

  @Override
  public Collection<Post> getPostsFromGroup(int groupId) {
    final String sql = "SELECT * FROM posts WHERE visibility = ?";
    List<Post> posts = jdbcTemplate.query(sql, new PostRowMapper(), groupId);
    return posts;
  }

  @Override
  public Collection<Post> getPostsFromGroup(String name) {
    int groupid = jdbcTemplate.queryForObject("SELECT groupid FROM groups WHERE groupname = ?"
        , new Object[]{name}, Integer.class);
    final String sql = "SELECT * FROM posts WHERE visibility = ? ORDER BY time";
    List<Post> posts = jdbcTemplate.query(sql, new PostRowMapper(), groupid);
    return posts;
  }

  @Override
  public void removePostsById(int id) {
    final String sql = "DELETE FROM posts WHERE id = ?";
    jdbcTemplate.update(sql, id);
  }

  @Override
  public void updatePosts(Post assignment) {

  }

  @Override
  public void createPost(Post post) {
    //INSERT INTO table_name (column1, column2, column3,...)
    //VALUES (value1, value2, value3,...)
    final String sql = "INSERT INTO posts (id, author, content, likes, time, visibility) " +
        "VALUES (?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, new Object[]{
        post.getId(),
        post.getAuthor(),
        post.getContent(),
        post.getLikes(),
        post.getTime(),
        post.getVisibility()
    });
  }

  @Override
  public Collection<Post> getPostsByContent(String content) {
    final String sql = "SELECT * FROM users WHERE content = ?";
    Collection<Post> posts = jdbcTemplate.query(sql, new PostRowMapper(), content);
    return posts;
  }

  @Override
  public Collection<Post> getPostsByAuthor(String author) {
    final String sql = "SELECT * FROM users WHERE author = ?";
    Collection<Post> posts = jdbcTemplate.query(sql, new PostRowMapper(), author);
    return posts;
  }

  @Override
  public Collection<Post> getPostsByLikes(int likes) {
    final String sql = "SELECT * FROM users WHERE likes = ?";
    Collection<Post> posts = jdbcTemplate.query(sql, new PostRowMapper(), likes);
    return posts;
  }

  @Override
  public Collection<Post> getPostsByLikedBy(int likedBy) {
    return null;
  }

  @Override
  public Collection<Post> getPostsByTime(int time) {
    final String sql = "SELECT * FROM users WHERE time = ?";
    Collection<Post> posts = jdbcTemplate.query(sql, new PostRowMapper(), time);
    return posts;
  }

  @Override
  public Collection<Post> getPostUserMainPage(int userid) {
    Collection<Post> result = new ArrayList<>();
    Collection<User> friends = friendsService.getAllFriends(userid);
    for (User user : friends) {
      Collection<Post> posts = getPostsByUser(user.getId());
      for (Post post : posts) {
        result.add(post);
      }
    }
    Collection<FbGroup> groups = groupService.getAllGroupsForUser(userid);
    for (FbGroup group : groups) {
      Collection<Post> posts = getPostsFromGroup(group.getGroupID());
      for (Post post : posts) {
        result.add(post);
      }
    }
    Collection<Post> myPosts = getPostsByUser(userid);
    for (Post post : myPosts) {
      result.add(post);
    }
    return result;
  }


  private static class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {
      Post post = new Post();
      post.setId(resultSet.getInt("id"));
      post.setAuthor(resultSet.getString("author"));
      post.setContent(resultSet.getString("content"));
      post.setLikes(resultSet.getInt("likes"));
      post.setTime(resultSet.getInt("time"));
      post.setVisibility(resultSet.getInt("visibility"));
      return post;
    }
  }
}
