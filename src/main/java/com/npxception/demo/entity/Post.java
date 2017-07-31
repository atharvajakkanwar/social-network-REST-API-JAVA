package com.npxception.demo.entity;

/**
 * Created by bryan on 6/2/2017.
 */
public class Post {
  private int id;
  private String authorFirst;
  private String authorLast;
  private String content;
  private int likes;
  private int time;
  private  int visibility;

  public Post(String authorFirst, String authorLast, String content, int likes, int time, int visibility) {
    this.authorFirst = authorFirst;
    this.authorLast = authorLast;
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

  public String getAuthorFirstName() {
    return this.authorFirst;
  }

  public String getAuthorLastName() {
    return this.authorLast;
  }

  public void setAuthorFirstName(String authorFirst) {
    this.authorFirst = authorFirst;
  }

  public void setAuthorLastName(String authorLast) {
    this.authorLast = authorLast;
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
    if (authorFirst != post.authorFirst) return false;
    if (authorLast != post.authorLast) return false;

    if (likes != post.likes) return false;
    if (time != post.time) return false;
    if (visibility != post.visibility) return false;
    return content != null ? content.equals(post.content) : post.content == null;
  }

  @Override
  public int hashCode() {
    int result = getId();
    result = 31 * result + (authorFirst != null ? authorFirst.hashCode() : 0);
    result = 31 * result + (authorLast != null ? authorLast.hashCode() : 0);
    result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
    result = 31 * result + getLikes();
    result = 31 * result + getTime();
    result = 31 * result + getVisibility();
    return result;
  }

  @Override
  public String toString() {
    return "Post{" +
        "id=" + id +
        ", authorFirst='" + authorFirst + '\'' +
        ", authorLast='" + authorLast + '\'' +
        ", content='" + content + '\'' +
        ", likes=" + likes +
        ", time=" + time +
        ", visibility=" + visibility +
        '}';
  }
}

