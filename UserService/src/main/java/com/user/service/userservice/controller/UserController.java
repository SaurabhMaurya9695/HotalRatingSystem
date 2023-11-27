package com.user.service.userservice.controller;

import com.user.service.userservice.dto.UserDto;
import com.user.service.userservice.response.ApiResponse;
import com.user.service.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    int retryCount = 1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingUserBreaker" , fallbackMethod = "ratingHotelFallBack")
    @Retry(name = "ratingUserBreaker" , fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") String userId){
        logger.info("Retrying {} times " , retryCount);
        retryCount++;
        UserDto singleUser = this.userService.getSingleUser(userId);
        return new ResponseEntity<>(singleUser , HttpStatus.OK);
    }

    private ResponseEntity<UserDto> ratingHotelFallBack(String userId , Exception ex){
        logger.info("Fall Back Method Executed for ratingHotelFallBack {}" , ex.getMessage());
        UserDto userDto = UserDto.builder().userId("1234").name("Dummy").email("dummy@gmail.com").about("This is dummy data").build();
        return new ResponseEntity<>(userDto ,HttpStatus.OK);
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
