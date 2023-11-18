package com.hotel.service.serviceImpl;

import com.hotel.service.entity.Hotel;
import com.hotel.service.repository.HotelRespository;
import com.hotel.service.response.ApiResponse;
import com.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl  implements HotelService {

    @Autowired
    private HotelRespository hotelRespository;
    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return this.hotelRespository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return this.hotelRespository.findAll();
    }

    @Override
    public Hotel getSingleHotel(String hotelId) {
        return this.hotelRespository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel Not Found!!"));
    }

    @Override
    public List<Hotel> getAllHotelByCountry(String country) {
        return this.hotelRespository.findByCountryName(country)
                .orElseThrow(() -> new RuntimeException("Hotel Not Found With This Country!!"));
    }

    @Override
    public ApiResponse deleteHotelById(String hotelId) {
        this.hotelRespository.deleteById(hotelId);
        return ApiResponse.builder().code(HttpStatus.OK).
                message("Deleted Successfully").success(true).build();
    }
}
