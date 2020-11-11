package com.andersen.jobsearch.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.java.Log;

@SpringBootApplication
@EntityScan("com.andersen.jobsearch.demo.entity")
public class DemoApplication extends SpringBootServletInitializer
{       
	public static void main(String[] args) 
	{
		SpringApplication.run(DemoApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) 
	{
       	return application.sources(DemoApplication.class);
	}
}
