package com.rating.service.repository;

import com.rating.service.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {

    //custom Methods

    //Find all Rating of a Hotel
    List<Rating> findByHotelId(String hotelId);

    //Find All rating given by User
    List<Rating> findByUserId(String userId);
}
