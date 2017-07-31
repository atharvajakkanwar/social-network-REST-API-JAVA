package com.npxception.demo.dao;

import com.npxception.demo.entity.Role;
import com.npxception.demo.entity.User;
import com.npxception.demo.exceptions.AuthenticationException;
import com.npxception.demo.helperMethods.UserRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Atharva Jakkanwar on 30-Jun-17.
 */
@Repository("PostgreRoleRepo")
public class PostgreSQLRoleDaoImpl implements  RoleDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Role getRoleById(int roleid) {
    final String sql = "SELECT * FROM role WHERE roleid = ?";
    Role role = jdbcTemplate.queryForObject(sql, new RoleRowMapper(), roleid);
    return role;
  }

  private static class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
      Role role = new Role();
      role.setRole(resultSet.getString("role"));
      role.setRoleid(resultSet.getInt("roleid"));
      return role;
    }
  }

  public void checkUser(int id){
    String sql0 = "SELECT * FROM users WHERE userid = ?";
    User user = jdbcTemplate.queryForObject(sql0, new Object[]{id}, new UserRowMapper());
    String email = user.getEmail();
    String password = user.getPassword();
    String sql1 = "SELECT email FROM loginfo";
    String sql2 = "SELECT password FROM loginfo";
    String loginEmail = jdbcTemplate.queryForObject(sql1, new Object[]{}, String.class);
    String loginPass = jdbcTemplate.queryForObject(sql2, new Object[]{}, String.class);
    if ((!email.equals(loginEmail)) || (!password.equals(loginPass))){
      throw new AuthenticationException(id);
    }
  }
}