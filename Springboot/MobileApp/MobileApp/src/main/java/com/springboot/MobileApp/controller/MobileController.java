package com.springboot.MobileApp.controller;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.springboot.MobileApp.model.MobileModel;
import com.springboot.MobileApp.model.Model;
import com.springboot.MobileApp.service.MobileService;

@RestController
@RequestMapping("/controller")
public class MobileController {

	@Autowired
	private MobileService mobileService;
	
	@PostMapping("/POST")
	public Model savec(@RequestBody Model model) {
		return mobileService.saves(model);
	}
	
	@GetMapping("/GET1/{deviceName}")
    public List<Model> getMobileByModelc(@PathVariable String deviceName) {
        List<Model> mobiles = mobileService.getMobileByDeviceNames(deviceName);
		return mobiles;
    }

	
	@GetMapping("/GET")
	public List<Model> getMobile(){
		return mobileService.getMobileS();
	}
	
	@PutMapping("/Put/{id}")
	public Model updatec(@RequestBody Model model, @PathVariable int id) {
		return mobileService.updates(model, id);
	}
	
	@DeleteMapping("/DELETE/{id}")
	public void dletec(@PathVariable int id) {
		mobileService.deletes(id);
	}
}
