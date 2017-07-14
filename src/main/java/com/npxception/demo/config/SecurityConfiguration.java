package com.npxception.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by Atharva Jakkanwar on 03-Jul-17.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  private int userid;
  @Qualifier("primary")
  @Autowired
  private DataSource dataSource;

  @Autowired
  protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    JdbcUserDetailsManagerConfigurer res = auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("SELECT email AS principal, password AS credentials" +
            ", true FROM users WHERE email = ?")
        .authoritiesByUsernameQuery("SELECT email AS principal, role AS role FROM users " +
            "WHERE email = ?");
    //set userid

  }

  //
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests()
        .antMatchers("/user/**").hasAuthority("USER")
        .antMatchers("/friend/**").hasAuthority("ADMIN")
        .antMatchers("/group/**").hasAuthority("ADMIN")
        .antMatchers("/posts/**").hasAuthority("ADMIN")
        .antMatchers("/auth/**").hasAnyAuthority("ADMIN", "USER")
        .and().exceptionHandling().accessDeniedPage("/403")
        .and().httpBasic();
    httpSecurity.csrf().disable();
  }
}
