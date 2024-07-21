package com.hub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hub.entity.Playlist;
import com.hub.entity.Song;
import com.hub.service.PlaylistService;
import com.hub.service.SongService;

//@CrossOrigin("*")
@Controller
public class PlaylistController 
{
	@Autowired
	PlaylistService playlistService;
	
	@Autowired
	SongService songService;
	
	@GetMapping("/createplaylists")
	public String createplaylist(Model model)
	{
		List<Song> songList = songService.fetchAllSongs();
		model.addAttribute("songs",songList);
		return "createplaylist";
	}
	
	@PostMapping("/addplaylist")
	public String addplaylist ( @ModelAttribute Playlist playlist) {
		
		playlistService.addplaylist(playlist);
		
		//updating the song_playlists table
		  List<Song> songs = playlist.getSongs();
		
		for (Song song : songs) {
			song.getPlaylists().add(playlist);
			songService.updateSong(song);
		}
		
		return "adminhome";
	}
	
	
	@GetMapping("/viewplaylist")
	public String viewPlaylist(Model model)
	{
			List<Playlist> playlists = playlistService.fetchAllPlaylist();
			model.addAttribute("playlist",playlists);
			return "viewplaylist";			
		
	}
	

	
}
