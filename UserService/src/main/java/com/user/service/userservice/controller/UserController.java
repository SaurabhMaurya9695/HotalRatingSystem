package com.user.service.userservice.controller;

import com.user.service.userservice.dto.UserDto;
import com.user.service.userservice.exceptions.GlobalExceptionHandler;
import com.user.service.userservice.response.ApiResponse;
import com.user.service.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    //create User
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto user = this.userService.saveUser(userDto);
        return new ResponseEntity<>(user , HttpStatus.CREATED);
    }

    //getSingleUser
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") String userId){
        UserDto singleUser = this.userService.getSingleUser(userId);
        return new ResponseEntity<>(singleUser , HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> allUser = this.userService.getAllUser();
        return new ResponseEntity<>(allUser , HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") String userId){
        ApiResponse response = this.userService.deleteUser(userId);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId ,@RequestBody UserDto userDto ){
        UserDto updateUser = this.userService.updateUser(userId, userDto);
        return new ResponseEntity<>(updateUser , HttpStatus.OK);
    }


}
