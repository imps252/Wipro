package com.springboot.MobileApp.repo;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.springboot.MobileApp.model.MobileModel;
import com.springboot.MobileApp.model.Model;

@Repository
public interface MobileRepo extends JpaRepository<Model, Integer> {
	List<Model> findByDeviceName(String deviceName);
	
}
