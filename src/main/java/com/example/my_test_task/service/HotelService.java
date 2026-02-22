package com.example.my_test_task.service;

import com.example.my_test_task.dto.*;
import com.example.my_test_task.repository.HotelRepository;
import com.example.my_test_task.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> search(String name, String brand, String city, String country) {
        if(city != null) {
            return hotelRepository.findByCityIgnoreCase(city);
        }

        if (name != null) {
            return  hotelRepository.findByNameContainingIgnoreCase(name);
        }

        if (brand != null) {
            return  hotelRepository.findByBrandIgnoreCase(brand);
        }

        return  hotelRepository.findAll();
    }
    public HotelSummaryResponse toSummaryDto(Hotel hotel) {
        String address = String.format("%d %s, %s, %s, %s",
                hotel.getHouseNumber(),
                hotel.getStreet(),
                hotel.getCity(),
                hotel.getPostCode(),
                hotel.getCountry());
        return new HotelSummaryResponse(
                hotel.getId(),
                hotel.getName(),
                hotel.getDescription(),
                address,
                hotel.getPhone()
        );
    }

    public HotelDetailsResponse toDetailsDto(Hotel hotel) {
        return new HotelDetailsResponse(
                hotel.getId(),
                hotel.getName(),
                hotel.getDescription(),
                hotel.getBrand(),
                new AddressDto(
                        hotel.getHouseNumber(),
                        hotel.getStreet(),
                        hotel.getCity(),
                        hotel.getCountry(),
                        hotel.getPostCode()
                ),
                new ContactsDto(hotel.getPhone(), hotel.getEmail()),
                new ArrivalTimeDto(hotel.getCheckIn(), hotel.getCheckOut()),
                List.of(
                        "Free parking",
                        "Free WiFi",
                        "Non-smoking rooms",
                        "Concierge",
                        "On-site restaurant",
                        "Fitness center",
                        "Pet-friendly rooms",
                        "Room service",
                        "Business center",
                        "Meeting rooms"
                )
        );
    }


}
