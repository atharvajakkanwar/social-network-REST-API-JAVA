package com.npxception.demo.dao;

import com.npxception.demo.entity.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Atharva on 6/18/2017.
 */

@Repository("PostgresPostRepo")
public class PostgreSQLPostDaoImpl implements PostDao {
  private PostgreSQLUserDaoImpl postgreSQLUserDao;
  private PostgreSQLPostDaoImpl postgreSQLPostDao;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  PostgreSQLPostDaoImpl(PostgreSQLUserDaoImpl postgreSQLUserDao, PostgreSQLPostDaoImpl postgreSQLPostDao) {
    this.postgreSQLUserDao = postgreSQLUserDao;
    this.postgreSQLPostDao = postgreSQLPostDao;
  }

  @Override
  public Collection<Post> getAllPosts() {
    final String sql = "SELECT * FROM posts";
    List<Post> posts = jdbcTemplate.query(sql, new PostRowMapper());
    return posts;
  }

  @Override
  public Collection<Post> getPostsByUser(int userId) {
    final String sql =
        "SELECT * " +
            "FROM posts " +
            "WHERE author = ? " +
            "ORDER BY time";
    List<Post> posts = jdbcTemplate.query(sql, new PostRowMapper(), userId);
    return posts;
  }

  @Override
  public Collection<Post> getPostsByUser(String firstName) {
    final String sql = "SELECT * FROM posts WHERE author IN (SELECT userid FROM users WHERE firstname = ?) ORDER BY time";
    List<Post> posts = jdbcTemplate.query(sql, new PostRowMapper(), firstName);
    return posts;
  }

  @Override
  public Collection<Post> getPostsByUserFromGroup(int userId, int groupId) {
    final String sql = "SELECT * FROM posts WHERE author = ? AND visibility = ? ORDER BY time";
    List<Post> posts = jdbcTemplate.query(sql, new PostRowMapper(), userId, groupId);
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
    final String sql = "SELECT * FROM posts WHERE visibility IN (SELECT groupid FROM groups WHERE name = ?) ORDER BY time";
    List<Post> posts = jdbcTemplate.query(sql, new PostRowMapper(), name);
    return posts;
  }

  @Override
  public void removePostsById(int id) {

  }

  @Override
  public void updatePosts(Post assignment) {

  }

  @Override
  public void createPost(Post post) {
    //INSERT INTO table_name (column1, column2, column3,...)
    //VALUES (value1, value2, value3,...)
    final String sql = "INSERT INTO posts (id, author, content, likes, time, visibility) VALUES (?, ?, ?, ?, ?, ?)";

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
    return null;
  }

  @Override
  public Collection<Post> getPostsByAuthor(String author) {
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