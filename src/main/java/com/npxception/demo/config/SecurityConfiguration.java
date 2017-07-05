package com.npxception.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Atharva Jakkanwar on 03-Jul-17.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//  @Override
//  protected  void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication()
//        .withUser("atharva").password("test").roles("USER").and()
//        .withUser("atharva").password("password").roles("USER");
//  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws  Exception{
    httpSecurity
        .authorizeRequests()
        .anyRequest()
        .fullyAuthenticated()
        .and().httpBasic();
    httpSecurity.csrf().disable();
  }
}
