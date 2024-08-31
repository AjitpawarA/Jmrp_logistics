package com.alpha.jmrplogistics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Unloading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyname;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String unloadingdate;
    private String unloadingtime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyname;
	}
	public void setCompanyName(String companyName) {
		this.companyname = companyName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getUnloadingDate() {
		return unloadingdate;
	}
	public void setUnloadingDate(String unloadingDate) {
		this.unloadingdate = unloadingDate;
	}
	public String getUnloadingTime() {
		return unloadingtime;
	}
	public void setUnloadingTime(String unloadingTime) {
		this.unloadingtime = unloadingTime;
	}

}
