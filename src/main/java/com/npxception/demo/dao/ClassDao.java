package com.npxception.demo.dao;

import com.npxception.demo.entity.Class;

import java.util.Collection;

/**
 * Created by bryan on 5/28/2017.
 */
public interface ClassDao {
  Collection<Class> getAllClasses();

  Class getClassByID(int id);

  void removeClassById(int id);

  void updateClass(Class classes);

  void insertClassToDb(Class classes);

}
