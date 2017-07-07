package com.npxception.demo.service;

import com.npxception.demo.dao.RoleDao;
import com.npxception.demo.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Atharva Jakkanwar on 30-Jun-17.
 */
@Service
public class AuthenticationService {

  @Autowired
  @Qualifier("PostgreRoleRepo")
  private RoleDao roledao;

  public Role getRoleById(int roleid) {
    return this.roledao.getRoleById(roleid);
  }

}
