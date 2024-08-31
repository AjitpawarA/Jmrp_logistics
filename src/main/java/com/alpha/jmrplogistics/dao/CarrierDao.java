package com.alpha.jmrplogistics.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.jmrplogistics.entity.Carrier;
import com.alpha.jmrplogistics.repo.CarrierRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class CarrierDao {

    @Autowired
    private CarrierRepository carrierRepository;

    public Carrier addCarrier(Carrier carrier) {
        return carrierRepository.saveAndFlush(carrier);
    }
    
    public Optional<Carrier> findById(int id) {
        return carrierRepository.findById(id);
    }
    
    public void deleteById(int id) {
        carrierRepository.deleteById(id);
    }

	public List<Carrier> findAll() {
		// TODO Auto-generated method stub
		return carrierRepository.findAll();
	}
}
