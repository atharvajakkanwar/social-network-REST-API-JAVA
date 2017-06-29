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

  @RequestMapping(value = "/get-all-friends-userid/{userid}",
      method = RequestMethod.GET)
  public Collection<User> getAllFriends(@PathVariable("userid") int id) {
    return service.getAllFriends(id);
  }

  //consumes = MediaType.APPLICATION_JSON_VALUE --- just for future reference, maybe I will need this
  @RequestMapping(value = "/remove-all-friends-userid/{userid}",
      method = RequestMethod.PUT)
  public void removeAllFriends(@PathVariable("userid") int id) {
    this.service.removeAllFriends(id);
  }

  @RequestMapping(value = "/unfriend-id1/{id1}/id2/{id2}",
      method = RequestMethod.PUT)
  public void unFriend(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
    this.service.unFriend(id1, id2);
  }

  @RequestMapping(value = "/count-friends-userid/{userid}",
      method = RequestMethod.GET)
  public int countFriends(@PathVariable("userid") int id) {
    return this.service.countFriends(id);
  }

  @RequestMapping(value = "/send-request-id1/{id1}/id2/{id2}",
      method = RequestMethod.PUT)
  public void sendRequest(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
    this.service.sendRequest(id1, id2);
  }

  @RequestMapping(value = "/become-friend-id1/{id1}/id2/{id2}",
      method = RequestMethod.PUT)
  public void becomeFriend(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
    this.service.becomeFriend(id1, id2);
  }

  @RequestMapping(value = "/block-friend-id1/{id1}/id2/{id2}",
      method = RequestMethod.PUT)
  public void blockFriend(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
    this.service.blockFriend(id1, id2);
  }

  @RequestMapping(value = "/common-friends-id1/{id1}/id2/{id2}",
      method = RequestMethod.GET)
  public Collection<User> commonFriends(@PathVariable("id1") int id1,
                                        @PathVariable("id1") int id2) {
    return this.service.commonFriends(id1, id2);
  }

  @RequestMapping(value = "/get-friends-byname-id/{id}/name/{name}",
      method = RequestMethod.GET)
  public Collection<User> getFriendsByName(@PathVariable("name") String name,
                                           @PathVariable("id") int id) {
    return this.service.getFriendsByName(name, id);
  }

  @RequestMapping(value = "/get-invitation-list-userid/{userid}",
      method = RequestMethod.GET)
  public Collection<User> getInvitationList(@PathVariable("userid") int id) {
    return this.service.getInvitationList(id);
  }

  @RequestMapping(value = "/get-block-list-userid/{userid}",
      method = RequestMethod.GET)
  public Collection<User> getBlockList(@PathVariable("userid") int id) {
    return this.service.getBlockList(id);
  }
}