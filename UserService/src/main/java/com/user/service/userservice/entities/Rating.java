package com.user.service.userservice.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    private String ratingId;
    private String userId;
    private String hotelId;
    private String rating;
    private String feedback;
    private Hotel hotel;

}
