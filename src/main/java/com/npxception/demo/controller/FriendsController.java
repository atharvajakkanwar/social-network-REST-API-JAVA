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

  @RequestMapping(value = "/{user}/friends/all",
      method = RequestMethod.GET)
  public Collection<User> getAllFriends(@PathVariable("user") int id) {
    return service.getAllFriends(id);
  }

  //consumes = MediaType.APPLICATION_JSON_VALUE --- just for future reference, maybe I will need this
  @RequestMapping(value = "/{user}/friends/all",
      method = RequestMethod.DELETE)
  public void removeAllFriends(@PathVariable("user") int id) {
    this.service.removeAllFriends(id);
  }

  @RequestMapping(value = "/{user}/friend/unfriend/{username}",
      method = RequestMethod.PUT)
  public void unFriend(@PathVariable("user") int id1, @PathVariable("username") String username) {
    this.service.unFriend(id1, username);
  }

  @RequestMapping(value = "/{user}/friend/count",
      method = RequestMethod.GET)
  public int countFriends(@PathVariable("user") int id) {
    return this.service.countFriends(id);
  }

  @RequestMapping(value = "/{user}/friend/request/{username}",
      method = RequestMethod.PUT)
  public void sendRequest(@PathVariable("user") int id1, @PathVariable("id2") String username) {
    this.service.sendRequest(id1, username);
  }

  @RequestMapping(value = "/{user}/friends/accept/{username}",
      method = RequestMethod.PUT)
  public void becomeFriend(@PathVariable("user") int id1, @PathVariable("username") String username) {
    this.service.becomeFriend(id1, username);
  }

  @RequestMapping(value = "/{user}/friends/block/{username}",
      method = RequestMethod.PUT)
  public void blockFriend(@PathVariable("user") int id1, @PathVariable("username") String username) {
    this.service.blockFriend(id1, username);
  }

  @RequestMapping(value = "/{user}/friend/common/{username}",
      method = RequestMethod.GET)
  public Collection<User> commonFriends(@PathVariable("user") int id1,
                                        @PathVariable("username") String username) {
    return this.service.commonFriends(id1, username);
  }

  @RequestMapping(value = "/{user}/friends/{username}",
      method = RequestMethod.GET)
  public Collection<User> getFriendsByName(@PathVariable("username") String username,
                                           @PathVariable("user") int id) {
    return this.service.getFriendsByName(username, id);
  }

  @RequestMapping(value = "/{user}/friends/pending-invations",
      method = RequestMethod.GET)
  public Collection<User> getInvitationList(@PathVariable("user") int id) {
    return this.service.getInvitationList(id);
  }

  @RequestMapping(value = "/{user}/friends/blocked",
      method = RequestMethod.GET)
  public Collection<User> getBlockList(@PathVariable("user") int id) {
    return this.service.getBlockList(id);
  }
}