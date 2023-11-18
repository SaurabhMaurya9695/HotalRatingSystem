package com.hotel.service.service;

import com.hotel.service.entity.Hotel;
import com.hotel.service.response.ApiResponse;
import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel> getAllHotel();

    Hotel getSingleHotel(String hotelId);

    List<Hotel> getAllHotelByCountry(String country);

    ApiResponse deleteHotelById(String hotelId);
}
