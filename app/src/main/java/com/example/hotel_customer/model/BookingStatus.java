package com.example.hotel_customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingStatus implements AdapterModel{
    int id;
    String name;


    @Override
    public String getContent() {
        return name;
    }
}
