package com.user.service.userservice.dto;

import com.user.service.userservice.entities.Rating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String name ;
    private String email;
    private String about ;
    private String password;
    private List<Rating> ratingList = new ArrayList<>();
}
