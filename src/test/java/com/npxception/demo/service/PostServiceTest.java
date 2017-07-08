package com.npxception.demo.service;

import com.npxception.demo.dao.PostgreSQLPostDaoImpl;
import com.npxception.demo.dao.PostgreSQLUserDaoImpl;
import com.npxception.demo.entity.Post;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bryan on 6/21/2017.
 */

public class PostServiceTest {
  private PostService postService;
  private PostgreSQLPostDaoImpl postgreSQLPostDao;

  @Before
  public void setUp() throws Exception {
    postgreSQLPostDao = Mockito.mock(PostgreSQLPostDaoImpl.class);
    postService = new PostService(postgreSQLPostDao);
  }


  @Test
  public void getPostsById() throws Exception {
    Post post1 = new Post();
    post1.setId(1);
    post1.setAuthor("a");
    post1.setContent("a");
    post1.setLikes(1);
    post1.setTime(1);
    post1.setVisibility(1);
    Post post2 = new Post();
    post2.setId(2);
    post2.setAuthor("b");
    post2.setContent("b");
    post2.setLikes(2);
    post2.setTime(2);
    post2.setVisibility(2);

    List<Post> list = new ArrayList<>();
    list.add(post1);
    list.add(post2);
    Mockito.doReturn(post1).when(postgreSQLPostDao).getPostsById(1);
    Assert.assertEquals(post1, postgreSQLPostDao.getPostsById(1));

  }

}