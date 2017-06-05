package com.npxception.demo.controller;

import com.npxception.demo.entity.Assignment;
import com.npxception.demo.service.AssignmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by bryan on 6/2/2017.
 */

@RestController
@RequestMapping("/assignment")

public class AssignmentController {

  @Autowired
  private AssignmentService assignmentService;

  @RequestMapping(method = RequestMethod.GET)
  public Collection<Assignment> getAllAssingment() {
    return assignmentService.getAllAssignment();
  }

  @RequestMapping(value = "/id = {id}", method = RequestMethod.GET)
  public Assignment getAssignmentById(@PathVariable("id") int id) {
    return assignmentService.getAssignmentById(id);
  }

  @RequestMapping(value = "/id = {id}", params = "id", method = RequestMethod.DELETE)
  public void deleteAssignmentById(@PathVariable("id") int id) {
    assignmentService.removeAssignmentById(id);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void insertAssignment(@RequestBody Assignment assignment) {
    assignmentService.insertAssignment(assignment);
  }

  @RequestMapping(value = "/course = {course}", method = RequestMethod.GET)
  public Collection<Assignment> getAssignmentByCourse(@PathVariable("course") String course) {
    return assignmentService.getAssignmentByCourse(course);
  }

  @RequestMapping(value = "/content = {content}", method = RequestMethod.GET)
  public Collection<Assignment> getAssignmentByContent(@PathVariable("content") String content) {
    return assignmentService.getAssignmentByContent(content);
  }

}
