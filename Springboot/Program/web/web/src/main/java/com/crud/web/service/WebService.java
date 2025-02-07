package com.crud.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.web.model.Model;
import com.crud.web.repo.WebRepo;

@Service
public class WebService {
	
	@Autowired
	private WebRepo Repo;
	
	public Model show(Model model) {
		return Repo.save(model);
	}
	
	public List<Model> read(){
		return Repo.findAll();
		
	
	}

	public Optional<Model> reads(Long id) {
		return Repo.findById(id);
	}
	
	public Model update(Model model,Long id) {
		Optional<Model> obj1= Repo.findById(id);
		Model obj2=obj1.get();
		obj2.setName(model.getName());
		obj2.setRole(model.getRole());
		
		return Repo.save(obj2);
		
	}
	
	public void erase(Long id) {
		Repo.deleteById(id);
	}
	

}
