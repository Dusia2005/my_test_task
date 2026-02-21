package com.example.my_test_task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArrivalTimeDto {

    private String checkIn;
    private String checkOut;
}