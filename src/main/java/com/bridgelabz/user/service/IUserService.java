

package com.bridgelabz.user.service;

import com.bridgelabz.user.dto.UserDTO;
import com.bridgelabz.user.model.User;
import java.util.List;

public interface IUserService {
    User registerUser(UserDTO dto);

    List<User> getUsers();

    User getById(String token);

    User updateById(String token, UserDTO dto);

    Object deleteById(String token);

    User getByIdAPI(Integer userId);

    User getByEmail(String email);
}
