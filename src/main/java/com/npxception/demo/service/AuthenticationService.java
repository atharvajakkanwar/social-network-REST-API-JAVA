package com.npxception.demo.service;

import com.npxception.demo.dao.RoleDao;
import com.npxception.demo.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Atharva Jakkanwar on 30-Jun-17.
 */
@Service
public class AuthenticationService implements UserDetailsService{

  @Autowired
  @Qualifier("PostgreRoleRepo")
  private RoleDao roledao;

  public Role getRoleById(int roleid) {
    return this.roledao.getRoleById(roleid);
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return null;
  }
}

