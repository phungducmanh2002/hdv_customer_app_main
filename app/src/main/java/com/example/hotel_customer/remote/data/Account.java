package com.example.hotel_customer.remote.data;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    Integer id;
    String firstName;
    String lastName;
    String email;
    String password;
    int gender;
    Date birthDay;
}
