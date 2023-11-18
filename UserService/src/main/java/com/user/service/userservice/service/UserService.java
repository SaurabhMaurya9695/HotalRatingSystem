package com.user.service.userservice.service;

import com.user.service.userservice.dto.UserDto;
import com.user.service.userservice.entities.User;
import com.user.service.userservice.response.ApiResponse;

import java.util.List;

public interface UserService {

    //create User
    UserDto saveUser(UserDto userDto);

    // getAllUser
    List<UserDto> getAllUser();

    //get Single User
    UserDto getSingleUser(String userId);

    // delete User
    ApiResponse deleteUser(String userId);

    //update User

    UserDto updateUser(String userId , UserDto userDto);
}
