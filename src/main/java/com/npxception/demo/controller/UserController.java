package com.npxception.demo.controller;

import com.npxception.demo.entity.User;
import com.npxception.demo.exceptions.DuplicateEmailException;
import com.npxception.demo.exceptions.ResourceNotFoundException;
import com.npxception.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Represents a controller for the User service.
 */

@RestController
@RequestMapping("/user")
@Api(description = "User Controller")
public class UserController {

  @Autowired
  private UserService userService;

  @ApiOperation(value = "Gets User given ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/user/{userid}", method = RequestMethod.GET)
  public User getUserById(@ApiParam(value = "User ID", required = true) @PathVariable("userid") int userid) {
    try {
      return userService.getUserById(userid);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException(Integer.toString(userid));
    }
  }

  @ApiOperation(value = "Delete User given ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully deleted user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/remove/{userid}", method = RequestMethod.DELETE)
  public void deleteUserById(@ApiParam(value = "User ID", required = true) @RequestBody int userid) {
    userService.removeUserById(userid);
  }

  @ApiOperation(value = "Updates User")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully updated user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(method = RequestMethod.PUT, params = "id", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updateUserById(@ApiParam(value = "User ID", required = true) @RequestBody User user) {
    userService.updateUser(user);
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
    Collection<User> result = userService.getUsersByFirstName(name);
    if (result.size() == 0) {
      throw new ResourceNotFoundException(name);
    }
    return result;
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
    Collection<User> result = userService.getUsersByLastName(name);
    if (result.size() == 0) {
      throw new ResourceNotFoundException(name);
    }
    return result;
  }

  @ApiOperation(value = "Gets List of User given full name")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/fullname/{name}", method = RequestMethod.GET)
  public User getUsersByFullName(@ApiParam(value = "name", required = true)
                                 @PathVariable("name") String name) {
    try {
      return userService.getUsersByFullName(name);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException(name);
    }
  }

  @ApiOperation(value = "Gets List of User given username")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list of user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/username/{firstname}.{lastname}", method = RequestMethod.GET)
  public User personalPage(
      @ApiParam(value = "Firstname", required = true)
      @PathVariable("firstname") String firstName,
      @ApiParam(value = "Lastname", required = true)
      @PathVariable("lastname") String lastName) {
    try {
      return userService.getUserByUserName(firstName + "." + lastName);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException(firstName, lastName);
    }
  }

  @ApiOperation(value = "Gets List of User given email")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/email/{email}/", method = RequestMethod.GET)
  public User getUserByEmail(@ApiParam(value = "Email address", required = true)
                             @PathVariable("email") String email) {
    try {
      return userService.getUserByEmail(email);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException(email);
    }
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
    Collection<User> result = userService.getUsersByAge(age);
    if (result.size() == 0) {
      throw new ResourceNotFoundException(age);
    }
    return result;
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
    Collection<User> result = userService.getUsersByGender(gender);
    if (result.size() == 0) {
      throw new ResourceNotFoundException(gender);
    }
    return result;
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
    Collection<User> result = userService.getUsersByCountry(country);
    if (result.size() == 0) {
      throw new ResourceNotFoundException(country);
    }
    return result;
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
    Collection<User> result = userService.getUsersByCity(city);
    if (result.size() == 0) {
      throw new ResourceNotFoundException(city);
    }
    return result;
  }

  @ApiOperation(value = "Registers User WHERE: id is not required")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully registered user"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })

  @RequestMapping(value = "/register", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public void register(
      //@ApiParam(value = "User", required = true)
      @RequestBody User user) {
    try {
      System.out.println(user.getFirstName());
      System.out.println(user.getLastName());
      userService.register(user);
    } catch (DataIntegrityViolationException e) {
      throw new DuplicateEmailException(user);
    }
  }

//  @ApiOperation(value = "Login")
//  @ApiResponses(value = {
//      @ApiResponse(code = 200, message = "Successfully logged in"),
//      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
//  })
//  @RequestMapping(value = "/login", method = RequestMethod.POST)
//  public void login(@ApiParam(value = "Email address", required = true) @RequestBody String email,
//                    @ApiParam(value = "Password", required = true) @RequestBody String password){
//    userService.login(email, password);
//  }

  // will prob need to add methods for country/city/password

  @ApiOperation(value = "Set the first name of the user")
  @RequestMapping(value = "/setfirst/{first}", params = "first", method = RequestMethod.POST)
  public void setFirst(@ApiParam(value = "First name", required = true)
                       @PathVariable("first") String first) {
    userService.setFirst(first);
  }

  @ApiOperation(value = "Set the last name of the user")
  @RequestMapping(value = "/setlast/{last}", params = "last", method = RequestMethod.POST)
  public void setLast(@ApiParam(value = "Last name", required = true)
                      @PathVariable("last") String last) {
    userService.setLast(last);
  }

  @ApiOperation(value = "Set email of the user")
  @RequestMapping(value = "/setemail/{email}/", params = "email", method = RequestMethod.POST)
  public void setEmail(@ApiParam(value = "Email", required = true)
                       @PathVariable("email") String email) {
    try {
      userService.setEmail(email);
    } catch (DataIntegrityViolationException e) {
      throw new DuplicateEmailException(email);
    }
  }

  @ApiOperation(value = "Set age of the user")
  @RequestMapping(value = "/setage/{age}", method = RequestMethod.POST)
  public void setAge(@ApiParam(value = "Age", required = true)
                     @PathVariable("age") int age) {
    userService.setAge(age);
  }

  @ApiOperation(value = "Set gender of the user")
  @RequestMapping(value = "/setgender/{gender}", params = "gender", method = RequestMethod.POST)
  public void setGender(@ApiParam(value = "Gender", required = true)
                        @PathVariable("gender") String gender) {
    userService.setGender(gender);
  }

  @ApiOperation(value = "Set country of the user")
  @RequestMapping(value = "/setcountry/{country}", params = "country", method = RequestMethod.POST)
  public void setCountry(@ApiParam(value = "Country", required = true)
                         @PathVariable("country") String country) {
    userService.setCountry(country);
  }

  @ApiOperation(value = "Set city of the user")
  @RequestMapping(value = "/setcity/{city}", params = "city", method = RequestMethod.POST)
  public void setCity(@ApiParam(value = "City", required = true)
                      @PathVariable("city") String city) {
    userService.setCity(city);
  }

  @ApiOperation(value = "Set password for the user")
  @RequestMapping(value = "/setpassword/{password}", params = "password", method = RequestMethod.POST)
  public void setPassword(@ApiParam(value = "Password", required = true)
                          @PathVariable("password") String password) {
    userService.setPassword(password);
  }
}
