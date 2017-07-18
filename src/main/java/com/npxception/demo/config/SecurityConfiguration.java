package com.npxception.demo.config;

import com.npxception.demo.controller.AuthenticationController;
import com.npxception.demo.dao.PostgreSQLUserDaoImpl;
import com.npxception.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

/**
 * Created by Atharva Jakkanwar on 03-Jul-17.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Qualifier("primary")
  @Autowired
  private DataSource dataSource;

//  @Autowired
//  private UserService userService;

  @Bean
  public RedirectLoginSuccessHandler loginFailedHandler(){
    return new RedirectLoginSuccessHandler();
  }

  @Bean
  public RedirectLoginFailHandler loginSuccessHandler(){
    return new RedirectLoginFailHandler();
  }

  @Autowired
  protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("SELECT email AS principal, password AS credentials" +
            ", true FROM users WHERE email = ?")
        .authoritiesByUsernameQuery("SELECT email AS principal, role AS role FROM users " +
            "WHERE email = ?");
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests()
        .antMatchers("/").hasAnyAuthority("ADMIN", "USER")
        .antMatchers("/user/**").hasAuthority("ADMIN")
        .antMatchers("/friend/**").hasAuthority("ADMIN")
        .antMatchers("/group/**").hasAuthority("ADMIN")
        .antMatchers("/posts/**").hasAuthority("ADMIN")
        .antMatchers("/auth/**").hasAnyAuthority("ADMIN", "USER")
        .and().formLogin().successHandler(loginFailedHandler())
       // .and().formLogin().failureHandler(loginSuccessHandler())
        .and().httpBasic();
    httpSecurity.csrf().disable();
  }
}
