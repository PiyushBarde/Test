package com.bridgelabz.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDTO {
    @Pattern(
        regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",
        message = "First letter must be capital"
    )
    @NotEmpty(
        message = "Name must be filled"
    )
    private String fullName;
    @NotEmpty(
        message = "Permanent Address must not be empty"
    )
    private String permanentAddress;
    private String temporaryAddress;
    @Pattern(
        regexp = "[0-9]{10}",
        message = "Invalid Mobile Number"
    )
    private String mobileNumber;
    @Email(
        message = "Insert valid email"
    )
    private String email;
    @Min(
        value = 18L,
        message = "Age must be between 18 to 60 years"
    )
    @Max(
        value = 60L,
        message = "Age must be between 18 to 60 years"
    )
    private int age;
    @NotEmpty(
        message = "occupation must not be empty"
    )
    private String occupation;
    private String familyBackground;
    @NotEmpty(
        message = "KYC must be done"
    )
    private String kyc;
    @NotEmpty(
        message = "Health check is important"
    )
    private String healthCondition;
    @NotEmpty(
        message = "Vehical data needs to be filled"
    )
    private String vehicleData;

    public UserDTO() {
    }
}