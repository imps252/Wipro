package com.crud.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.web.model.Model;
import com.crud.web.service.WebService;

@RestController
@RequestMapping("/web")
public class WebCtrl {
	
	@Autowired
	private WebService service;  
	
	@GetMapping("Mass")
	public String Get() {
		return "Mass";
	}
	
	@PostMapping("Post")
	public Model create(@RequestBody Model model) {
		return service.show(model);
	}
	
	@GetMapping("Get")
	public List<Model> see(){
		return service.read();
	}
	
	@GetMapping("/Get/{id}")
	public Optional<Model> seeu(@PathVariable Long id) {
		return service.reads(id);
		
	}
	
	@PutMapping("/Put/{id}")
	public Model change(@RequestBody Model model,@PathVariable Long id) {
		return service.update(model, id);
	}
	
	@DeleteMapping("/Delete/{id}")
	public void del(@PathVariable Long id) {
		
		service.erase(id);
	}
	
	

}
