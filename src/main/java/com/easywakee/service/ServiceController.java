package com.easywakee.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.easywakee.entities.*;
import com.google.gson.Gson;


@Controller
public class ServiceController {

	@Autowired
	private UserRepository repo;
	
	//User handling
	@RequestMapping(value="/signUp", method = RequestMethod.GET)
	@RestResource(path="/signUp")

	public String signUp(@RequestParam(value="ps",defaultValue="M") String pseudo,
			@RequestParam(value="pa",defaultValue="M") String password,
			@RequestParam(value="em",defaultValue="M") String em,
			@RequestParam(value="time",defaultValue="M") String time,
			@RequestParam(value="nb",defaultValue="M") String nb, //User's address
			@RequestParam(value="str",defaultValue="M") String street,
			@RequestParam(value="pc",defaultValue="M") String postalCode,
			@RequestParam(value="city",defaultValue="M") String city,
			@RequestParam(value="tr",defaultValue="") String transport,
			@RequestParam(value="nbs",defaultValue="M") String nbS, //fields for school address
			@RequestParam(value="strs",defaultValue="M") String streetS,
			@RequestParam(value="pcs",defaultValue="M") String postalCodeS,
			@RequestParam(value="citys",defaultValue="M") String cityS,
			@RequestParam(value="device",defaultValue="M") String device,
			Model model){
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
				
				//Creer la liste des transports quand on saura comment elle est pass�e
				ArrayList<String> transports = new ArrayList<String>(Arrays.asList(transport.split(" ")));
				
				User user = new User(em,pseudo,password,Integer.parseInt(time),add,
						transports,schoolAdd,device);
				repo.save(user);
				Gson gson = new Gson();
				model.addAttribute("user",gson.toJson(user));
				return "homepage";
			}
			else{
				return "already used";
			}
		}catch(Exception e){
			//renvoyer un code d'erreur HTTP 50x
			return "repository not found";
		}
	}

	@RequestMapping(value="/update", method = RequestMethod.GET)
	@RestResource(path="/update")
	public String updateUser(@RequestParam(value="ps",defaultValue="M") String pseudo,
			@RequestParam(value="pa",defaultValue="M") String password,
			@RequestParam(value="em",defaultValue="M") String em,
			@RequestParam(value="time",defaultValue="M") String time,
			@RequestParam(value="nb",defaultValue="M") String nb, //User's address
			@RequestParam(value="str",defaultValue="M") String street,
			@RequestParam(value="pc",defaultValue="M") String postalCode,
			@RequestParam(value="city",defaultValue="M") String city,
			@RequestParam(value="tr",defaultValue="") String transport,
			@RequestParam(value="nbs",defaultValue="M") String nbS, //fields for school address
			@RequestParam(value="strs",defaultValue="M") String streetS,
			@RequestParam(value="pcs",defaultValue="M") String postalCodeS,
			@RequestParam(value="citys",defaultValue="M") String cityS,
			@RequestParam(value="device",defaultValue="M") String device){
		try{
			if(repo.findByEmail(em)==null){//check if the user can be updated
				return "user unexistant";
			}
			else{
				User update = repo.findByEmail(em);
				if(pseudo.trim().length() > 0){
					update.setPseudo(pseudo);
				}
				if(password.trim().length() > 0){
					update.setPassword(password);
				}
				if(time.trim().length() > 0){
					update.setTime(Integer.parseInt(time));
				}
				if(nb.trim().length() > 0 && street.trim().length() > 0 
						&& postalCode.trim().length() > 0 && city.trim().length() > 0){
					update.setAddress(new Address(Integer.parseInt(nb), street,
							Integer.parseInt(postalCode), city));
				}
				if(transport.trim().length() > 0){
					ArrayList<String> t = new ArrayList<String>();
					t.add(transport);
					update.setTransport(t);
				}
				if(nbS.trim().length() > 0 && streetS.trim().length() > 0 
						&& postalCodeS.trim().length() > 0 && cityS.trim().length() > 0){
					update.setSchoolPlace(new Address(Integer.parseInt(nbS), streetS,
							Integer.parseInt(postalCodeS), cityS));
				}
				repo.save(update);
				return "redirect:home";
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
				repo.deleteByEmail(em);
				return "userDeleted";
			}
		}catch(Exception e){
			//return HTTP error code 50x
			return "repository not found";
		}
	}

	//Function giving the time of the first event of the day on user's agenda
	public Date firstEvent(User u){
		//Google API
		return new Date();
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
		Date alarmTime = firstEvent(u);
		//alarmTime.substract(computeRoute(u) + u.getTime());
		
		//Write code to send the info to the device
		//Other possibility: write the info somewhere and the device
		//will send a request to read it
	}
	
//	@RequestMapping(value="/sign")
//	@RestResource(path="/sign")
//	public String toSign(){
//		return"signup";
//	}
	
	@RequestMapping(value="/home")
	@RestResource(path="/home")
	public String toHome(){
		return"homepage";
	}
	
	@RequestMapping(value="/form")
	@RestResource(path="/form")
	public String toForm(){
		return"form";
	}
	
	@RequestMapping("/sign")
    public ModelAndView sign() {
        return new ModelAndView("signup");
    }
}