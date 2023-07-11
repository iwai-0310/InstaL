package com.java.springboot.InstaL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	//create a mapping for greetings
	@GetMapping("/hello")
	public String greeting (Model theModel) {
		theModel.addAttribute("theDate",new java.util.Date());
		return "hellomate";
	}
	//create an index page
	@GetMapping("/")
	public String Home() {
		return "index";
	}
	//create a request mapping for leaders
	@GetMapping("/leaders")
	public String showtoleaders() {
		return "leaders";
	}

}
