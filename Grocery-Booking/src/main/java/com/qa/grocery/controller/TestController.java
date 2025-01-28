package com.qa.grocery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/get")
public class TestController {
	
	@GetMapping("/string")
	public String getString() {
		return "Akash";
	}

}
