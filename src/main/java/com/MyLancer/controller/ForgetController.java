package com.MyLancer.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MyLancer.Repository.UserRepository;
import com.MyLancer.models.User;
import com.MyLancer.service.EmailService;

@Controller
public class ForgetController {

	// this is generate random number of varifying otp
	
	Random random = new Random(1000);
	
	// autowire emailservice
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// email id form open handler
	
	@RequestMapping("/forget")
	public String openEmailForm(Model model ) {
		
		model.addAttribute("title", "Forget Page !!");
		return "forgetemail";
	}
	
	
	@PostMapping("/send-otp")
	public String sendotp(@RequestParam String email,HttpSession session)
	{
		System.out.println("email is "+email);
		
		// generation otp 4 digit
		
		
		
		int otp = random.nextInt(999999);
		System.out.println("OTP "+otp);
		
		// write a code for send otp to email
		
		String subject="OTP From Bharat";
		
		// when your are active in html mail the write as follew code
		
		/*String message=""
				       +"<div style='border:1px solid #e2e2e2; padding:20px' >"
				       +"<h1>"
				       +"OTP is"
				       +"<b>"+otp
				       +"</n>"
				       +"</h1>"
				       +"</div>";
		
		*/
		
		String message="OTP is"+otp;
		String to=email;
		
		boolean flag = emailService.sendEmail(subject, message, to);
		
		if(flag)
		{
			// to store temporary save otp
			session.setAttribute("myotp", otp);
			session.setAttribute("email", email);
			
			
			
			
			return "varify_otp";
			
			
		}
		else {
			
			session.setAttribute("message", "Check Your email Id");
			return "forgetemail";
		}
		
	}
	
	

	@PostMapping("/verifyotp")
	public String otpvarify(Model model, @RequestParam int otp,HttpSession session)
	{
		int myOtp=(int)session.getAttribute("myotp");
		
		String email=(String)session.getAttribute("email");
		
		model.addAttribute("title", "varifyOtp");
		
		User user = userRepository.getUserByUserName(email);
		
		if(user==null)
		{
			// send error message
			session.setAttribute("message", "the user does not exit this email");
			return "forgetemail";
			
			
		}else {
			
			// send password form
			
			
		}
		
		
		if(myOtp==otp)
		{
			// password chage form
			return "change_password";
			
		}else
		{
			session.setAttribute("message", "You have Enterd Wrong otp");
			return "varify_otp";
		}
		
}
	
	
	@RequestMapping("/change_password")
	public String changepass(@RequestParam String newPassword,HttpSession session)
	{
		String email=(String)session.getAttribute("email");
		User user = userRepository.getUserByUserName(email);
			user.setPassword(passwordEncoder.encode(newPassword));
			userRepository.save(user);
		
			
			
		return "redirect:/signin?change=password changed sussussfully..";
		
	}
	
	
	
	
	
	
	
}
