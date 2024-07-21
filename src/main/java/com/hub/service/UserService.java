package com.hub.service;

import com.hub.entity.User;

public interface UserService 
{
	void saveuser(User user);

	boolean emailExists(User user);

	boolean validUser(String email, String password);

	String getRole(String email);

	User getUser(String mail);

	void updateUser(User user);



}
