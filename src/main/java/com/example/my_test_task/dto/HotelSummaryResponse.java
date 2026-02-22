package com.example.my_test_task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HotelSummaryResponse {

    private Long id;
    private String name;
    private String description;
    private String address; // объединяем все в одну строку
    private String phone;
}