package com.example.hotel_customer.remote.service;

import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.data.Booking;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookingService {
    @GET("/booking/hotels/{idHotel}/room-classes/{idRoomClass}/rooms/can-booking")
    public Call<ResData> getRoomsCanBooking(
            @Path("idHotel") int idHotel,
            @Path("idRoomClass") int idRoomClass,
            @Query("startTime") String startTime,
            @Query("endTime") String endTime);

    @POST("/booking/bookings")
    public Call<ResData> createBooking(@Body Booking booking);

    @GET("/booking/bookings/{idBooking}")
    public Call<ResData> getBooking(@Path("idBooking") int idBooking);

    @GET("/booking/users/{idUser}/bookings")
    public Call<ResData> getUserBookings(@Path("idUser") int idUser, @Query("status") int status);

    @PUT("/bookings/{idBooking}")
    Call<ResData> updateBookingStatus(@Path("idBooking") Integer idBooking, @Query("status") int status);
}
