package com.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.dto.UserDTO;
import com.healthcare.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping()
	public ResponseEntity<UserDTO> registerUser(@Validated @RequestBody UserDTO userDTO) {
		UserDTO createdUser = userService.registerUser(userDTO);
		return ResponseEntity.ok(createdUser);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUserProfile(@PathVariable Long userId,
			@Validated @RequestBody UserDTO userDTO) {
		UserDTO updatedUser = userService.updateUserProfile(userId, userDTO);
		return ResponseEntity.ok(updatedUser);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserDetails(@PathVariable Long userId) {
		UserDTO userDTO = userService.getUserDetails(userId);
		return ResponseEntity.ok(userDTO);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		Boolean isDeleted = userService.deleteUser(userId);
		if (isDeleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/search")
	public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam String query) {
		List<UserDTO> users = userService.searchUsers(query);
		return ResponseEntity.ok(users);
	}
}
