package com.npxception.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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


  //  @Override
  @Autowired
  protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("atharva").password("test").roles("USER")
        .and().withUser("gohan").password("test").roles("ADMIN");
  }

  // All requests single password
//  @Override
//  protected void configure(HttpSecurity httpSecurity) throws  Exception{
//    httpSecurity
//        .authorizeRequests()
//        .antMatchers("/user**")
//        .hasRole("USER")
//        .and().httpBasic();
//    httpSecurity.csrf().disable();
//  }

  //
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests().anyRequest().fullyAuthenticated()
        .antMatchers("auth/**").permitAll()
        .antMatchers("/user/**").hasAuthority("ADMIN")
        .and().httpBasic();
    httpSecurity.csrf().disable();
  }
}
