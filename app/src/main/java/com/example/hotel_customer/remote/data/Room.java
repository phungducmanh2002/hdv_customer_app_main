package com.example.hotel_customer.remote.data;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    int id;
    String name;
    int idHotelRoomClass;
    float roomPrice;
    String roomClassName;

    // Override equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && Objects.equals(name, room.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
