package com.alpha.jmrplogistics.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.jmrplogistics.entity.Truck;
import com.alpha.jmrplogistics.repo.TruckRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class TruckDao {

    @Autowired
    private TruckRepository truckRepository;

    public Truck addTruck(Truck truck) {
        return truckRepository.saveAndFlush(truck);
    }
    
    public Optional<Truck> findById(int id) {
        return truckRepository.findById(id);
    }
    
    public void deleteById(int id) {
        truckRepository.deleteById(id);
    }

	public List<Truck> findAll() {
		// TODO Auto-generated method stub
		return truckRepository.findAll();
	}
}
