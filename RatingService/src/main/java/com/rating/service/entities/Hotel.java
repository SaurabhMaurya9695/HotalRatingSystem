package com.rating.service.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private String countryName;
    private String about;
}
