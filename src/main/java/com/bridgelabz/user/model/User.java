package com.bridgelabz.user.model;

import com.bridgelabz.user.dto.UserDTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "UserDetails")
public class User {
    @Id
    @GeneratedValue
    private Integer userId;
    private String fullName;
    private String permanentAddress;
    private String temporaryAddress;
    private String mobileNumber;
    private String email;
    private int age;
    private String occupation;
    private String familyBackground;
    private String kyc;
    private String healthCondition;
    private String vehicleData;
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();

    public User(UserDTO dto) {
        this.fullName = dto.getFullName();
        this.permanentAddress = dto.getPermanentAddress();
        this.temporaryAddress = dto.getTemporaryAddress();
        this.mobileNumber = dto.getMobileNumber();
        this.email = dto.getEmail();
        this.age = dto.getAge();
        this.occupation = dto.getOccupation();
        this.familyBackground = dto.getFamilyBackground();
        this.kyc = dto.getKyc();
        this.healthCondition = dto.getHealthCondition();
        this.vehicleData = dto.getVehicleData();
    }

    public User(Integer userId, UserDTO dto) {
        this.userId = userId;
        this.fullName = dto.getFullName();
        this.permanentAddress = dto.getPermanentAddress();
        this.temporaryAddress = dto.getTemporaryAddress();
        this.mobileNumber = dto.getMobileNumber();
        this.age = dto.getAge();
        this.occupation = dto.getOccupation();
        this.familyBackground = dto.getFamilyBackground();
        this.kyc = dto.getKyc();
        this.healthCondition = dto.getHealthCondition();
        this.vehicleData = dto.getVehicleData();
    }

    public User() {
    }
}