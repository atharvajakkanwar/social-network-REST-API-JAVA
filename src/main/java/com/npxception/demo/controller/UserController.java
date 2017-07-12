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
    return userService.getAllUsers();
  }

  @RequestMapping(value = "/{userid}", method = RequestMethod.GET)
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

  @RequestMapping(value = "/first/{name}", method = RequestMethod.GET)
  public Collection<User> getUsersByFirstName(@PathVariable("name") String name) {
    return userService.getUsersByFirstName(name);
  }

  @RequestMapping(value = "/last/{name}", method = RequestMethod.GET)
  public Collection<User> getUsersByLastName(@PathVariable("name") String name) {
    return userService.getUsersByLastName(name);
  }

  @RequestMapping(value = "/fullname/{name}", method = RequestMethod.GET)
  public Collection<User> getUsersByFullName(@PathVariable("name") String name) {
    return userService.getUsersByFullName(name);
  }

  @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
  public Collection<User> getUserByUserName(@PathVariable("username") String username) {
    return userService.getUsersByFirstName(username);
  }

  @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
  public User getUserByEmail(@PathVariable("email") String email) {
    return userService.getUserByEmail(email);
  }

  @RequestMapping(value = "/age/{age}", method = RequestMethod.GET)
  public Collection<User> getUserByAge(@PathVariable("age") int age) {
    return userService.getUsersByAge(age);
  }

  @RequestMapping(value = "/gender/{gender}", method = RequestMethod.GET)
  public Collection<User> getUsersByGender(@PathVariable("gender") String gender) {
    return userService.getUsersByGender(gender);
  }

  @RequestMapping(value = "/country/{country}", method = RequestMethod.GET)
  public Collection<User> getUsersByCountry(@PathVariable("country") String country) {
    return userService.getUsersByCountry(country);
  }

  @RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
  public Collection<User> getUsersByCity(@PathVariable("city") String city) {
    return userService.getUsersByCity(city);
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

  @RequestMapping(value = "/first={first}", params = "first", method = RequestMethod.POST)
  public void setFirst(@PathVariable("first") String first) {
    userService.setFirst(first);
  }

  @RequestMapping(value = "/last={last}", params = "last", method = RequestMethod.POST)
  public void setLast(@PathVariable("last") String last) {
    userService.setLast(last);
  }

  @RequestMapping(value = "/email={email}", params = "email", method = RequestMethod.POST)
  public void setEmail(@PathVariable("email") String email) {
    userService.setEmail(email);
  }

  @RequestMapping(value = "/age={age}", params = "age", method = RequestMethod.POST)
  public void setAge(@PathVariable("age") int age) {
    userService.setAge(age);
  }

  @RequestMapping(value = "/gender={gender}", params = "gender", method = RequestMethod.POST)
  public void setGender(@PathVariable("gender") String gender) {
    userService.setGender(gender);
  }

  @RequestMapping(value = "/country={country}", params = "country", method = RequestMethod.POST)
  public void setCountry(@PathVariable("country") String country) {
    userService.setCountry(country);
  }

  @RequestMapping(value = "/city={city}", params = "city", method = RequestMethod.POST)
  public void setCity(@PathVariable("city") String city) {
    userService.setCity(city);
  }

}