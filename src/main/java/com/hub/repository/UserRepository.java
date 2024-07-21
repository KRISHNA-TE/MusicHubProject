package com.hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hub.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> 
{
	User findByEmail(String email);

}
