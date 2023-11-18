package com.user.service.userservice.serviceImpl;

import com.user.service.userservice.dto.UserDto;
import com.user.service.userservice.entities.User;
import com.user.service.userservice.exceptions.GlobalExceptionHandler;
import com.user.service.userservice.exceptions.ResourceNotFoundException;
import com.user.service.userservice.repository.UserRepository;
import com.user.service.userservice.response.ApiResponse;
import com.user.service.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper ;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto saveUser(UserDto userDto) {

        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);
        logger.info(userId);
        //converted into entity
        User user  = modelMapper.map(userDto, User.class);

        //saved the entity
        User savedUser = this.userRepository.save(user);

        //convert back to dto
        UserDto  userDto1=modelMapper.map(savedUser, UserDto.class);

        //send the Dto
        return userDto1;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User>  allUsers = this.userRepository.findAll();
        //now we have to send all users in the form of DTO

        List<UserDto> allUsersDto = allUsers.stream().map(item -> {
            return modelMapper.map(item, UserDto.class);
        }).collect(Collectors.toList());

        return allUsersDto;
    }

    @Override
    public UserDto getSingleUser(String userId) {
        User user = this.userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User Not Found With this UserId"));

        // here we are getting user but we don't have any ratings of user
        // so we have to get ratings also ..for that we use feign client or restTemplate
        // http://localhost:2025/rating/user/{userId}
        return modelMapper.map(user , UserDto.class);
    }

    @Override
    public ApiResponse deleteUser(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found With this UserId"));
        this.userRepository.delete(user);
        return ApiResponse.builder().message("User Deleted Successfully").code(HttpStatus.OK).success(true).build();
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found With this UserId"));
        // here no need to set userId
        user.setAbout(userDto.getAbout());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        // now save this user again in db
        this.userRepository.save(user);

        // all the details updated now
        return modelMapper.map(user , UserDto.class);
    }
}
