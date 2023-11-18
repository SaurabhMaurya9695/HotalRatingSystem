package com.rating.service.controller;


import com.rating.service.entities.Rating;
import com.rating.service.response.ApiResponse;
import com.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    //Create Ratings
    @PostMapping("/")
    public ResponseEntity<Rating> createRating( @RequestBody  Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.ratingService.createRating(rating));
    }

    @GetMapping("/")
    public ResponseEntity<List<Rating>> getAllRating(){
        return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.getAllRating());
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getSingleRating(@PathVariable("ratingId") String ratingId){
        return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.getSingleRating(ratingId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingOfHotel(@PathVariable("hotelId") String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.getAllRatingOfHotel(hotelId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingOfUser(@PathVariable("userId") String userId){
        return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.getAllRatingOfUser(userId));
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<ApiResponse> deleteRating(@PathVariable("ratingId") String ratingId){
        return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.deleteRating(ratingId));
    }


}
