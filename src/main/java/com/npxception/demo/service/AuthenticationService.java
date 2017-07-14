package com.npxception.demo.service;

import com.npxception.demo.dao.RoleDao;
import com.npxception.demo.dao.UserDao;
import com.npxception.demo.entity.Role;
import com.npxception.demo.helperMethods.Usernames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by Atharva Jakkanwar on 30-Jun-17.
 */
@Service
public class AuthenticationService implements UserDetailsService{

  @Autowired
  @Qualifier("PostgreRoleRepo")
  private RoleDao roledao;

  @Autowired
  private UserDao userDao;

  public Role getRoleById(int roleid) {
    return this.roledao.getRoleById(roleid);
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    com.npxception.demo.entity.User user = userDao.getUserByUserName(s);
    GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
    String fullName = new Usernames().getFullName(user.getFirstName(), user.getLastName());
    UserDetails details = (UserDetails)new org.springframework.security.core.userdetails
        //this is full name or anything else??
        .User(fullName, user.getPassword(), Arrays.asList(authority));
    return details;
  }
}
