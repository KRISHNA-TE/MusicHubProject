package com.hub.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hub.entity.Song;
import com.hub.entity.User;
import com.hub.service.SongService;
import com.hub.service.UserService;

import jakarta.servlet.http.HttpSession;

//@CrossOrigin("*")
@Controller
public class UserController 
{
	@Autowired
	UserService userService;
	
	@Autowired
	SongService songService;
	
	//Registration_Page
	@PostMapping("/register")
	public String adduser( @ModelAttribute User user)
	{	
		//To Check a user with a Email is present
		boolean userExists = userService.emailExists(user);
		
		if(userExists==false)
		{
			userService.saveuser(user);
			System.out.println("User Added Sucessfully");
		}
		else
		{
			System.out.println("Duplicate User");
		}
		return "login";
	}
	
	//Login_Page
	@PostMapping("/validate")
	public String validate( @RequestParam ("email")String email,@RequestParam("password") String password, HttpSession session,Model model)
	{				
		if(userService.validUser(email,password)==true)
		{
			
			session.setAttribute("email", email);
			
			String role = userService.getRole(email);
			
			if(role.equals("admin"))
			{
				return "adminhome";
			}
			else
			{
				User user = userService.getUser(email);
				boolean userstatus = user.isPremium();
				
				List<Song> fetchAllSongs = songService.fetchAllSongs();
				model.addAttribute("songs",fetchAllSongs);
				
				model.addAttribute("ispremium", userstatus);
				return "customerhome";
			}
		}
		else
		{
			return "login";
		}		
	}
//	@PostMapping("/validate")
//	public String validate(@RequestBody LoginData logindata, HttpSession session,Model model)
//	{			
//		System.out.println(logindata +"Success");
//		if(userService.validUser(logindata.getEmail(),logindata.getPassword())==true)
//		{
//			System.out.println(logindata +"success");
//			session.setAttribute("email",logindata.getEmail());
//			
//			String role = userService.getRole(logindata.getEmail());
//			
//			if(role.equals("admin"))
//			{	
//				return "adminhome";
//			}
//			else
//			{
//				User user = userService.getUser(logindata.getEmail());
//				boolean userstatus = user.isPremium();
//				
//				List<Song> fetchAllSongs = songService.fetchAllSongs();
//				model.addAttribute("songs",fetchAllSongs);
//				
//				model.addAttribute("ispremium", userstatus);
//				return "customerhome";
//			}
//		}
//		else
//		{
//			return "login";
//		}		
//	}

	@GetMapping("/logout") 
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "login";
	}
		
}
