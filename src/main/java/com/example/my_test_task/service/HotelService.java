package com.example.my_test_task.service;

import com.example.my_test_task.dto.*;
import com.example.my_test_task.repository.HotelRepository;
import com.example.my_test_task.entity.Hotel;
import com.example.my_test_task.util.PhoneFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Hotel> search(String name, String brand, String city, String country, String amenity) {
        if(city != null) {
            return hotelRepository.findByCityIgnoreCase(city);
        }

        if (name != null) {
            return  hotelRepository.findByNameContainingIgnoreCase(name);
        }

        if (brand != null) {
            return  hotelRepository.findByBrandIgnoreCase(brand);
        }
        if (country != null) {
            return hotelRepository.findByCountryIgnoreCase(country);
        }

        if (amenity != null) {
            return hotelRepository.findByAmenitiesIgnoreCase(amenity);
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
                PhoneFormatter.format(hotel.getPhone())
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
                new ContactsDto(
                        PhoneFormatter.format(hotel.getPhone()),
                        hotel.getEmail()
                ),
                new ArrivalTimeDto(hotel.getCheckIn(), hotel.getCheckOut()),
                hotel.getAmenities()
        );
    }

    public void addAmenities(Long id, List<String> amenities) {
        Hotel hotel = getHotelById(id);
        hotel.setAmenities(amenities);
        hotelRepository.save(hotel);
    }


    public Map<String, Long> getHistogram(String param) {

        List<Hotel> hotels = hotelRepository.findAll();

        return switch (param) {
            case "brand" -> hotels.stream()
                    .collect(Collectors.groupingBy(Hotel::getBrand, Collectors.counting()));

            case "city" -> hotels.stream()
                    .collect(Collectors.groupingBy(Hotel::getCity, Collectors.counting()));

            case "country" -> hotels.stream()
                    .collect(Collectors.groupingBy(Hotel::getCountry, Collectors.counting()));

            case "amenities" -> hotels.stream()
                    .flatMap(h -> h.getAmenities().stream())
                    .collect(Collectors.groupingBy(a -> a, Collectors.counting()));

            default -> throw new RuntimeException("Unknown param");
        };
    }
}
