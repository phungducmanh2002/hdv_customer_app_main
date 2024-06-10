package com.example.hotel_customer.remote.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    Integer id;
    Integer idAccount;
    String username;
    Integer idRole;
}
