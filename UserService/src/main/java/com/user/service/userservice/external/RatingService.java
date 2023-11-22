package com.user.service.userservice.external;

import com.user.service.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/rating/user/{userId}")
    public List<Rating> getAllRatings(@PathVariable("userId") String userId);
}
