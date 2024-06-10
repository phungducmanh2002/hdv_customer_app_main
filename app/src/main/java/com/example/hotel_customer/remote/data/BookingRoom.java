package com.example.hotel_customer.remote.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRoom {
    Integer id;
    Integer idRoom;
    float roomPrice;
    String roomClassName;
}
