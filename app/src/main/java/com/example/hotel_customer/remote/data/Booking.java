package com.example.hotel_customer.remote.data;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking implements Serializable {
    Integer id;
    Date checkin, checkout;
    String note, personName, personPhone;
    Integer idUser;
    Integer[] idRooms;
    Integer status;
    BookingRoom[] rooms;
}
