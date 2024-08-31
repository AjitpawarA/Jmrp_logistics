package com.alpha.jmrplogistics.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class DriverDto {
	@NotEmpty(message = "Driver name should not be null or empty")
    private String drivername;

    @NotEmpty(message = "Driver phone number should not be null or empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Driver phone number must be a 10-digit number")
    private String driverphonenumber;

    @Positive(message = "Carrier ID should be greater than zero")
    private int carrierId;

    @Positive(message = "Truck ID should be greater than zero")
    private int truckId;

    
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getDriverphonenumber() {
		return driverphonenumber;
	}
	public void setDriverphonenumber(String driverphonenumber) {
		this.driverphonenumber = driverphonenumber;
	}
	public int getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(int carrierId) {
		this.carrierId = carrierId;
	}
	public int getTruckId() {
		return truckId;
	}
	public void setTruckId(int truckId) {
		truckId = truckId;
	}
    
    
}
