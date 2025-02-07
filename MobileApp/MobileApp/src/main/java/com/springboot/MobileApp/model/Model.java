package com.springboot.MobileApp.model;

//package com.springboot.MobileApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Mobile")
public class Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "device_name")
	private String deviceName;

	@Column(name = "mobile_model")
	private String model;
	
	private String processor;
	
	public Model() {
		
	}

	public Model(int version, String deviceName, String model, String processor) {
		super();
		this.id = version;
		this.deviceName = deviceName;
		this.model = model;
		this.processor = processor;
	}

	public int getVersion() {
		return id;
	}

	public void setVersion(int version) {
		this.id = version;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}
	
}
