package com.example.hotel_customer.remote.data;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hotel {
    Integer id;
    String name;
    String description;
    String hotline;
    Date createdAt;
    Float star;
    Float minPrice;
    Float maxPrice;
    Integer idUser;
    Integer idCommune;
}
