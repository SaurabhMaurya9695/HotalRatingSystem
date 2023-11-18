package com.hotel.service.controller;

import com.hotel.service.entity.Hotel;
import com.hotel.service.response.ApiResponse;
import com.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/")
    public ResponseEntity<Hotel> create(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.hotelService.create(hotel));
    }

    @GetMapping("/")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        return ResponseEntity.status(HttpStatus.OK).body(this.hotelService.getAllHotel());
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getAllHotel(@PathVariable("hotelId") String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(this.hotelService.getSingleHotel(hotelId));
    }

    @GetMapping("/country/{countryName}")
    public ResponseEntity<List<Hotel>> getAllHotelOfCountry(@PathVariable("countryName") String countryName){
        return ResponseEntity.status(HttpStatus.OK).body(this.hotelService.getAllHotelByCountry(countryName));
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<ApiResponse> deleteHotel(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(this.hotelService.deleteHotelById(hotelId));
    }

}
