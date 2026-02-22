package com.example.my_test_task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String brand;

    @Column(name = "house_number")
    private Integer houseNumber;
    private String street;
    private String city;
    private String country;
    @Column(name = "post_code")
    private String postCode;

    private String phone;
    private String email;

    @Column(name = "check_in")
    private String checkIn;
    @Column(name = "check_out")
    private String checkOut;

    public Long getId() {
        return id;
    }
}
