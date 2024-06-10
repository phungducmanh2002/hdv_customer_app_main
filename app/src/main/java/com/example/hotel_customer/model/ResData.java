package com.example.hotel_customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResData {
    int code; // 200, 201, 400 404, 500
    String message;
    Object data;
}
