package com.alpha.jmrplogistics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @NotEmpty(message = "Registered number should not be null or empty")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "Registered number must contain only uppercase letters, numbers, and hyphens")
    private String registerednumber;

    @NotNull(message = "Capacity should not be null")
    @Positive(message = "Capacity should be greater than zero")
    private Integer capacity;

    @NotEmpty(message = "Status should not be null or empty")
    @Pattern(regexp = "^(ACTIVE|INACTIVE|MAINTENANCE)$", message = "Status must be one of the following: ACTIVE, INACTIVE, MAINTENANCE")
    private String status;

    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegisteredNumber() {
		return registerednumber;
	}
	public void setRegisteredNumber(String registeredNumber) {
		this.registerednumber = registeredNumber;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
