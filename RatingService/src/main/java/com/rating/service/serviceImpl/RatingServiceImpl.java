package com.rating.service.serviceImpl;

import com.rating.service.entities.Rating;
import com.rating.service.exception.ResourceNotFoundException;
import com.rating.service.repository.RatingRepository;
import com.rating.service.response.ApiResponse;
import com.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating createRating(Rating rating) {
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return this.ratingRepository.findAll();
    }

    @Override
    public Rating getSingleRating(String ratingId) {
        return this.ratingRepository.findById(ratingId).orElseThrow(() -> new RuntimeException("Hotel Id Not Found!!"));
    }

    @Override
    public List<Rating> getAllRatingOfHotel(String hotelId) {
        return this.ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public List<Rating> getAllRatingOfUser(String userId) {
        return this.ratingRepository.findByUserId(userId);
    }

    @Override
    public ApiResponse deleteRating(String ratingId) {
        Rating rating = this.ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating Id Not Exist!!.."));
        this.ratingRepository.deleteById(ratingId);
        return ApiResponse.builder().code(HttpStatus.OK).message("Successfully Deleted").success(true).build();
    }

    @Override
    public ApiResponse clearAllRating(String userId){
        List<Rating> ratings = this.ratingRepository.findByUserId(userId);
        System.out.println(ratings);
        ratings.stream().map(item -> {
            this.ratingRepository.findById(item.getRatingId())
                    .orElseThrow(() -> new ResourceNotFoundException("Rating Id Not Exist!!.."));
            this.ratingRepository.deleteById(item.getRatingId());
            return null;
        }).collect(Collectors.toList());
        return ApiResponse.builder().code(HttpStatus.OK).message("Cleared The Ratings for User")
                .success(true).build();
    }
}
