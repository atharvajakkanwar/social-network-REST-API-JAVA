package com.npxception.demo.dao;

import com.npxception.demo.entity.Class;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@org.springframework.beans.factory.annotation.Qualifier("fakeData")
public class ClassDaoImpl implements ClassDao {

  private static Map<Integer, Class> classMap;

  static {

    classMap = new HashMap<Integer, Class>(){

      {
        put(1, new Class(1, "CSE 142", "CSE", "Marty Stepp", 200));
        put(2, new Class(2, "Undergrad Research", "Lewis", "Nathan Kutz", 10));
        put(3, new Class(3, "MATH 402", "Anderson", "Monty McGovern", 30));
        put(4, new Class(4, "AMATH 301", "Lewis", "Chris Vogl", 120));
      }
    };
  }


  @Override
  public Collection<Class> getAllClasses() {
    return this.classMap.values();
  }

  @Override
  public Class getClassByID(int id) {
    return this.classMap.get(id);
  }

  @Override
  public void removeClassById(int id) {
    this.classMap.remove(id);
  }

  @Override
  public void updateClass(Class classes) {
    classMap.get(classes).setSize(classMap.get(classes).getSize()+1);
  }

  @Override
  public void insertClassToDb(Class classes) {
    this.classMap.put(classes.getClassID(), classes);


  }
}
