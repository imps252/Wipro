package com.healthcare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

	private Long id;

	@NotBlank
	@Size(min = 4, max = 20)
	private String username;

	@NotBlank
	@Size(min = 6, max = 100)
	private String password;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	private boolean active;

	public UserDTO() {
		super();
	}

	public UserDTO(Long id, @NotBlank @Size(min = 4, max = 20) String username,
			@NotBlank @Size(min = 6, max = 100) String password, @NotBlank @Email String email,
			@NotBlank String firstName, @NotBlank String lastName, boolean active) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
