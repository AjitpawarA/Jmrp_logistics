package com.alpha.jmrplogistics.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.jmrplogistics.dao.AddressDao;
import com.alpha.jmrplogistics.dto.ResponseStructure;
import com.alpha.jmrplogistics.entity.Address;

import jakarta.validation.Valid;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<Map<String, Object>>> addAddress(@Valid Address address) {
	    ResponseStructure<Map<String, Object>> responseStructure = new ResponseStructure<>();

	    Address savedAddress = addressDao.addAddress(address);

	    Map<String, Object> savedAddressData = new HashMap<>();
	    savedAddressData.put("data", savedAddress);

	    responseStructure.setStatuscode(HttpStatus.CREATED.value());
	    responseStructure.setMessage("Address saved successfully");
	    responseStructure.setData(savedAddressData);

	    return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	

	public ResponseEntity<ResponseStructure<Address>> getAddressById(int id) {
		ResponseStructure<Address> responseStructure = new ResponseStructure<>();
		Optional<Address> address = addressDao.findById(id);

		if (address.isPresent()) {
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("Address found");
			responseStructure.setData(address.get());
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Address not found");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteAddress(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		Optional<Address> optionalAddress = addressDao.findById(id);

		if (optionalAddress.isPresent()) {
			addressDao.deleteById(id);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("Address deleted successfully");
			responseStructure.setData("Deleted address with ID: " + id);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Address not found");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Address>>> getAllAddresses() {
		ResponseStructure<List<Address>> responseStructure = new ResponseStructure<>();
		List<Address> addresses = addressDao.findall();

		if (addresses.size() != 0) {
			responseStructure.setMessage("All addresses fetched sucessfully ...");
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setData(addresses);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);

		} else {
			responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Addresses not found");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}
}
