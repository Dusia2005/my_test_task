package com.example.my_test_task.controller;

import com.example.my_test_task.dto.HotelDetailsResponse;
import com.example.my_test_task.dto.HotelSummaryResponse;
import com.example.my_test_task.service.HotelService;
import com.example.my_test_task.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property-view")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotels")
    public List<HotelSummaryResponse> getAll() {
        return hotelService.getAllHotels().stream()
                .map(hotelService::toSummaryDto)
                .toList();
    }

    @GetMapping("/hotels/{id}")
    public HotelDetailsResponse getById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        return hotelService.toDetailsDto(hotel);
    }

    @PostMapping("/hotels")
    public HotelSummaryResponse create(@RequestBody Hotel hotel) {
        Hotel saved = hotelService.createHotel(hotel);
        return hotelService.toSummaryDto(saved);
    }

    @GetMapping("/search")
    public List<HotelSummaryResponse> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country
    ) {
        return hotelService.search(name, brand, city, country).stream()
                .map(hotelService::toSummaryDto)
                .toList();
    }
}
