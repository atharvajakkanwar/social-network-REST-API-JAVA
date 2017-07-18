package com.npxception.demo.controller;

import com.npxception.demo.entity.User;
import com.npxception.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/")


public class UserController {

  @Autowired
  private UserService userService;
//
//  public int getUserId(){
//    String email = new AuthenticationController().getEmail();
//
//    return userService.getUserByEmail(email).getId();
//  }

  @RequestMapping(value = "/aaaaa", method = RequestMethod.GET)
  public Collection<User> getAllUsers() {
    return userService.getAllUser();
  }

  @RequestMapping(value = "/find/{userid}", method = RequestMethod.GET)
  public User getUserById(@PathVariable("userid") int userid) {
    return userService.getUserById(userid);
  }

  @RequestMapping(value = "/remove/{userid}", params = "id", method = RequestMethod.DELETE)
  public void deleteUserById(@PathVariable("userid") int id) {
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


  @RequestMapping(value = "/find/first/{name}", method = RequestMethod.GET)
  public Collection<User> getUsersByFirstName(@PathVariable("name") String name) {
    return userService.getUsersByFirstName(name);
  }

  @RequestMapping(value = "/find/last/{name}", method = RequestMethod.GET)
  public Collection<User> getUsersByLastName(@PathVariable("name") String name) {
    return userService.getUsersByLastName(name);
  }

  @RequestMapping(value = "/find/fullname/{name}", method = RequestMethod.GET)
  public Collection<User> getUsersByFullName(@PathVariable("name") String name) {
    return userService.getUsersByFullName(name);
  }

  @RequestMapping(value = "/find/user/{username}", method = RequestMethod.GET)
  public Collection<User> getUserByUserName(@PathVariable("username") String username) {
    return userService.getUsersByFirstName(username);
  }

  @RequestMapping(value = "/find/email/{email}", method = RequestMethod.GET)
  public User getUserByEmail(@PathVariable("email") String email) {
    return userService.getUserByEmail(email);
  }

  @RequestMapping(value = "/find/age/{age}", method = RequestMethod.GET)
  public Collection<User> getUserByAge(@PathVariable("age") int age) {
    return userService.getUsersByAge(age);
  }

  @RequestMapping(value = "/find/gender/{gender}", method = RequestMethod.GET)
  public Collection<User> getUsersByGender(@PathVariable("gender") String gender) {
    return userService.getUsersByGender(gender);
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public void register(@RequestBody User user){
    userService.register(user);
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public void login(@RequestBody String email, @RequestBody String password){
    userService.login(email, password);
  }

  @RequestMapping(value = "/{firstname}.{lastname}", method = RequestMethod.GET)
  public User personalPage(@PathVariable("firstname") String firstName,
                           @PathVariable("lastname") String lastName){
    return userService.getUserByUserName(firstName+"."+lastName);
  }
  // will prob need to add methods for country/city/password
}