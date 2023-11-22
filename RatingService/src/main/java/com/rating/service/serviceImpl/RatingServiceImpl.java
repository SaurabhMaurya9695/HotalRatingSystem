package com.rating.service.serviceImpl;

import com.rating.service.entities.Hotel;
import com.rating.service.entities.Rating;
import com.rating.service.exception.ResourceNotFoundException;
import com.rating.service.repository.RatingRepository;
import com.rating.service.response.ApiResponse;
import com.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating createRating(Rating rating) {
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        // before sending we have to add the hotels of all ratings;
        return this.ratingRepository.findAll();
    }

    @Override
    public Rating getSingleRating(String ratingId) {
        // we have to add the hotel also
        // here we have to add the hotel by hotel_Id


        Rating rating =  this.ratingRepository.findById(ratingId).orElseThrow(() -> new RuntimeException("Hotel Id Not Found!!"));
        Hotel hotel  = restTemplate.getForObject("http://HOTEL-SERVICE/hotel/" + rating.getHotelId() , Hotel.class);
        rating.setHotel(hotel);
        return rating;
    }

    @Override
    public List<Rating> getAllRatingOfHotel(String hotelId) {
        return this.ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public List<Rating> getAllRatingOfUser(String userId) {
        List<Rating> ls =  this.ratingRepository.findByUserId(userId);
        //before sending we have to add hotels here
        // we have here rating and rating has hotel id .. now add hotels one by one
        List<Rating> list = ls.stream().map((ratings) -> {
            Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotel/" + ratings.getHotelId(), Hotel.class);
            ratings.setHotel(hotel);
            return ratings;
        }).collect(Collectors.toList());

        return list;
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
