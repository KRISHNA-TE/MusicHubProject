package com.hub.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.entity.User;
import com.hub.repository.UserRepository;
import com.hub.service.UserService;
@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	UserRepository userRepository;

	@Override
	public void saveuser(User user) 
	{
		userRepository.save(user);
	}

	@Override
	public boolean emailExists(User user) {
		
		//Checking User is Present.
		User existinguser = userRepository.findByEmail(user.getEmail());				//Find-by-email is used to get the record from Database.
		
		if(existinguser!= null)
		{
			System.out.println("Present");
			return true;
		}
		else
		{
			System.out.println("Absent");
			return false;
		}
	}

	@Override
	public boolean validUser(String email, String password) 		//For linking 
	{
		User user = userRepository.findByEmail(email);
		
		 String dppwd = user.getPassword();
		 
		 if(password.equals(dppwd))
		 {
			 return true;
		 }
		 else
		 {
			 return false;			 
		 }
	}

	@Override
	public String getRole(String email) 						//For Linking Login Page to the registered role Page(Admin,Customer)
	{
		User user = userRepository.findByEmail(email);
		
		return user.getRole();
				
	}

	@Override
	public User getUser(String mail) 
	{
		return userRepository.findByEmail(mail);
	}

	@Override
	public void updateUser(User user)
	{
		userRepository.save(user);
	}

	

}

	
	
	
	
	

