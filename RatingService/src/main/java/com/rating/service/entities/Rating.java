package com.rating.service.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("rating") // in case of mongo we have to use Document not Entity
public class Rating {

    @Id
    private String ratingId;
    private String userId;
    private String hotelId;
    private String rating;
    private String feedback;

    private Hotel hotel ;

}