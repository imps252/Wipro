package com.crud.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.web.model.Model;

public interface WebRepo extends JpaRepository<Model, Long>{

	//Model save(Optional<Model> obj1);

}
