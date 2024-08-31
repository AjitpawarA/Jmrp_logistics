package com.alpha.jmrplogistics.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class OrderDto {
	
	@NotNull(message = "Date of order should not be null")
    private LocalDate dateoforder;

    @NotEmpty(message = "Order status should not be null or empty")
    @Pattern(regexp = "^(PENDING|COMPLETED|CANCELLED)$", message = "Order status must be one of the following: PENDING, COMPLETED, CANCELLED")
    private String orderstatus;

    @NotNull(message = "Freight cost should not be null")
    @Positive(message = "Freight cost should be greater than zero")
    private Double freightcost;

    @Size(max = 255, message = "Additional info should not exceed 255 characters")
    private String additionalinfo;

    @Positive(message = "Cargo ID should be greater than zero")
    private long cargoId;

    @Positive(message = "Carrier ID should be greater than zero")
    private long carrierID;

    @Positive(message = "Loading User ID should be greater than zero")
    private int loadingUserid;

    @Positive(message = "Unloading User ID should be greater than zero")
    private int unloadingUserid;

    @Positive(message = "Loading ID should be greater than zero")
    private int loadingId;

    @Positive(message = "Unloading ID should be greater than zero")
    private int unloadingId;
	
	public int getLoadingId() {
		return loadingId;
	}
	public void setLoadingId(int loadingId) {
		this.loadingId = loadingId;
	}
	public int getUnloadingId() {
		return unloadingId;
	}
	public void setUnloadingId(int unloadingId) {
		this.unloadingId = unloadingId;
	}
	public int getLoadingUserid() {
		return loadingUserid;
	}
	public void setLoadingUserid(int loadingUserid) {
		this.loadingUserid = loadingUserid;
	}
	public int getUnloadingUserid() {
		return unloadingUserid;
	}
	public void setUnloadingUserid(int unloadingUserid) {
		this.unloadingUserid = unloadingUserid;
	}
	public LocalDate getDateoforder() {
		return dateoforder;
	}
	public void setDateoforder(LocalDate dateoforder) {
		this.dateoforder = dateoforder;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	public Double getFreightcost() {
		return freightcost;
	}
	public void setFreightcost(Double freightcost) {
		this.freightcost = freightcost;
	}
	public String getAdditionalinfo() {
		return additionalinfo;
	}
	public void setAdditionalinfo(String additionalinfo) {
		this.additionalinfo = additionalinfo;
	}
	public long getCargoId() {
		return cargoId;
	}
	public void setCargoId(long cargoId) {
		this.cargoId = cargoId;
	}
	public long getCarrierID() {
		return carrierID;
	}
	public void setCarrierID(long carrierID) {
		this.carrierID = carrierID;
	}
	
	
}
