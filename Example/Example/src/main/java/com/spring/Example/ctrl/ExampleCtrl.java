package com.spring.Example.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//package com.spring.Example.ctrl;

//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.service.annotation.PutExchange;

import com.spring.Example.model.ExampleModel;
import com.spring.Example.service.ExampleService;

@RestController
@RequestMapping("/api")
public class ExampleCtrl {
	
	@Autowired
	ExampleService exampleService;
	
	@PostMapping("post")
	public ExampleModel Control(@RequestBody ExampleModel exampleModel) {
		
		return exampleService.Service(exampleModel);
//		return exampleModel;
	}
	
	@GetMapping("/get")
	public List<ExampleModel> Fetch(){
		return exampleService.Get();
	}
	
	@PutMapping("/put/{id}")
	public ExampleModel Put(@RequestBody ExampleModel exampleModel, @PathVariable Long id){
		return exampleService.update(exampleModel, id);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		exampleService.Erase(id);
		
	}
	
	
	

}
