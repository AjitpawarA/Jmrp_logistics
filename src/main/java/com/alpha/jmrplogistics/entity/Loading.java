package com.alpha.jmrplogistics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Loading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyname;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String loadingdate;
    private String loadingtime;
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
	public String getLoadingDate() {
		return loadingdate;
	}
	public void setLoadingDate(String loadingDate) {
		this.loadingdate = loadingDate;
	}
	public String getLoadingTime() {
		return loadingtime;
	}
	public void setLoadingTime(String loadingTime) {
		this.loadingtime = loadingTime;
	}

    // Getters and Setters
}
