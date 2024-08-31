package com.alpha.jmrplogistics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "Street name should not be null or empty")
    private String streetname;

    @NotEmpty(message = "House number should not be null or empty")
    private String housenumber;

    @NotEmpty(message = "Area pincode should not be null or empty")
    @Pattern(regexp = "^\\d{6}$", message = "Area pincode must be exactly 6 digits")
    private String areapincode;

    @NotEmpty(message = "District should not be null or empty")
    @Size(max = 50, message = "District name should not exceed 50 characters")
    private String district;

    @NotEmpty(message = "State should not be null or empty")
    @Size(max = 50, message = "State name should not exceed 50 characters")
    private String state;

    @NotEmpty(message = "Country should not be null or empty")
    @Size(max = 50, message = "Country name should not exceed 50 characters")
    private String country;
    
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStreetName() {
		return streetname;
	}
	public void setStreetName(String streetName) {
		this.streetname = streetName;
	}
	public String getHouseNumber() {
		return housenumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.housenumber = houseNumber;
	}
	public String getAreaPincode() {
		return areapincode;
	}
	public void setAreaPincode(String areaPincode) {
		this.areapincode = areaPincode;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	

}
