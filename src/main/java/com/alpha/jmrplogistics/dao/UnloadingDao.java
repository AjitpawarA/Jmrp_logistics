package com.alpha.jmrplogistics.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.jmrplogistics.entity.Unloading;
import com.alpha.jmrplogistics.repo.UnloadingRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class UnloadingDao {

    @Autowired
    private UnloadingRepository unloadingRepository;

    public Unloading saveUnloading(Unloading unloading) {
        return unloadingRepository.saveAndFlush(unloading);
    }
    
    public Optional<Unloading> findById(int id) {
        return unloadingRepository.findById(id);
    }
    
    public void deleteById(int id) {
        unloadingRepository.deleteById(id);
    }

	public List<Unloading> findAll() {
		// TODO Auto-generated method stub
		return unloadingRepository.findAll();
	}
}
