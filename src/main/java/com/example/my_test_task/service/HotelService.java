package com.example.my_test_task.service;

import com.example.my_test_task.repository.HotelRepository;
import com.example.my_test_task.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
