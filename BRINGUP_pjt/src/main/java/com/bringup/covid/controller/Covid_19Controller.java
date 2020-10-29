package com.bringup.covid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("servlet")
public class Covid_19Controller {

	@GetMapping("/main")
	public String view() {
		return "index";
	}

	@GetMapping("/food")
	public String food() {
		return "food";
	}
	
	@GetMapping("/global")
	public String global() {
		return "global";
	}
}
