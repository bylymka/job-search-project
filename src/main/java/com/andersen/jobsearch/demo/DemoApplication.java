package com.andersen.jobsearch.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.extern.java.Log;

//exclude = {DataSourceAutoConfiguration.class }
@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer
{
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) 
	{
        return application.sources(DemoApplication.class);
	}
        
	public static void main(String[] args) 
	{
		System.setProperty("server.servlet.context-path", "/job-search");
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
}
