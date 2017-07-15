package com.npxception.demo.controller;

import com.npxception.demo.entity.User;
import com.npxception.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")
@Api(description = "User Controller")


public class UserController {

  @Autowired
  private UserService userService;

  @ApiOperation(value = "Gets all User")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(method = RequestMethod.GET)
  public Collection<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @ApiOperation(value = "Gets User given ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/{userid}", method = RequestMethod.GET)
  public User getUserById(@ApiParam(value = "User ID", required = true) @PathVariable("userid") int userid) {
    return userService.getUserById(userid);
  }

  @ApiOperation(value = "Delete User given ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully deleted user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/remove/{userid}", params = "id", method = RequestMethod.DELETE)
  public void deleteUserById(@ApiParam(value = "User ID", required = true) @PathVariable("userid") int id) {
    userService.removeUserById(id);
  }

  @ApiOperation(value = "Updates User")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully updated user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(method = RequestMethod.PUT, params = "id", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updateUserById(@ApiParam(value = "User ID", required = true)@RequestBody User user) {
    userService.updateUser(user);
  }

  @ApiOperation(value = "Inserts User into database")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully inserted user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void insertUser(@ApiParam(value = "User", required = true) @RequestBody User user) {
    userService.insertUser(user);
  }

  @ApiOperation(value = "Gets List of User given first name")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/first/{name}", method = RequestMethod.GET)
  public Collection<User> getUsersByFirstName(@ApiParam(value = "First name", required = true)
                                                @PathVariable("name") String name) {
    return userService.getUsersByFirstName(name);
  }

  @ApiOperation(value = "Gets List of User given last name")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/last/{name}", method = RequestMethod.GET)
  public Collection<User> getUsersByLastName(@ApiParam(value = "Last name", required = true)
                                               @PathVariable("name") String name) {
    return userService.getUsersByLastName(name);
  }

  @ApiOperation(value = "Gets List of User given full name")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/fullname/{name}", method = RequestMethod.GET)
  public Collection<User> getUsersByFullName(@ApiParam(value = "Full name", required = true)
                                               @PathVariable("name") String name) {
    return userService.getUsersByFullName(name);
  }

  @ApiOperation(value = "Gets List of User given username")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
  public Collection<User> getUserByUserName(@ApiParam(value = "Username", required = true)
                                              @PathVariable("username") String username) {
    return userService.getUsersByFirstName(username);
  }

  @ApiOperation(value = "Gets List of User given email")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
  public User getUserByEmail(@ApiParam(value = "Email address", required = true)
                               @PathVariable("email") String email) {
    return userService.getUserByEmail(email);
  }

  @ApiOperation(value = "Gets List of User given age")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/age/{age}", method = RequestMethod.GET)
  public Collection<User> getUserByAge(@ApiParam(value = "Age", required = true)
                                         @PathVariable("age") int age) {
    return userService.getUsersByAge(age);
  }

  @ApiOperation(value = "Gets List of User given gender")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/gender/{gender}", method = RequestMethod.GET)
  public Collection<User> getUsersByGender(@ApiParam(value = "Gender", required = true)
                                             @PathVariable("gender") String gender) {
    return userService.getUsersByGender(gender);
  }

  @ApiOperation(value = "Gets List of User given country")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/country/{country}", method = RequestMethod.GET)
  public Collection<User> getUsersByCountry(@ApiParam(value = "Country", required = true)
                                              @PathVariable("country") String country) {
    return userService.getUsersByCountry(country);
  }

  @ApiOperation(value = "Gets List of User given city")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
  public Collection<User> getUsersByCity(@ApiParam(value = "City", required = true)
                                           @PathVariable("city") String city) {
    return userService.getUsersByCity(city);
  }

  @ApiOperation(value = "Registers User")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully registered user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/register", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public void register(@ApiParam(value = "User", required = true)
                         @RequestBody User user){
    userService.register(user);
  }

  @ApiOperation(value = "Login")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully logged in"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public void login(@ApiParam(value = "Email address", required = true) @RequestBody String email,
                    @ApiParam(value = "Password", required = true) @RequestBody String password){
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