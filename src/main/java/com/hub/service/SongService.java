package com.hub.service;

import java.util.List;

import com.hub.entity.Song;

public interface SongService 
{

	void savesong(Song song);

	boolean songExists(String name);

	List<Song> fetchAllSongs();

	void updateSong(Song song);


}
