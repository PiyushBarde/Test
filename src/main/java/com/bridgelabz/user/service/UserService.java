package com.bridgelabz.user.service;

import com.bridgelabz.user.dto.UserDTO;
import com.bridgelabz.user.exceptions.UserException;
import com.bridgelabz.user.model.User;
import com.bridgelabz.user.repository.UserRepository;
import com.bridgelabz.user.util.EmailSenderService;
import com.bridgelabz.user.util.TokenUtil;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository repo;
    @Autowired
    EmailSenderService sender;
    @Autowired
    TokenUtil tokenUtil;

    public UserService() {
    }
    
    //to register user 
    public User registerUser(UserDTO dto) {
        Optional<User> matcher = repo.findByEmail(dto.getEmail());
 //       Object user = repo.findByEmail(dto.getEmail()).orElseThrow(()-> new UserException("Email Already Registered"));
        if (matcher.isPresent()) {
            throw new UserException("Email Already Registered");
        } else {
            User user = new User(dto);
            this.repo.save(user);
            String token = this.tokenUtil.createToken(user.getUserId());
            this.sender.sendEmail(user.getEmail(), "User successfully registered", "for User : \n" 
		            + user + "\n click on following link to retrieve data : \n http://localhost:8080/user/findById/" 
		            + token);
            return user;
        }
    }
    
    //to retrieve list of all users
    public List<User> getUsers() {
        List<User> list = this.repo.findAll();
        if (list.isEmpty()) {
            throw new UserException("There are no users added");
        } else {
            return list;
        }
    }
    
    //to retrieve certain user using token as id
    public User getById(String token) {
        Integer userId = this.tokenUtil.decodeToken(token);
        Optional<User> user = this.repo.findById(userId);
        if (user.isEmpty()) {
            throw new UserException("There are no users with given id");
        } else {
            this.sender.sendEmail(((User)user.get()).getEmail(), "User successfully retrieved", "for User : \n" 
            		+ user + "\n click on following link to retrieve data : \n http://localhost:8080/user/findbyid/" 
            		+ token);
            return (User)user.get();
        }
    }
    
  //to retrieve certain user using email
    public User getByEmail(String email) {
        Optional<User> user = this.repo.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserException("There are no users with given id");
        } else {
            this.sender.sendEmail(((User)user.get()).getEmail(), "User successfully retrieved", "for User : \n" 
            		+ user + "\n click on following link to retrieve data : \n http://localhost:8080/user/findbyid/"
            		+ this.tokenUtil.createToken(((User)user.get()).getUserId()));
            return (User)user.get();
        }
    }
    
    //method used in updateById service
    public User updateFunction(String token, UserDTO dto) {
        Integer userId = tokenUtil.decodeToken(token);
        Optional<User> user = repo.findById(userId);
        if (user.isPresent()) {
            User newUser = new User(userId, dto);
            newUser.setEmail(dto.getEmail());
            this.repo.save(newUser);
            this.sender.sendEmail(((User)user.get()).getEmail(), "User successfully updated", "for User : \n" 
            		+ user + "\n click on following link to retrieve data : \n http://localhost:8080/user/findbyid/" 
            		+ token);
            return newUser;
        } else {
            throw new UserException("Employee not found");
        }
    }
    
    //to update user checking if email exists and update
    public User updateById(String token, UserDTO dto) {
        Optional<User> matcher = repo.findByEmail(dto.getEmail());
        if (((User)matcher.get()).getEmail().equals(dto.getEmail())) {
            this.updateFunction(token, dto);
            return this.updateFunction(token, dto);
        } else if (!matcher.isEmpty()) {
            throw new UserException("Email Already Registered");
        } else {
            return this.updateFunction(token, dto);
        }
    }
    
    //to delete user by id
    public Object deleteById(String token) {
        Integer userId = this.tokenUtil.decodeToken(token);
        Optional<User> user = this.repo.findById(userId);
        if (user.isEmpty()) {
            throw new UserException("Invalid token..please input valid token");
        } else {
            this.sender.sendEmail(((User)user.get()).getEmail(), "User successfully deleted", "User : \n" + user.get());
            this.repo.deleteById(userId);
            return user.get();
        }
    }
    
    //---------------------------Rest template-------------------------//
    
    public User getByIdAPI(Integer userId) {
        Optional<User> user = this.repo.findById(userId);
        if (user.isEmpty()) {
            throw new UserException("There are no users with given id");
        } else {
            return (User)user.get();
        }
    }
}
