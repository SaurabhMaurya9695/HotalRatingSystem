package com.rating.service.service;

import com.rating.service.entities.Rating;
import com.rating.service.response.ApiResponse;

import java.util.List;

public interface RatingService {

    //Create Rating
    Rating createRating(Rating rating);

    //get All Rating
    List<Rating> getAllRating();

    //get All Rating by Id
    Rating getSingleRating(String ratingId);

    // getAll Rating of hotel
    List<Rating> getAllRatingOfHotel(String hotelId);

    //getAll Rating given By user
    List<Rating> getAllRatingOfUser(String userId);

    //delete rating
    ApiResponse deleteRating(String ratingId);

    //clear All rating of users
    ApiResponse clearAllRating(String userId);



}
