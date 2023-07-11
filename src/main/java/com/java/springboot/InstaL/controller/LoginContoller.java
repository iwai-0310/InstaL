package com.java.springboot.InstaL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginContoller {
	
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		return "plain-Login";
	}
	//add mapping for 403 access denied-status
	@GetMapping("/access-denied")
	public String showAccessDenied(){
		return "customAccessDeniedPage";
	}

}
