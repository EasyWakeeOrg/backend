package com.easywakee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easywakee.entities.User;

@RestController

public class ServiceController {

	@Autowired
	private UserRepository urepo;
	
	
	//User handling
	@RequestMapping(value="/signUp", method = RequestMethod.GET)
	@RestResource(path="/signUp")
	public String signUp(@RequestParam(value="fn",defaultValue="M") String fn,
				@RequestParam(value="ln",defaultValue="M") String ln,
				@RequestParam(value="em",defaultValue="M") String em){
		try{
			if(urepo.findByEmail(em)==null){//insert the new user in the db
				urepo.save(new User(fn,ln,em));
				return "user created";
			}
			else{
				return "email already used";
			}
		}catch(Exception e){
			return "repository not found";
		}
	}
}