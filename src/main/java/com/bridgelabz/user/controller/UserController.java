package com.bridgelabz.user.controller;


import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.user.dto.ResponseDTO;
import com.bridgelabz.user.dto.UserDTO;
import com.bridgelabz.user.model.User;
import com.bridgelabz.user.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService service;

    public UserController() {
    }

    @PostMapping({"/register"})
    public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UserDTO dto) {
        User user = this.service.registerUser(dto);
        ResponseDTO response = new ResponseDTO("User Registered to bookstore", user);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping({"/getAll"})
    public ResponseEntity<String> getUsers() {
        List<User> user = this.service.getUsers();
        ResponseDTO response = new ResponseDTO("Users :", user);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping({"/findById/{token}"})
    public ResponseEntity<ResponseDTO> getById(@PathVariable String token) {
        User user = this.service.getById(token);
        ResponseDTO response = new ResponseDTO("Requested User : ", user);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping({"/getByEmail/{email}"})
    public ResponseEntity<ResponseDTO> getByEmail(@PathVariable String email) {
        User user = this.service.getByEmail(email);
        ResponseDTO response = new ResponseDTO("Requested User : ", user);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping({"/update/{token}"})
    public ResponseEntity<ResponseDTO> updateById(@PathVariable String token, @Valid @RequestBody UserDTO dto) {
        User user = this.service.updateById(token, dto);
        ResponseDTO response = new ResponseDTO("User updated : ", user);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping({"/delete/{token}"})
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable String token) {
        ResponseDTO response = new ResponseDTO("User deleted successfully", this.service.deleteById(token));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping({"/getByIdAPI/{userId}"})
    public User getByIdAPI(@PathVariable Integer userId) {
        System.out.println("Test");
        User user = this.service.getByIdAPI(userId);
        return user;
    }
}
