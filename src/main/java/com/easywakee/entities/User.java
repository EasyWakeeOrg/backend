package com.easywakee.entities;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

public class User {
	
	@Id
	private String email;
	private String firstName;
	private String lastName;
	private int time;//the time param in minutes
	private Address address;
	private ArrayList<String> transport;
	
	public User(){
	}
	
	public User(String fn, String ln, String em){
		firstName=fn;
		lastName=ln;
		email=em;
	}

    @Override
    public String toString() {
        return String.format(
                "User[email='%s', firstName='%s', lastName='%s']",
                email, firstName, lastName);
    }
}
