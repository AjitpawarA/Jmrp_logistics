package com.alpha.jmrplogistics.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class UnloadingDto {

    @NotEmpty(message = "Company name should not be null or empty")
    private String companyname;

    @NotEmpty(message = "Unloading date should not be null or empty")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Unloading date must be in YYYY-MM-DD format")
    private String unloadingdate;

    @NotEmpty(message = "Unloading time should not be null or empty")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Unloading time must be in HH:MM format")
    private String unloadingtime;

    @Positive(message = "Address ID should be greater than zero")
    private int addressId;

    // Getters and setters
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getUnloadingdate() {
        return unloadingdate;
    }

    public void setUnloadingdate(String unloadingdate) {
        this.unloadingdate = unloadingdate;
    }

    public String getUnloadingtime() {
        return unloadingtime;
    }

    public void setUnloadingtime(String unloadingtime) {
        this.unloadingtime = unloadingtime;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
