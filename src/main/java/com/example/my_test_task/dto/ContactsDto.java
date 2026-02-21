package com.example.my_test_task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactsDto {

    private String phone;
    private String email;
}