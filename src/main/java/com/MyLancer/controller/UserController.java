package com.MyLancer.controller;


import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MyLancer.Repository.UserRepository;
import com.MyLancer.helper.Message;
import com.MyLancer.models.User;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) 
	{
		String username = principal.getName();
		System.out.println("username Is=: "+username);
		
		// get the user using username(email)
		
		User user = userRepository.getUserByUserName(username);
		
		System.out.println("user all data print "+user);
		
		model.addAttribute("title", "Dashboard");
		model.addAttribute("user", user);
		return "normal/userDashboard";
		
	}
	
	// your profile handler
	
	@RequestMapping("/prodash")
	public String proDash(Model model, Principal principal )
	{
		String userName = principal.getName();
		
		User user = userRepository.getUserByUserName(userName);
		
		model.addAttribute("title", "ProfilePage");
		model.addAttribute("user", user);
		
		return "normal/profile";
		
	}
	
	
	// change password module 
	@RequestMapping("/changepassword")
	public String changepass(Model model, Principal principal)
	{
		String userName = principal.getName(); // 1
		User user = userRepository.getUserByUserName(userName); // 2
		
		
		model.addAttribute("title", "Change Password");
		model.addAttribute("user", user); // 3  1+2+3 he jr user asel tr pratek handler mdhe hi 3 kame krave lagtilch nahi tr handler error yeil
		return "normal/changepassword";
	}
	
	@PostMapping("/changepass")
	public String changepass(Model model,Principal principal,
			@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword
			,HttpSession session)
	{
		System.out.println("Old Password is=:"+oldPassword);
		System.out.println("New Password is="+newPassword);
		
		String userName = principal.getName();  // je login aahe tyacha email id
		User user = userRepository.getUserByUserName(userName);
		model.addAttribute("user", user);
		
		// change password
		
		if(passwordEncoder.matches(oldPassword, user.getPassword()))
		{
			// change password
			
			user.setPassword(passwordEncoder.encode(newPassword));
			userRepository.save(user);
			session.setAttribute("message", new Message("your password successfull updated", "success"));
		}
		else {
			
			// error
			session.setAttribute("message", new Message("enter your corred password", "danger"));
			return "normal/changepassword";
		}
		
		
		
		return "normal/userDashboard";
		
	}
	
	
	
	
	
	
	
}
