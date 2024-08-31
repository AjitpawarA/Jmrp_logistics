package com.alpha.jmrplogistics.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.jmrplogistics.entity.Address;
import com.alpha.jmrplogistics.repo.AddressRepo;

@Repository
public class AddressDao {
    @Autowired
    private AddressRepo addressRepository;

    public Address addAddress(Address address) {
        return addressRepository.saveAndFlush(address);
    }

    public Optional<Address> findById(int id) {
        return addressRepository.findById(id);
    }
    
    public void deleteById(int id) {
    	addressRepository.deleteById(id);
	}

	public List<Address> findall(){
		return addressRepository.findAll();
	}
}
