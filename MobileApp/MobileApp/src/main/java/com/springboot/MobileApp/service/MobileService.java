package com.springboot.MobileApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.springboot.MobileApp.model.MobileModel;
import com.springboot.MobileApp.model.Model;
import com.springboot.MobileApp.repo.MobileRepo;

@Service
public class MobileService {

	@Autowired
	private MobileRepo mobileRepo;
	
	public List<Model> getMobileS() {
		// TODO Auto-generated method stub
		return mobileRepo.findAll();
	}

	public Model saves(Model model) {
		// TODO Auto-generated method stub
		return mobileRepo.save(model);
	}

	public Model updates(Model model, int id) {
		// TODO Auto-generated method stub
		Optional<Model> mobile1=mobileRepo.findById(id);
		Model mobile2=mobile1.get();
		mobile2.setDeviceName(model.getDeviceName());
		mobile2.setModel(model.getModel());
		mobile2.setProcessor(model.getProcessor());
		
		return mobileRepo.save(mobile2);
	}

	public void deletes(int id) {
		// TODO Auto-generated method stub
		mobileRepo.deleteById(id);
	}

	public List<Model> getMobileByDeviceNames(String deviceName) {
		return mobileRepo.findByDeviceName(deviceName);
		// TODO Auto-generated method stub
		
	}

}
