package com.MyLancer.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.MyLancer.Repository.UserRepository;
import com.MyLancer.confige.CustomeUserDetails;
import com.MyLancer.models.User;

public class UserDetailserviceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// fetching user from database 
		
		User user = userRepository.getUserByUserName(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("Could Not Found User !!");
		}
		
		CustomeUserDetails customeUserDetails = new CustomeUserDetails(user);
		
		
		return customeUserDetails;
	}

}
