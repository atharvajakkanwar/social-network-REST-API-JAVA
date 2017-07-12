package com.npxception.demo.config;

import com.npxception.demo.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
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

  @Qualifier("primary")
  @Autowired
  private DataSource dataSource;

  @Autowired
  private AuthenticationService authenticationService;

  //  @Override
  @Autowired
  protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    ShaPasswordEncoder encoder = new ShaPasswordEncoder();
    auth.userDetailsService(authenticationService).passwordEncoder(encoder);

//    auth.jdbcAuthentication().dataSource(dataSource)
//        .usersByUsernameQuery("SELECT firstname,lastname, password from users where = ?")
//        .authoritiesByUsernameQuery("SELECT roleid from users where = ?");

//        auth.inMemoryAuthentication()
//        .withUser("atharva").password("test").authorities("USER")
//        .and().withUser("gohan").password("test").authorities("ADMIN");
  }

  //
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests()
        .antMatchers("/user/**").hasAuthority("ADMIN")
        .antMatchers("/friend/**").hasAuthority("ADMIN")
        .antMatchers("/group/**").hasAuthority("ADMIN")
        .antMatchers("/posts/**").hasAuthority("ADMIN")
        .antMatchers("auth/**").hasAnyAuthority("ADMIN", "USER")
        .and().httpBasic();
    httpSecurity.csrf().disable();
  }
}
