package com.andersen.jobsearch.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.extern.java.Log;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer
{
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) 
	{
       		return application.sources(DemoApplication.class);
	}
        
	public static void main(String[] args) 
	{
		SpringApplication.run(DemoApplication.class, args);
	}
}
