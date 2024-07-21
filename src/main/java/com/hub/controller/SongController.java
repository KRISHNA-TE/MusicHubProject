package com.hub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hub.entity.Song;
import com.hub.service.SongService;

//@CrossOrigin("*")
@Controller

public class SongController 
{
	@Autowired
	SongService songService;
	
	@PostMapping("/addsongs")
	@ResponseBody
	public String addsong(@ModelAttribute Song song)
	{
		//To check a song with a name is present or not
		String name = song.getName();
		
		boolean songExists = songService.songExists(name);
		
		if(songExists == false)
		{
			songService.savesong(song);
			System.out.println("Song Added Successfully");
		}
		else
		{
			System.out.println("Duplicate Song");
		}
		return "adminhome";
	}
	
	@GetMapping("/playsongs")
	public String playsongs(Model model)
	{
		boolean premium = true;
		if(premium)
		{
			List<Song> songslist = songService.fetchAllSongs();
			model.addAttribute("songs", songslist);
			System.out.println(songslist);
			return "viewsongs";			
		}
		else {
			return "pay";		//EndPoint of the linking HTML Page by using Class Name:
		}
	}
	@GetMapping("/viewsongs")
	public String viewsongs(Model model)
	{		
			List<Song> songslist = songService.fetchAllSongs();
			model.addAttribute("songs", songslist);
			System.out.println(songslist);
			return "viewsongs";
	}
//	@GetMapping("/viewsongs")
//	public @ResponseBody List<Song> viewsongs(Model model)
//	{			
//		return songService.fetchAllSongs();
//	}
//	
	

}
