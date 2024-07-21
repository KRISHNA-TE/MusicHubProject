package com.hub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@CrossOrigin("*")
@Controller
public class NavController
{
	@GetMapping("/register")
	public String registration()
	{
		return "Registration";			//HTML File 
	}
	@GetMapping("/login")
	public String login()
	{
		return "Login";					//Navigates to the HTML File
	}
	@GetMapping("/addsong")
	public String song() {
		return "song";					//Navigate to the HTML File
	}
	
}
