package com.healthcare.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.dto.UserDTO;
import com.healthcare.entity.User;
import com.healthcare.exception.NotFoundException;
import com.healthcare.repo.UserRepository;
import com.healthcare.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		User user = convertToEntity(userDTO);
		user = userRepository.save(user);
		return convertToDTO(user);
	}

	@Override
	public UserDTO updateUserProfile(Long userId, UserDTO userDTO) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setUsername(userDTO.getUsername());
			user.setPassword(userDTO.getPassword());
			user.setEmail(userDTO.getEmail());
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setActive(userDTO.isActive());
			user = userRepository.save(user);
			return convertToDTO(user);
		} else {
			throw new NotFoundException("User not found with id " + userId);
		}
	}

	@Override
	public UserDTO getUserDetails(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			return convertToDTO(optionalUser.get());
		} else {
			throw new NotFoundException("User not found with id " + userId);
		}
	}

	@Override
	public Boolean deleteUser(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			userRepository.deleteById(userId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<UserDTO> searchUsers(String query) {
		List<User> users = userRepository.findByUsernameContainingIgnoreCase(query);
		return users.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setEmail(user.getEmail());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setActive(user.isActive());
		return userDTO;
	}

	private User convertToEntity(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setActive(userDTO.isActive());
		return user;
	}
}
