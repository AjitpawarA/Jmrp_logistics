package com.alpha.jmrplogistics.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.jmrplogistics.dao.CargoDao;
import com.alpha.jmrplogistics.dto.ResponseStructure;
import com.alpha.jmrplogistics.entity.Cargo;

import jakarta.validation.Valid;

@Service
public class CargoService {

    @Autowired
    private CargoDao cargoDao;
    
    
    
    private Map<String, String> validateCargo(Cargo cargo) {
        Map<String, String> errorMessage = new HashMap<>();
        if (cargo.getCargoName() == null || cargo.getCargoName().isEmpty()) {
            errorMessage.put("cargoName", "Cargo name should not be null or empty");
        }
        if (cargo.getCargoDescription() == null || cargo.getCargoDescription().isEmpty()) {
            errorMessage.put("cargoDescription", "Cargo description should not be null or empty");
        }
        if (cargo.getCargoWeight() <= 0) {
            errorMessage.put("cargoWeight", "Cargo weight should be greater than zero");
        }
        if (cargo.getCargoCount() <= 0) {
            errorMessage.put("cargoCount", "Cargo count should be greater than zero");
        }
        return errorMessage;
    }


    public ResponseEntity<ResponseStructure<Map<String, Object>>> addCargo(@Valid Cargo cargo, String userrole) {
	    ResponseStructure<Map<String, Object>> responseStructure = new ResponseStructure<>();
	    if (!userrole.equals("ADMIN")) {
	        responseStructure.setStatuscode(HttpStatus.FORBIDDEN.value());
	        responseStructure.setMessage("You are not an admin. Sorry...");
	        responseStructure.setData(null);

	        return new ResponseEntity<>(responseStructure, HttpStatus.FORBIDDEN);
	    } else {
        Cargo savedCargo = cargoDao.addCargo(cargo);

        Map<String, Object> savedCargoData = new HashMap<>();
        savedCargoData.put("data", savedCargo);

        if (savedCargo != null) {
            responseStructure.setStatuscode(HttpStatus.CREATED.value());
            responseStructure.setMessage("Cargo added successfully");
            responseStructure.setData(savedCargoData);
            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
        } else {
            responseStructure.setStatuscode(HttpStatus.BAD_REQUEST.value());
            responseStructure.setMessage("Failed to add cargo");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
        }}
    }

    public ResponseEntity<ResponseStructure<Cargo>> getCargoById(int id) {
        ResponseStructure<Cargo> responseStructure = new ResponseStructure<>();
        Optional<Cargo> cargo = cargoDao.findById(id);

        if (cargo.isPresent()) {
            responseStructure.setStatuscode(HttpStatus.OK.value());
            responseStructure.setMessage("Cargo found");
            responseStructure.setData(cargo.get());
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
            responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Cargo not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ResponseStructure<String>> deleteCargo(int id) {
        ResponseStructure<String> responseStructure = new ResponseStructure<>();
        Optional<Cargo> optionalCargo = cargoDao.findById(id);

        if (optionalCargo.isPresent()) {
            cargoDao.deleteById(id);
            responseStructure.setStatuscode(HttpStatus.OK.value());
            responseStructure.setMessage("Cargo deleted successfully");
            responseStructure.setData("Deleted cargo with ID: " + id);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
            responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Cargo not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ResponseStructure<List<Cargo>>> getAllCargo() {
        ResponseStructure<List<Cargo>> responseStructure = new ResponseStructure<>();
        List<Cargo> cargoList = cargoDao.findAll(); // Ensure your DAO has a findAll method

        responseStructure.setStatuscode(HttpStatus.OK.value());
        responseStructure.setMessage("Cargo list retrieved successfully");
        responseStructure.setData(cargoList);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }
}
