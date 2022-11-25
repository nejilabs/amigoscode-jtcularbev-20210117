package com.nellyxinwei.backend.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nellyxinwei.backend.appuser.AppUserService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {
  private final AppUserService appUserService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authenticationProvider(daoAuthenticationProvider())
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/v*/registration/**").permitAll()
        .anyRequest()
        .authenticated().and()
        .formLogin();
    return http.build();

  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(bCryptPasswordEncoder);
    provider.setUserDetailsService(appUserService);
    return provider;
  }
}

// @Configuration
// @AllArgsConstructor
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

// private final AppUserService appUserService;
// private final BCryptPasswordEncoder bCryptPasswordEncoder;

// @Override
// protected void configure(HttpSecurity http) throws Exception {
// http
// .csrf().disable()
// .authorizeRequests()
// .antMatchers("/api/v*/registration/**")
// .permitAll()
// .anyRequest()
// .authenticated().and()
// .formLogin();
// }

// @Override
// protected void configure(AuthenticationManagerBuilder auth) throws Exception
// {
// auth.authenticationProvider(daoAuthenticationProvider());
// }

// @Bean
// public DaoAuthenticationProvider daoAuthenticationProvider() {
// DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
// provider.setPasswordEncoder(bCryptPasswordEncoder);
// provider.setUserDetailsService(appUserService);
// return provider;
// }

// }