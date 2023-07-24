package com.MyLancer.controller;

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
public class HomeController {
	
	


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@RequestMapping("/login")
	public String home(Model model)
	{
		model.addAttribute("title", "Sign In");
		return "login";
		
	}
	
	
	@RequestMapping("/signup")
	public String register(Model model)
	{
		model.addAttribute("title", "Register Page");
		model.addAttribute("user", new User());
		return "other";
	}
	
	
	@PostMapping("/doRegister")
	public String formReister(@Valid @ModelAttribute("user") User user,BindingResult result1  ,@RequestParam(value = "agreement",defaultValue = "false") boolean agreement,Model model,HttpSession session)
	{
		
		try {
			
			if(!agreement)
			{
				System.out.println("Your not agreed the terms and conditions");
				throw new Exception("Your not agreed the terms and conditions"); // hi method aahe try chi jevha agreement unchek krta tya veles ha error yeil 
				
			}
			
			
			if(result1.hasErrors())
			{
				System.out.println("Error"+result1.toString());
				model.addAttribute("user", user);
				return "other";
				
			}
			
			
			user.setRole("ROLE_USER");
			user.setImageUrl("default.png");
			user.setEnabled(true);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			
			
			System.out.println("Agreement"+agreement);
			System.out.println("User"+user);
			
			
			User result = this.userRepository.save(user);
			
			model.addAttribute("user", new User());
			
			session.setAttribute("message", new Message("Successfully Registerd", "alert-success"));
			
			//model.addAttribute("user", result);  // jo data get jhala toch data prt tyach page vr yeto
			
			return "other";
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Someting Went To Wrong!!"+e.getMessage(), "alert-danger"));
			
			return "other";
		}
		
		
		
		
	}
	
	
	@RequestMapping("/")
	public String home1(Model model)
	{
		model.addAttribute("title", "MyLancers !!");
		return "home";
	}
	
	@RequestMapping("/service")
	public String services()
	{
		
		
		return "services";
	}
	
	
	// hander lor custom login ..
	@GetMapping("/signin")
	public String customLogin(Model model)
	{
		model.addAttribute("title", "Login Page");
		return "login";
	}
	
	
	

}
