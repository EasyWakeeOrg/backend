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

import com.easywakee.entities.*;


@RestController
public class ServiceController {

	@Autowired
	private UserRepository repo;
	
	//User handling
	@RequestMapping(value="/signUp", method = RequestMethod.POST)
	@RestResource(path="/signUp")

	public String signUp(@RequestParam(value="em",defaultValue="M",required=true) String em,
				@RequestParam(value="fn",defaultValue="M",required=true) String fn,
				@RequestParam(value="ln",defaultValue="M",required=true) String ln,
				@RequestParam(value="time",defaultValue="M",required=true) String time,
				@RequestParam(value="nb",defaultValue="M",required=true) String nb, //User's address
				@RequestParam(value="str",defaultValue="M",required=true) String street,
				@RequestParam(value="pc",defaultValue="M",required=true) String postalCode,
				@RequestParam(value="city",defaultValue="M",required=true) String city,
				@RequestParam(value="tr",defaultValue="",required=true) String transport,
				@RequestParam(value="nbs",defaultValue="M",required=true) String nbS, //fields for school address
				@RequestParam(value="strs",defaultValue="M",required=true) String streetS,
				@RequestParam(value="pcs",defaultValue="M",required=true) String postalCodeS,
				@RequestParam(value="citys",defaultValue="M",required=true) String cityS,
				@RequestParam(value="device",defaultValue="M",required=true) String device){
		try{
			if(repo.findByEmail(em)==null){//insert the new user in the db
				//Parse the address of the user
				String[] pcString = postalCode.split(" ");
				String pcConcat = "";

				if(pcString.length > 1){
					for(int i = 0; i<pcString.length;i++){
						pcConcat.concat(pcString[i]);
					}
				}
				//Ajouter gestion de si le code postal n'est pas present
				else pcConcat = pcString[0];
				int pc = Integer.parseInt(pcConcat);
					
				//Parse the school address
				String[] pcStringS = postalCodeS.split(" ");
				String pcConcatS = "";

				if(pcStringS.length > 1){
					for(int i = 0; i<pcStringS.length-1;i++){
						pcConcatS.concat(pcStringS[i+1]);
					}
				}
				//Gestion si pas de pc en argument?
				else pcConcatS = pcStringS[0];
				int pcS = Integer.parseInt(pcConcatS);
				
				Address add = new Address(Integer.parseInt(nb),street,pc,city);
				Address schoolAdd = new Address(Integer.parseInt(nbS),streetS,pcS,cityS);
				
				//Creer la liste des transports quand on saura comment elle est passée
				ArrayList<String> transports = new ArrayList<String>();
				repo.save(new User(em,fn,ln,Integer.parseInt(time),add,
						transports,schoolAdd,device));
				return "user created";
			}
			else{
				return "email already used";
			}
		}catch(Exception e){
			//renvoyer un code d'erreur HTTP 50x
			return "repository not found";
		}
	}

	@RequestMapping(value="/update", method = RequestMethod.PUT)
	@RestResource(path="/update")
	public String updateUser(@RequestParam(value="fn",defaultValue="") String new_fn,
				@RequestParam(value="ln",defaultValue="") String new_ln,
				@RequestParam(value="em",required =true) String em,
				@RequestParam(value="bio",defaultValue="") String new_bio){
		try{
			if(urepo.findByEmail(em)==null){//check if the user can be updated
				return "user unexistant";
			}
			else{
				User update = urepo.findByEmail(em);
				if(new_fn.trim().length() > 0){
					update.setFirstName(new_fn);
				}
				if(new_ln.trim().length() > 0){
					update.setLastName(new_ln);
				}
				if(new_bio.trim().length() > 0){
					update.setBio(new_bio);
				}
				urepo.save(update);
				return "groups";
			}
		}catch(Exception e){
			return "repository not found";
		}
	}
	
	@RequestMapping(value="/deleteUser")
	@RestResource(path="/deleteUser")
	public String deleteUser(@RequestParam(value="em",required=true) String em){
		try{
			if(repo.findByEmail(em)==null){//check if the user can be deleted
				//return an HTTP error code 50x
				return "user unexistant";
			}
			else{
				repo.deleteUserByEmail(em);
				return "userDeleted";
			}
		}catch(Exception e){
			//return HTTP erro code 50x
			return "repository not found";
		}
	}

	//Function giving the time of the first event of the day on user's agenda
	public Time firstEvent(User u){
		//Google API
		return new Time();
	}
	
	//Computes the route of the user from home to school. Return the time needed
	//to perform the travel
	public int computeRoute(User u){
		//Write code here
		return 0;
	}
	
	//Send the alarm time to the alarm clock device
	public void setAlarmTime(User u){
		//Computes the alarm time
		Time alarmTime = firstEvent(u);
		alarmTime.substract(computeRoute(u) + u.getTime()); 
		
		//Write code to send the info to the device
		//Other possibility: write the info somewhere and the device
		//will send a request to read it
	}
	
	
	
	@RequestMapping(value="/home")
	@RestResource(path="/home")
	public String toHome(){
		return"home";
	}
}