package com.hub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.entity.Song;
import com.hub.repository.SongRepository;
import com.hub.service.SongService;

@Service
public class SongServiceImpl implements SongService
{
	@Autowired
	SongRepository songRepository;

	@Override
	public void savesong(Song song) {
		songRepository.save(song);
	}

	@Override
	public boolean songExists(String name) 
	{
		Song ExistingSong = songRepository.findByName(name);
		
		if(ExistingSong != null)
		{
			System.out.println("Present");
			return true;					
		}
		else {
			System.out.println("Absent");		
			return false;	
		}
	}

	@Override
	public List<Song> fetchAllSongs()
	{
		List<Song> songs = songRepository.findAll();
		return songs;
	}

	@Override
	public void updateSong(Song song) 
	{
		songRepository.save(song);
	}

}
