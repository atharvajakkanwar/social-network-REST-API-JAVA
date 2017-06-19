package com.npxception.demo.controller;

import com.npxception.demo.entity.FbGroup;
import com.npxception.demo.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by bryan on 6/2/2017.
 */

@RestController
@RequestMapping("/Fbgroup")

public class GroupController {

  @Autowired
  private GroupService courseService;

  @RequestMapping(method = RequestMethod.GET)
  public Collection<FbGroup> getAllCourse() {
    return courseService.getAllGroup();
  }

  @RequestMapping(value = "/id = {id}", method = RequestMethod.GET)
  public FbGroup getCourseById(@PathVariable("id") int id) {
    return courseService.getGroupById(id);
  }

  @RequestMapping(value = "/id = {id}", params = "id", method = RequestMethod.DELETE)
  public void deleteGroupById(@PathVariable("id") int id) {
    courseService.removeGroupById(id);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void insertGroup(@RequestBody FbGroup group) {
    courseService.insertGroup(group);
  }

//  @RequestMapping(value = "/professor = {professor}", method = RequestMethod.GET)
//  public Collection<Group> getGroupByProf(@PathVariable("professor") String professor) {
//    return courseService.getGroupByProf(professor);
//  }

  @RequestMapping(value = "/title = {title}", method = RequestMethod.GET)
  public Collection<FbGroup> getCourseByTitle(@PathVariable("title") String title) {
    return courseService.getGroupByGroupName(title);
  }

//  @RequestMapping(value = "/size = {size}", method = RequestMethod.GET)
//  public Collection<Group> getGroupByName(@PathVariable("size") int size) {
//    return courseService.getGroupBySize(size);
//  }

}
