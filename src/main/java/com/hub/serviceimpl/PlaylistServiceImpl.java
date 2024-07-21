package com.hub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.entity.Playlist;
import com.hub.repository.PlaylistRepository;
import com.hub.service.PlaylistService;

@Service
public class PlaylistServiceImpl implements PlaylistService
{
	@Autowired
	PlaylistRepository playlistRepository;

	@Override
	public void addplaylist(Playlist playlist) {
		Playlist ExistingPlaylist = playlistRepository.findByName(playlist.getName());
		
		if(ExistingPlaylist == null)
		{
			playlistRepository.save(playlist);		
		}
		else {
			System.out.println("Playlist Already Exists");
		}
		
	}

	public List<Playlist> fetchAllPlaylist() {
		List<Playlist> playlist = playlistRepository.findAll();
		return playlist;
	}
	
	
}
