package com.easywakee.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EasywakeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasywakeeApplication.class, args);
	}
	
	@Bean
	public ProviderSignInController providerSignInController() {
	    return new ProviderSignInController(connectionFactoryLocator(), 
	        usersConnectionRepository(), new SimpleSignInAdapter());
	}
}
