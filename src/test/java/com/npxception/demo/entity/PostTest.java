package com.npxception.demo.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bryan on 6/21/2017.
 */
public class PostTest {
  private Post post1;
  private Post mt;
  private Post newPost;
  @Before
  public void setUp() throws Exception {
    this.post1 = new Post(1, 2, "Hello World", 5, 12, 1);
    this.mt = new Post();
    this.newPost = new Post(37, 37, "a", 37, 37, 37);
  }

  @Test
  public void testsForAllSetters() throws Exception {
    this.mt.setId(37);
    this.mt.setAuthor(37);
    this.mt.setContent("a");
    this.mt.setLikes(37);
    this.mt.setTime(37);
    this.mt.setVisibility(37);
    Assert.assertEquals(mt, newPost);
  }

  @Test
  public void getId() throws Exception {
    Assert.assertEquals(post1.getId(), 1);

  }


  @Test
  public void getAuthor() throws Exception {
    Assert.assertEquals(post1.getAuthor(), 2);
  }


  @Test
  public void getContent() throws Exception {
    Assert.assertEquals(post1.getContent(),"Hello World");
  }


  @Test
  public void getLikes() throws Exception {
    Assert.assertEquals(post1.getLikes(), 5);
  }




  @Test
  public void getTime() throws Exception {
    Assert.assertEquals(post1.getTime(), 12);

  }

  @Test
  public void getVisibility() throws Exception {
    Assert.assertEquals(post1.getVisibility(), 1);
  }



}