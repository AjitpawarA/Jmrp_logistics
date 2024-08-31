package com.alpha.jmrplogistics.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.jmrplogistics.entity.Cargo;
import com.alpha.jmrplogistics.repo.CargoRepository;

@Repository
public class CargoDao {

    @Autowired
    private CargoRepository cargoRepository;

    // Add a new Cargo or update an existing one
    public Cargo addCargo(Cargo cargo) {
        return cargoRepository.saveAndFlush(cargo);
    }
    
    // Find Cargo by its ID
    public Optional<Cargo> findById(int id) {
        return cargoRepository.findById(id);
    }
    
    // Delete Cargo by its ID
    public void deleteById(int id) {
        cargoRepository.deleteById(id);
    }

	public List<Cargo> findAll() {
		
		return cargoRepository.findAll();
	}
}
