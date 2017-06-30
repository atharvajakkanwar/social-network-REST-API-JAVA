package com.npxception.demo.entity;

/**
 * Created by bryan on 6/2/2017.
 */
public class Post {
  private int id;
  private String author;
  private String content;
  private int likes;
  private int time;
  private  int visibility;

  public Post(int id, String author, String content, int likes, int time, int visibility) {

    this.id = id;
    this.author = author;
    this.content = content;
    this.likes = likes;

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

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
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


  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public int getVisibility() { return visibility; }

  public void setVisibility(int visibility) {  this.visibility = visibility; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Post post = (Post) o;

    if (id != post.id) return false;
    if (author != post.author) return false;
    if (likes != post.likes) return false;
    if (time != post.time) return false;
    if (visibility != post.visibility) return false;
    return content != null ? content.equals(post.content) : post.content == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + author.hashCode();
    result = 31 * result + (content != null ? content.hashCode() : 0);
    result = 31 * result + likes;
    result = 31 * result + time;
    result = 31 * result + visibility;
    return result;
  }

  @Override
  public String toString() {
    return "Post{" +
        "id=" + id +
        ", author=" + author +
        ", content='" + content + '\'' +
        ", likes=" + likes +
        ", time=" + time +
        ", visibility=" + visibility +
        '}';
  }
}

