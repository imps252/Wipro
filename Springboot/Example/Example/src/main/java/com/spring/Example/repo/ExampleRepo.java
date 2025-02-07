package com.spring.Example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.Example.model.ExampleModel;


@Repository
public interface ExampleRepo extends JpaRepository<ExampleModel, Long>  {
	
	

}
