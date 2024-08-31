package com.alpha.jmrplogistics.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.jmrplogistics.dao.AddressDao;
import com.alpha.jmrplogistics.dao.UserDao;
import com.alpha.jmrplogistics.dto.ResponseStructure;
import com.alpha.jmrplogistics.dto.UserDto;
import com.alpha.jmrplogistics.entity.Address;
import com.alpha.jmrplogistics.entity.User;
import com.alpha.jmrplogistics.exception.AddressNotFoundException;
import com.alpha.jmrplogistics.exception.UserNotFoundException;

import jakarta.validation.Valid;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<Map<String, Object>>> addUser(@Valid UserDto userDto) {
		ResponseStructure<Map<String, Object>> responseStructure = new ResponseStructure<>();

		Address address = addressDao.findById(userDto.getAddressId()).orElseThrow(
				() -> new AddressNotFoundException("Address not found with ID: " + userDto.getAddressId()));

		User userToBeSaved = new User();
		userToBeSaved.setUsername(userDto.getUsername());
		userToBeSaved.setUserpassword(userDto.getUserpassword());
		userToBeSaved.setUserrole(userDto.getUserrole());
		userToBeSaved.setAddress(address);

		User savedUser = userDao.addUser(userToBeSaved);

		Map<String, Object> savedUserData = new HashMap<>();
		savedUserData.put("data", savedUser);

		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("User saved successfully");
		responseStructure.setData(savedUserData);

		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(int id, UserDto userDto) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();

		try {
			User userToUpdate = userDao.getUserById(id)
					.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

			userToUpdate.setUsername(userDto.getUsername());
			userToUpdate.setUserpassword(userDto.getUserpassword());
			userToUpdate.setUserrole(userDto.getUserrole());

			Address address = addressDao.findById(userDto.getAddressId()).orElseThrow(
					() -> new AddressNotFoundException("Address not found with ID: " + userDto.getAddressId()));
			userToUpdate.setAddress(address);

			User updatedUser = userDao.addUser(userToUpdate);

			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("User updated successfully");
			responseStructure.setData(updatedUser);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} catch (UserNotFoundException | AddressNotFoundException e) {
			responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage(e.getMessage());
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseStructure.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			responseStructure.setMessage("Failed to update user: " + e.getMessage());
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<ResponseStructure<User>> getUserById(int id) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();

		try {
			User user = userDao.getUserById(id)
					.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("User found");
			responseStructure.setData(user);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage(e.getMessage());
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<User>>> getAllUsers() {
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
		List<User> users = userDao.getAllUsers();

		responseStructure.setStatuscode(HttpStatus.OK.value());
		responseStructure.setMessage("Users retrieved successfully");
		responseStructure.setData(users);
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteUser(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();

		try {
			User user = userDao.getUserById(id)
					.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

			userDao.deleteUser(id);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("User deleted successfully");
			responseStructure.setData("Deleted user with ID: " + id);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage(e.getMessage());
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseStructure.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			responseStructure.setMessage("Failed to delete user: " + e.getMessage());
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
