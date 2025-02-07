package com.healthcare.dto;

import jakarta.validation.constraints.NotBlank;

public class DoctorDTO {

	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String specialty;

	public DoctorDTO() {
		super();
	}

	public DoctorDTO(Long id, @NotBlank String name, @NotBlank String specialty) {
		super();
		this.id = id;
		this.name = name;
		this.specialty = specialty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
}
