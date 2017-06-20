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
 * Created by Robert on 6/13/2017.
 */
@Repository("PostgresPostRepo")
public class PostgreSQLPostDaoImpl implements PostDao {


  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Collection<Post> getAllPosts() {
    final String sql = "SELECT * FROM posts";
    List<Post> posts = jdbcTemplate.query(sql, new PostRowMapper());
    return posts;
  }

  @Override
  public Collection<Post> getAllPostsByUser(int user) {
    final String sql = "SELECT * FROM posts WHERE author = ? ORDER BY time";
    List<Post> posts = jdbcTemplate.query(sql, new PostRowMapper(), user);
    return posts;
  }

  @Override
  public Post getPostsById(int id) {
    final String sql = "SELECT * FROM posts WHERE id = ?";
    Post post = jdbcTemplate.queryForObject(sql, new PostRowMapper(), id);
    return post;
  }

  @Override
  public void removePostsById(int id) {

  }

  @Override
  public void updatePosts(Post assignment) {

  }

  @Override
  public void insertPostsToDb(Post post) {
    //INSERT INTO table_name (column1, column2, column3,...)
    //VALUES (value1, value2, value3,...)
    final String sql = "INSERT INTO posts (id, author, content, likes, likedBy, time) VALUES (?, ?, ?, ?, ?, ?)";

    jdbcTemplate.update(sql, new Object[]{
        post.getId(),
        post.getAuthor(),
        post.getContent(),
        post.getLikes(),
        post.getLikedBy(),
        post.getTime(),
    });
  }

  @Override
  public Collection<Post> getPostsByContent(String content) {
    return null;
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




  private static class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {
      Post post = new Post();
      post.setId(resultSet.getInt("id"));
      post.setAuthor(resultSet.getInt("author"));
      post.setContent(resultSet.getString("content"));
      post.setLikes(resultSet.getInt("likes"));
      post.setLikedBy(resultSet.getInt("likedBy"));
      post.setTime(resultSet.getInt("time"));
      return post;
    }
  }
}
