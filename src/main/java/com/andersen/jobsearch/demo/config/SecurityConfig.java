package com.andersen.jobsearch.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http
			.formLogin()
			.loginProcessingUrl("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.loginPage("login.html")
			.failureUrl("/login?error");
	}
	
}
