package com.crud.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Box")
public class Model {
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	public Model() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String Name;
	private String Role;
	
	
	
	

}
