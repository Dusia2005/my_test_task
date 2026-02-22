package com.example.my_test_task.repository;

import com.example.my_test_task.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByCityIgnoreCase(String city);

    List<Hotel> findByNameContainingIgnoreCase(String name);

    List<Hotel> findByBrandIgnoreCase(String brand);

}
