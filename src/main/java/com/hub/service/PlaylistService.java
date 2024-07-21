package com.hub.service;

import java.util.List;

import com.hub.entity.Playlist;

public interface PlaylistService 
{

	public void addplaylist(Playlist playlist);

	public List<Playlist> fetchAllPlaylist();




}
