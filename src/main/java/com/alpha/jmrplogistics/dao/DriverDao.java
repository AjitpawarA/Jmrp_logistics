package com.alpha.jmrplogistics.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.jmrplogistics.entity.Driver;
import com.alpha.jmrplogistics.repo.DriverRepository;

@Repository
public class DriverDao {

    @Autowired
    private DriverRepository driverRepository;

    public Driver saveDriver(Driver driver) {
        return driverRepository.saveAndFlush(driver);
    }
    
    public Optional<Driver> findById(int id) {
        return driverRepository.findById(id);
    }
    
    public void deleteById(int id) {
        driverRepository.deleteById(id);
    }

	public List<Driver> findAll() {
		// TODO Auto-generated method stub
		return driverRepository.findAll();
	}
}
