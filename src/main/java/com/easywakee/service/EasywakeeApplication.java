package com.easywakee.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan(basePackageClasses=ServiceController.class)
public class EasywakeeApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(EasywakeeApplication.class, args);
	}
}
