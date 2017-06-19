//package com.npxception.demo.service;
//
//import com.npxception.demo.dao.PostsDao;
//import com.npxception.demo.entity.Posts;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//
//
///**
// * Created by bryan on 6/2/2017.
// */
//
//@Service
//public class PostsService {
//
//  @Autowired
//  @Qualifier("fakeData")
//  private PostsDao assignmentDao;
//
//  public Collection<Posts> getAllPosts() {
//    return this.assignmentDao.getAllPosts();
//  }
//
//  public Posts getPostsById(int id) {
//    return this.assignmentDao.getPostsById(id);
//  }
//
//  public void removePostsById(int id) {
//    this.assignmentDao.removePostsById(id);
//  }
//
//  public void updatePosts (Posts assignment) {
//    this.assignmentDao.updatePosts(assignment);
//  }
//
//  public void insertPosts(Posts assignment) {
//    this.assignmentDao.insertPostsToDb(assignment);
//  }
//
//
//
//  public Collection<Posts> getPostsByContent(String content) {
//    return this.assignmentDao.getPostsByContent(content);
//  }
//
//
//}
