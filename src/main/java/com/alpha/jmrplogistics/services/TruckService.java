package com.alpha.jmrplogistics.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.jmrplogistics.dao.TruckDao;
import com.alpha.jmrplogistics.dto.ResponseStructure;
import com.alpha.jmrplogistics.entity.Truck;
import com.alpha.jmrplogistics.exception.TruckAdditionException;
import com.alpha.jmrplogistics.exception.TruckNotFoundException;

import jakarta.validation.Valid;

@Service
public class TruckService {

	@Autowired
	private TruckDao truckDao;

	public ResponseEntity<ResponseStructure<Map<String, Object>>> addTruck(@Valid Truck truck, String userrole) {
	    ResponseStructure<Map<String, Object>> responseStructure = new ResponseStructure<>();
	    if (!userrole.equals("ADMIN")) {
	        responseStructure.setStatuscode(HttpStatus.FORBIDDEN.value());
	        responseStructure.setMessage("You are not an admin. Sorry...");
	        responseStructure.setData(null);

	        return new ResponseEntity<>(responseStructure, HttpStatus.FORBIDDEN);
	    } else {
	        Truck savedTruck = truckDao.addTruck(truck);

	        if (savedTruck == null) {
	            throw new TruckAdditionException("Failed to add truck");
	        }

	        Map<String, Object> savedTruckData = new HashMap<>();
	        savedTruckData.put("data", savedTruck);

	        responseStructure.setStatuscode(HttpStatus.CREATED.value());
	        responseStructure.setMessage("Truck added successfully");
	        responseStructure.setData(savedTruckData);

	        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	    }
	}


	public ResponseEntity<ResponseStructure<Truck>> getTruckById(int id) {
		ResponseStructure<Truck> responseStructure = new ResponseStructure<>();

		Truck truck = truckDao.findById(id)
				.orElseThrow(() -> new TruckNotFoundException("Truck not found with ID: " + id));

		responseStructure.setStatuscode(HttpStatus.OK.value());
		responseStructure.setMessage("Truck found");
		responseStructure.setData(truck);
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteTruck(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();

		Truck truck = truckDao.findById(id)
				.orElseThrow(() -> new TruckNotFoundException("Truck not found with ID: " + id));

		truckDao.deleteById(id);

		responseStructure.setStatuscode(HttpStatus.OK.value());
		responseStructure.setMessage("Truck deleted successfully");
		responseStructure.setData("Deleted truck with ID: " + id);
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Truck>>> getAllTrucks() {
		ResponseStructure<List<Truck>> responseStructure = new ResponseStructure<>();
		List<Truck> trucks = truckDao.findAll();

		responseStructure.setStatuscode(HttpStatus.OK.value());
		responseStructure.setMessage("Trucks retrieved successfully");
		responseStructure.setData(trucks);
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
}
