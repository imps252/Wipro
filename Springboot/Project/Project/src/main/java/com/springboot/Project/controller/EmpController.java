package com.springboot.Project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Project.service.EmpService;

@RestController
@RequestMapping("/api")


public class EmpController {
	
	EmpService obj= new EmpService(); // calling obj from empservice
	
	
	@PostMapping("/fetch")
	public String fetchEmployee() {
		String var=obj.Service(); //method from empservice
		return var;
		
	}
	
	@GetMapping("/map")
	public String fetchEmployee1() {
		return "mapping";
	}

}
