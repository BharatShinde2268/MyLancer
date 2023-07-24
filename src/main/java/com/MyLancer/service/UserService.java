package com.MyLancer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyLancer.Repository.UserRepository;
import com.MyLancer.models.User;

@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	List<User> userList=new ArrayList<>();
	
	// creating users
	
	public User createUser(User user)
	{
		User savedUsers = userRepository.save(user);
		return savedUsers;
		
	}
	
	
	
	
	
}
