package com.npxception.demo.dao;

import com.npxception.demo.entity.Role;

import org.springframework.stereotype.Repository;

/**
 * Created by Atharva Jakkanwar on 30-Jun-17.
 */
@Repository("PostgreRoleRepo")
public class PostGreSQLRoleDaoImpl implements  RoleDao {
  @Override
  public Role findRoleBy(String role) {
    return null;
  }
}
