package com.alpha.jmrplogistics.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UserDto {

	@NotEmpty(message = "Username should not be null or empty")
    @Size(min = 3, max = 50, message = "Username should be between 3 and 50 characters")
    private String username;

    @NotEmpty(message = "User password should not be null or empty")
    @Size(min = 8, message = "Password should be at least 8 characters long")
    private String userpassword;

    @Positive(message = "Address ID should be greater than zero")
    private int addressId;

    @NotEmpty(message = "User role should not be null or empty")
    @Pattern(regexp = "^(ADMIN|USER|DRIVER)$", message = "User role must be one of the following: ADMIN, USER, DRIVER")
    private String userrole;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
    
    
    
}
