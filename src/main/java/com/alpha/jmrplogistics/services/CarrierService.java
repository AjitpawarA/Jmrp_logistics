package com.alpha.jmrplogistics.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.jmrplogistics.dao.CarrierDao;
import com.alpha.jmrplogistics.dto.ResponseStructure;
import com.alpha.jmrplogistics.entity.Carrier;
import com.alpha.jmrplogistics.exception.CarrierNotFoundException;

import jakarta.validation.Valid;

@Service
public class CarrierService {

	@Autowired
	private CarrierDao carrierDao;

	public ResponseEntity<ResponseStructure<Carrier>> addCarrier(@Valid Carrier carrier, String userrole) {
		ResponseStructure<Carrier> responseStructure = new ResponseStructure<>();
		if (!userrole.equals("ADMIN")) {
			responseStructure.setStatuscode(HttpStatus.FORBIDDEN.value());
			responseStructure.setMessage("You are not an admin. I am Sorry...");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.FORBIDDEN);
		} else {
			Carrier savedCarrier = carrierDao.addCarrier(carrier);
			if (savedCarrier != null) {
				responseStructure.setStatuscode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Carrier saved successfully.");
				responseStructure.setData(savedCarrier);
				return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
			} else {
				responseStructure.setStatuscode(HttpStatus.BAD_REQUEST.value());
				responseStructure.setMessage("Carrier could not be saved.");
				responseStructure.setData(null);
				return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
			}
		}
	}

	public ResponseEntity<ResponseStructure<Carrier>> getCarrierById(int id) {
		ResponseStructure<Carrier> responseStructure = new ResponseStructure<>();
		Optional<Carrier> carrier = Optional.of(carrierDao.findById(id)
				.orElseThrow(() -> new CarrierNotFoundException("CArrier not found with id :" + id)));

		responseStructure.setStatuscode(HttpStatus.OK.value());
		responseStructure.setMessage("Carrier found");
		responseStructure.setData(carrier.get());
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteCarrier(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		Optional<Carrier> optionalCarrier = Optional.of(carrierDao.findById(id)
				.orElseThrow(() -> new CarrierNotFoundException("Carrier not found for id :" + id)));
		carrierDao.deleteById(id);
		responseStructure.setStatuscode(HttpStatus.OK.value());
		responseStructure.setMessage("Carrier deleted successfully");
		responseStructure.setData("Deleted carrier with ID: " + id);
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Carrier>>> getAllCarriers() {
		ResponseStructure<List<Carrier>> responseStructure = new ResponseStructure<>();
		List<Carrier> carriers = carrierDao.findAll();

		responseStructure.setStatuscode(HttpStatus.OK.value());
		responseStructure.setMessage("Carriers retrieved successfully");
		responseStructure.setData(carriers);
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
}
