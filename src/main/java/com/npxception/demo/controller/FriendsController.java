package com.npxception.demo.controller;

import com.npxception.demo.entity.User;
import com.npxception.demo.service.FriendsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

  @RequestMapping(value = "/get_all_friends_userid={userid}", method = RequestMethod.GET)
  public Collection<User> getUserById(@PathVariable("userid") int id) {
    return service.listByUserId(id);
  }

  @RequestMapping(value = "/remove_all_friends_userid={userid}",
      method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void removeAllFriends(@PathVariable("userid") int id) {
    this.service.removeAllFriends(id);
  }

  @RequestMapping(value = "/unfriend_id1={id1}/id2={id2}",
      method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void unFriend(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
    this.service.unFriend(id1, id2);
  }

  @RequestMapping(value = "/count_friends_userid={userid}", method = RequestMethod.GET)
  public int countFriends(@PathVariable("userid") int id) {
    return this.service.countFriends(id);
  }

  @RequestMapping(value = "/send_request_id1={id1}/id2={id2}",
      method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void sendRequest(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
    this.service.sendRequest(id1, id2);
  }

  @RequestMapping(value = "/accept_request_id1={id1}/id2={id2}",
      method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void acceptRequest(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
    this.service.acceptRequest(id1, id2);
  }
}