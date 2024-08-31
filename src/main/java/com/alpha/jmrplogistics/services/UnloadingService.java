package com.alpha.jmrplogistics.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alpha.jmrplogistics.dao.AddressDao;
import com.alpha.jmrplogistics.dao.UnloadingDao;
import com.alpha.jmrplogistics.dto.ResponseStructure;
import com.alpha.jmrplogistics.dto.UnloadingDto;
import com.alpha.jmrplogistics.entity.Address;
import com.alpha.jmrplogistics.entity.Unloading;
import com.alpha.jmrplogistics.exception.AddressNotFoundException;
import com.alpha.jmrplogistics.exception.UnloadingNotFoundException;

import jakarta.validation.Valid;

@Service
public class UnloadingService {

	@Autowired
	private UnloadingDao unloadingDao;

	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<Map<String, Object>>> addUnloading(@Valid UnloadingDto unloadingDto,
			String userrole) {
		ResponseStructure<Map<String, Object>> responseStructure = new ResponseStructure<>();
		if (!userrole.equals("ADMIN")) {
			responseStructure.setStatuscode(HttpStatus.FORBIDDEN.value());
			responseStructure.setMessage("You are not an admin. Sorry...");
			responseStructure.setData(null);

			return new ResponseEntity<>(responseStructure, HttpStatus.FORBIDDEN);
		} else {
			Address address = addressDao.findById(unloadingDto.getAddressId()).orElseThrow(
					() -> new AddressNotFoundException("Address not found with ID: " + unloadingDto.getAddressId()));

			Unloading unloadingToBeSaved = new Unloading();
			unloadingToBeSaved.setCompanyName(unloadingDto.getCompanyname());
			unloadingToBeSaved.setUnloadingDate(unloadingDto.getUnloadingdate());
			unloadingToBeSaved.setUnloadingTime(unloadingDto.getUnloadingtime());
			unloadingToBeSaved.setAddress(address);

			Unloading savedUnloading = unloadingDao.saveUnloading(unloadingToBeSaved);

			Map<String, Object> savedUnloadings = new HashMap<>();
			savedUnloadings.put("data", savedUnloading);

			responseStructure.setStatuscode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Unloading saved successfully");
			responseStructure.setData(savedUnloadings);

			return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<ResponseStructure<Unloading>> getUnloadingById(int id) {
		ResponseStructure<Unloading> responseStructure = new ResponseStructure<>();

		Unloading unloading = unloadingDao.findById(id)
				.orElseThrow(() -> new UnloadingNotFoundException("Unloading not found with ID: " + id));

		responseStructure.setStatuscode(HttpStatus.OK.value());
		responseStructure.setMessage("Unloading found");
		responseStructure.setData(unloading);
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteUnloading(int id, String userrole) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();

		if (!userrole.equals("ADMIN")) {
			responseStructure.setStatuscode(HttpStatus.FORBIDDEN.value());
			responseStructure.setMessage("You are not an admin. Sorry...");
			responseStructure.setData(null);

			return new ResponseEntity<>(responseStructure, HttpStatus.FORBIDDEN);
		} else {

			Unloading unloading = unloadingDao.findById(id)
					.orElseThrow(() -> new UnloadingNotFoundException("Unloading not found with ID: " + id));

			unloadingDao.deleteById(id);

			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("Unloading deleted successfully");
			responseStructure.setData("Deleted unloading with ID: " + id);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<List<Unloading>>> getAllUnloadings() {
		ResponseStructure<List<Unloading>> responseStructure = new ResponseStructure<>();
		List<Unloading> unloadings = unloadingDao.findAll();

		responseStructure.setStatuscode(HttpStatus.OK.value());
		responseStructure.setMessage("Unloadings retrieved successfully");
		responseStructure.setData(unloadings);
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
}
