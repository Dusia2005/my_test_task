package com.example.my_test_task.controller;

import com.example.my_test_task.dto.HotelDetailsResponse;
import com.example.my_test_task.service.HotelService;
import com.example.my_test_task.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public HotelDetailsResponse getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }
}
