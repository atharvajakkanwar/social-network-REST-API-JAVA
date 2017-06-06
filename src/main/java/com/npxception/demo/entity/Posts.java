package com.npxception.demo.entity;

/**
 * Created by bryan on 6/2/2017.
 */
public class Posts {
  private int id;
  private int author;
  private String content;
  private int likes;
  private int likedBy;
  private String time;

  public Posts(int id, int author, String content, int likes, int likedBy, String time) {
    this.id = id;
    this.author = author;
    this.content = content;
    this.likes = likes;
    this.likedBy = likedBy;
    this.time = time;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAuthor() {
    return author;
  }

  public void setAuthor(int author) {
    this.author = author;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  public int getLikedBy() {
    return likedBy;
  }

  public void setLikedBy(int likedBy) {
    this.likedBy = likedBy;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }
}
