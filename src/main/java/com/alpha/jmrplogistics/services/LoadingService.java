package com.alpha.jmrplogistics.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.jmrplogistics.dao.AddressDao;
import com.alpha.jmrplogistics.dao.LoadingDao;
import com.alpha.jmrplogistics.dto.LoadingDto;
import com.alpha.jmrplogistics.dto.ResponseStructure;
import com.alpha.jmrplogistics.entity.Address;
import com.alpha.jmrplogistics.entity.Loading;
import com.alpha.jmrplogistics.exception.AddressNotFoundException;
import com.alpha.jmrplogistics.exception.LoadingNotFoundException;
import com.alpha.jmrplogistics.repo.LoadingRepository;

import jakarta.validation.Valid;

@Service
public class LoadingService {

	@Autowired
	private LoadingRepository loadingRepository;

	@Autowired
	private LoadingDao loadingDao;

	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<Map<String, Object>>> addLoading(@Valid LoadingDto loadingDto,
			String userrole) {
		ResponseStructure<Map<String, Object>> responseStructure = new ResponseStructure<>();
		if (!userrole.equals("ADMIN")) {
			responseStructure.setStatuscode(HttpStatus.FORBIDDEN.value());
			responseStructure.setMessage("You are not an admin. Sorry...");
			responseStructure.setData(null);

			return new ResponseEntity<>(responseStructure, HttpStatus.FORBIDDEN);
		} else {
			Address address = addressDao.findById(loadingDto.getAddressId()).orElseThrow(
					() -> new AddressNotFoundException("Address not found with ID: " + loadingDto.getAddressId()));

			Loading loadingToBeSaved = new Loading();
			loadingToBeSaved.setCompanyName(loadingDto.getCompanyname());
			loadingToBeSaved.setLoadingDate(loadingDto.getLoadingdate());
			loadingToBeSaved.setLoadingTime(loadingDto.getLoadingtime());
			loadingToBeSaved.setAddress(address);

			Loading savedLoading = loadingDao.addLoading(loadingToBeSaved);

			Map<String, Object> savedLoadings = new HashMap<>();
			savedLoadings.put("data", savedLoading);

			responseStructure.setStatuscode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Loading saved successfully");
			responseStructure.setData(savedLoadings);

			return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
		}
	}

	// Get a loading entity by ID
	public ResponseEntity<ResponseStructure<Loading>> getLoadingById(int id) {
		ResponseStructure<Loading> responseStructure = new ResponseStructure<>();

		Loading loading = loadingDao.findById(id)
				.orElseThrow(() -> new LoadingNotFoundException("Loading not found with ID: " + id));

		responseStructure.setStatuscode(HttpStatus.OK.value());
		responseStructure.setMessage("Loading found");
		responseStructure.setData(loading);
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	// Delete a loading entity by ID
	public ResponseEntity<ResponseStructure<String>> deleteLoading(int id, String userrole) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		if (!userrole.equals("ADMIN")) {
			responseStructure.setStatuscode(HttpStatus.FORBIDDEN.value());
			responseStructure.setMessage("You are not an admin. Sorry...");
			responseStructure.setData(null);

			return new ResponseEntity<>(responseStructure, HttpStatus.FORBIDDEN);
		} else {

			Loading loading = loadingDao.findById(id)
					.orElseThrow(() -> new LoadingNotFoundException("Loading not found with ID: " + id));
			loadingRepository.deleteById(id);

			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("Loading deleted successfully");
			responseStructure.setData("Deleted successfully");
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<List<Loading>>> getAllLoadings() {
		ResponseStructure<List<Loading>> responseStructure = new ResponseStructure<>();
		List<Loading> loadings = loadingDao.findall();

		responseStructure.setStatuscode(HttpStatus.OK.value());
		responseStructure.setMessage("Loadings retrieved successfully");
		responseStructure.setData(loadings);
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
}
