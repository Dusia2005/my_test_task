package com.example.my_test_task.util;

public class PhoneFormatter {

    public static String format(String phone) {

        if (phone == null || phone.length() != 13) {
            return phone;
        }

        return String.format("%s %s %s-%s-%s",
                phone.substring(0, 4),
                phone.substring(4, 6),
                phone.substring(6, 9),
                phone.substring(9, 11),
                phone.substring(11, 13)
        );
    }
}