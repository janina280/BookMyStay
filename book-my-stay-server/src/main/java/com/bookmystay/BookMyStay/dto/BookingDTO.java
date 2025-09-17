package com.bookmystay.BookMyStay.dto;

import com.bookmystay.BookMyStay.entity.Room;
import com.bookmystay.BookMyStay.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {

    private Long id;
    private LocalDate checkInData;
    private LocalDate checkOutDate;
    private int numOfAdults;
    private int numOfChildren;
    private int totalNumOfGuest;
    private String bookingConfirmationCode;
    private UserDTO user;
    private RoomDTO room;

}
