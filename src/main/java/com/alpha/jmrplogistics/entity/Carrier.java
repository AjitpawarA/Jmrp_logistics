package com.alpha.jmrplogistics.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Carrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carriercompanyname;
    private String carriercontact;
    private String carrieremail;

    @OneToMany(mappedBy = "carrier")
    @JsonIgnore
    private List<Driver> drivers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarrierCompanyName() {
		return carriercompanyname;
	}

	public void setCarrierCompanyName(String carrierCompanyName) {
		this.carriercompanyname = carrierCompanyName;
	}

	public String getCarrierContact() {
		return carriercontact;
	}

	public void setCarrierContact(String carrierContact) {
		this.carriercontact = carrierContact;
	}

	public String getCarrierEmail() {
		return carrieremail;
	}

	public void setCarrierEmail(String carrierEmail) {
		this.carrieremail = carrierEmail;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

}
