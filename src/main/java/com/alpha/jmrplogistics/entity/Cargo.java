package com.alpha.jmrplogistics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Cargo name should not be null or empty")
    @Size(max = 100, message = "Cargo name must not exceed 100 characters")
    private String cargoname;

    @NotEmpty(message = "Cargo description should not be null or empty")
    @Size(max = 255, message = "Cargo description must not exceed 255 characters")
    private String cargodescription;

    @NotNull(message = "Cargo weight should not be null")
    @Positive(message = "Cargo weight must be greater than zero")
    private Double cargoweight;

    @NotNull(message = "Cargo count should not be null")
    @Positive(message = "Cargo count must be greater than zero")
    private Integer cargocount;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCargoName() {
		return cargoname;
	}
	public void setCargoName(String cargoName) {
		this.cargoname = cargoName;
	}
	public String getCargoDescription() {
		return cargodescription;
	}
	public void setCargoDescription(String cargoDescription) {
		this.cargodescription = cargoDescription;
	}
	public Double getCargoWeight() {
		return cargoweight;
	}
	public void setCargoWeight(Double cargoWeight) {
		this.cargoweight = cargoWeight;
	}
	public Integer getCargoCount() {
		return cargocount;
	}
	public void setCargoCount(Integer cargoCount) {
		this.cargocount = cargoCount;
	}

    // Getters and Setters
}
