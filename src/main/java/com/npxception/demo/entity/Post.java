package com.npxception.demo.entity;

/**
 * Created by bryan on 6/2/2017.
 */
public class Post {
  private int id;
  private int author;
  private String content;
  private int likes;
  private int likedBy;
  private int time;
  private  int visibility;

  public Post(int id, int author, String content, int likes, int likedBy, int time, int visibility) {
    this.id = id;
    this.author = author;
    this.content = content;
    this.likes = likes;
    this.likedBy = likedBy;
    this.time = time;
    this.visibility = visibility;
  }

  public Post() {}

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

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public int getVisibility() { return visibility; }

  public void setVisibility(int visibility) {  this.visibility = visibility; }
}
