package com.andersen.jobsearch.demo.config;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.UserRole;
import com.andersen.jobsearch.demo.security.UserDetailsServiceImpl;
import com.andersen.jobsearch.demo.security.filters.JwtAuthenticationFilter;
import com.andersen.jobsearch.demo.security.filters.JwtAuthorizationFilter;

@EnableWebSecurity
@Configuration
@Order(1)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired 
    private UserDetailsServiceImpl userDetailsService;
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception 
    {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

	
    protected void configure(HttpSecurity http) throws Exception 
    {
        http
        		.httpBasic().disable()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers("/", "/login/**", "/logout/**", "/sign-up/**").permitAll()
                .and().authorizeRequests().antMatchers("/admin/**").hasRole(UserRole.ADMIN.name())
        		.and().authorizeRequests().antMatchers("/employee/**").hasRole(UserRole.EMPLOYEE.name())
        		.and().authorizeRequests().antMatchers("/employer/**").hasRole(UserRole.EMPLOYER.name())
        		.anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and().formLogin().loginPage( "/api/v1/auth/login/")
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()));
    }
}
