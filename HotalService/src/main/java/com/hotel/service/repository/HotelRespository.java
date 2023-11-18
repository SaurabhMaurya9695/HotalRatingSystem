package com.hotel.service.repository;

import com.hotel.service.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRespository extends JpaRepository<Hotel , String> {
    Optional<List<Hotel>> findByCountryName(String country_name);
}
