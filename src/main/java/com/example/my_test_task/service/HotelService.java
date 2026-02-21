package com.example.my_test_task.service;

import com.example.my_test_task.dto.AddressDto;
import com.example.my_test_task.dto.ArrivalTimeDto;
import com.example.my_test_task.dto.ContactsDto;
import com.example.my_test_task.dto.HotelDetailsResponse;
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

    public HotelDetailsResponse getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        return new HotelDetailsResponse(
                hotel.getId(),
                hotel.getName(),
                hotel.getDescription(),
                "Hilton",
                new AddressDto(
                        9,
                        "Pobediteley Avenue",
                        "Minsk",
                        "Belarus",
                        "220004"
                ),
                new ContactsDto(
                        hotel.getPhone(),
                        "doubletreeminsk.info@hilton.com"
                ),
                new ArrivalTimeDto("14:00", "12:00"),
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
