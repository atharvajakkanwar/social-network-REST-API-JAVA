package com.npxception.demo.controller;

import com.npxception.demo.entity.User;
import com.npxception.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")


public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(method = RequestMethod.GET)
  public Collection<User> getAllUsers() {
    return userService.getAllUser();
  }

  @RequestMapping(value = "/userid={userid}", method = RequestMethod.GET)
  public User getUserById(@PathVariable("userid") int userid) {
    return userService.getUserById(userid);
  }

  @RequestMapping(value = "/id={id}", params = "id", method = RequestMethod.DELETE)
  public void deleteUserById(@PathVariable("id") int id) {
    userService.removeUserById(id);
  }

  @RequestMapping(method = RequestMethod.PUT, params = "id", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updateUserById(@RequestBody User user) {
    userService.updateUser(user);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void insertUser(@RequestBody User user) {
    userService.insertUser(user);
  }


  @RequestMapping(value = "/name={name}", method = RequestMethod.GET)
  public Collection<User> getUsersByName(@PathVariable("name") String name) {
    return userService.getUserByName(name);
  }

  @RequestMapping(value = "/gender={gender}", method = RequestMethod.GET)
  public Collection<User> getStudentsByGender(@PathVariable("gender") String gender) {
    return userService.getUserByGender(gender);
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public void register(@RequestBody User user){
    userService.insertUserToDb(user);
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public void login(@RequestBody String email, @RequestBody String password){
    userService.login(email, password);
  }

 // will prob need to add methods for country/city/password
}
