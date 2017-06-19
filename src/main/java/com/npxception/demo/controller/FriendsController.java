package com.npxception.demo.controller;

import com.npxception.demo.entity.User;
import com.npxception.demo.service.FriendsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by RachelDi on 18/06/2017.
 */
@RestController
@RequestMapping("/friend")
public class FriendsController {
  @Autowired
  private FriendsService service;

  @RequestMapping(value = "/userid={userid}", method = RequestMethod.GET)
  public Collection<User> getUserById(@PathVariable("userid") int userid) {
    return service.listByUserId(userid);
  }

  public void removeAllFriends(User user) {
    this.service.removeAllFriends(user);
  }

  public void unFriend(User user1, User user2) {
    this.service.unFriend(user1, user2);
  }

  public int countFriends(User user) {
    return this.service.countFriends(user);
  }

  public void sendRequest(User user1, User user2) {
    this.service.sendRequest(user1, user2);
  }

  public void acceptRequest(User user1, User user2) {
    this.service.acceptRequest(user1, user2);
  }
}