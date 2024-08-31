package com.alpha.jmrplogistics.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.jmrplogistics.dao.CarrierDao;
import com.alpha.jmrplogistics.dao.DriverDao;
import com.alpha.jmrplogistics.dao.TruckDao;
import com.alpha.jmrplogistics.dto.DriverDto;
import com.alpha.jmrplogistics.dto.ResponseStructure;
import com.alpha.jmrplogistics.entity.Carrier;
import com.alpha.jmrplogistics.entity.Driver;
import com.alpha.jmrplogistics.entity.Truck;
import com.alpha.jmrplogistics.exception.CarrierNotFoundException;
import com.alpha.jmrplogistics.exception.TruckNotFoundException;

import jakarta.validation.Valid;

@Service
public class DriverService {

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private TruckDao truckDao;

    @Autowired
    private CarrierDao carrierDao;

    public ResponseEntity<ResponseStructure<Map<String, Object>>> addDriver(@Valid DriverDto driverDto) {
        ResponseStructure<Map<String, Object>> responseStructure = new ResponseStructure<>();
        Map<String, String> errorMessage = validateDriver(driverDto);

        if (!errorMessage.isEmpty()) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("errors", errorMessage);

            responseStructure.setStatuscode(HttpStatus.BAD_REQUEST.value());
            responseStructure.setMessage("Invalid Inputs");
            responseStructure.setData(errors);

            return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
        }

        Truck truck = truckDao.findById(driverDto.getTruckId())
                .orElseThrow(() -> new TruckNotFoundException("Truck not found with ID: " + driverDto.getTruckId()));

        Carrier carrier = carrierDao.findById(driverDto.getCarrierId())
                .orElseThrow(() -> new CarrierNotFoundException("Carrier not found with ID: " + driverDto.getCarrierId()));

        Driver driverToBeSaved = new Driver();
        driverToBeSaved.setDriverName(driverDto.getDrivername());
        driverToBeSaved.setDriverPhoneNumber(driverDto.getDriverphonenumber());
        driverToBeSaved.setTruck(truck);
        driverToBeSaved.setCarrier(carrier);

        Driver savedDriver = driverDao.saveDriver(driverToBeSaved);

        
        Map<String, Object> savedDriverData = new HashMap<>();
        savedDriverData.put("data", savedDriver);

        responseStructure.setStatuscode(HttpStatus.CREATED.value());
        responseStructure.setMessage("Driver added successfully");
        responseStructure.setData(savedDriverData);

        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
    }

    private Map<String, String> validateDriver(DriverDto driverDto) {
        Map<String, String> errorMessage = new HashMap<>();
        if (driverDto.getDrivername() == null || driverDto.getDrivername().isEmpty()) {
            errorMessage.put("driverName", "Driver name should not be null or empty");
        }
        if (driverDto.getDriverphonenumber() == null || driverDto.getDriverphonenumber().isEmpty()) {
            errorMessage.put("driverPhoneNumber", "Driver phone number should not be null or empty");
        }
        return errorMessage;
    }

    public ResponseEntity<ResponseStructure<Driver>> updateDriver(int id, Driver newDriver) {
        ResponseStructure<Driver> responseStructure = new ResponseStructure<>();
        return driverDao.findById(id).map(driver -> {
            if (newDriver.getDriverName() != null) driver.setDriverName(newDriver.getDriverName());
            if (newDriver.getCarrier() != null) driver.setCarrier(newDriver.getCarrier());
            if (newDriver.getDriverPhoneNumber() != null) driver.setDriverPhoneNumber(newDriver.getDriverPhoneNumber());
            if (newDriver.getTruck() != null) driver.setTruck(newDriver.getTruck());

            Driver updatedDriver = driverDao.saveDriver(driver);
            responseStructure.setStatuscode(HttpStatus.OK.value());
            responseStructure.setMessage("Driver info updated successfully");
            responseStructure.setData(updatedDriver);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }).orElseGet(() -> {
            responseStructure.setStatuscode(HttpStatus.BAD_REQUEST.value());
            responseStructure.setMessage("Driver info not updated");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
        });
    }

    public ResponseEntity<ResponseStructure<Driver>> getDriverById(int id) {
        ResponseStructure<Driver> responseStructure = new ResponseStructure<>();
        return driverDao.findById(id).map(driver -> {
            responseStructure.setStatuscode(HttpStatus.OK.value());
            responseStructure.setMessage("Driver found");
            responseStructure.setData(driver);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }).orElseGet(() -> {
            responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Driver not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        });
    }

    public ResponseEntity<ResponseStructure<List<Driver>>> getAllDrivers() {
        ResponseStructure<List<Driver>> responseStructure = new ResponseStructure<>();
        List<Driver> drivers = driverDao.findAll();
        if (drivers != null && !drivers.isEmpty()) {
            responseStructure.setStatuscode(HttpStatus.OK.value());
            responseStructure.setMessage("Drivers retrieved successfully");
            responseStructure.setData(drivers);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
            responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("No drivers found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ResponseStructure<String>> deleteDriver(int id) {
        ResponseStructure<String> responseStructure = new ResponseStructure<>();
        return driverDao.findById(id).map(driver -> {
            driverDao.deleteById(id);
            responseStructure.setStatuscode(HttpStatus.OK.value());
            responseStructure.setMessage("Driver deleted successfully");
            responseStructure.setData("Driver with ID " + id + " deleted");
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }).orElseGet(() -> {
            responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Driver not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        });
    }
}
