package com.spring.Example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.Example.model.ExampleModel;
import com.spring.Example.repo.ExampleRepo;

@Service
public class ExampleService {
	
	@Autowired
	ExampleRepo exampleRepo;
	
	public ExampleModel Service(ExampleModel exampleModel) {
		
		return exampleRepo.save(exampleModel);
		
//		return exampleModel; 
	}
	
	public List<ExampleModel> Get(){
		return exampleRepo.findAll();
	}
	
	public ExampleModel update (ExampleModel exampleModel, Long id) {
		Optional<ExampleModel> obj1 = exampleRepo.findById(id);
		 
		//Optional<ExampleModel> obj1= exampleRepo.findById(id);
		ExampleModel obj2=obj1.get();
		obj2.setFirstName(exampleModel.getFirstName());
		obj2.setLastName(exampleModel.getLastName());
		obj2.setEmailId(exampleModel.getEmailId());
		return exampleRepo.save(obj2);

		
		
	}
	
	public void Erase (Long id) {
		exampleRepo.deleteById(id);
		
	}

}
