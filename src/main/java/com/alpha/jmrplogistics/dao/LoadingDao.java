package com.alpha.jmrplogistics.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.jmrplogistics.entity.Loading;
import com.alpha.jmrplogistics.repo.LoadingRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class LoadingDao {

    @Autowired
    private LoadingRepository loadingRepository;

    public Loading addLoading(Loading loading) {
        return loadingRepository.saveAndFlush(loading);
    }
    
    public Optional<Loading> findById(int id) {
        return loadingRepository.findById(id);
    }
    
    public void deleteById(int id) {
        loadingRepository.deleteById(id);
    }
    
    
    public List<Loading> findall(){
    	return loadingRepository.findAll();
    	}
}
