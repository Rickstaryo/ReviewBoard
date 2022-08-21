package com.example.file.Controller;

import org.springframework.stereotype.Controller;

@Controller
public class MainController {
	
	public String home() {
		
		return "/index";
	}
}
