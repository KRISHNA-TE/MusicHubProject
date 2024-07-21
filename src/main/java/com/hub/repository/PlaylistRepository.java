package com.hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hub.entity.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

	public Playlist findByName(String string);

}
