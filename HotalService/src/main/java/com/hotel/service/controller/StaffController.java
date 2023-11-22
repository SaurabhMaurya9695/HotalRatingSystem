package com.hotel.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @GetMapping("/")
    public List<String> getStaff(){
        return List.of("Saurabh" , "Yash" , "Bhupriti");
    }
}
