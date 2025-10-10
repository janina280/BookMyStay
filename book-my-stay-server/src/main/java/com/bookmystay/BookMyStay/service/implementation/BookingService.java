package com.bookmystay.BookMyStay.service.implementation;

import com.bookmystay.BookMyStay.dto.Response;
import com.bookmystay.BookMyStay.entity.Booking;
import com.bookmystay.BookMyStay.entity.Room;
import com.bookmystay.BookMyStay.entity.User;
import com.bookmystay.BookMyStay.exception.OurException;
import com.bookmystay.BookMyStay.repository.BookingRepository;
import com.bookmystay.BookMyStay.repository.RoomRepository;
import com.bookmystay.BookMyStay.repository.UserRepository;
import com.bookmystay.BookMyStay.service.interfac.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements IBookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Response getBookingById(String bookingId) {

        return null;
    }

    @Override
    public Response findBookingByConfirmationCode(String confirmationCode) {

        return null;
    }

    @Override
    public Response getAllBookings() {
        return null;
    }

    @Override
    public Response saveBooking(Long roomId, Long userId, Booking bookingRequest) {
        Response response=new Response();
        try {
            if(bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate()))
            {
                throw new IllegalAccessException("Check in date must come after check out date");
            }
            Room room=roomRepository.findById(roomId).orElseThrow(()->new OurException("Room not found"));
            User user= userRepository.findById(userId).orElseThrow(()-> new OurException("User not found"));
            List<Booking> existingBookings= room.getBookings();

            if(!roomIsAvailable(bookingRequest, existingBookings)){
                throw new OurException("Room not Available for selected date range");
            }
        }
        catch (OurException e){

        }
        catch (Exception e){

        }
        return null;
    }

    private boolean roomIsAvailable(Booking bookingRequest, List<Booking> existingBookings) {
        return existingBookings.stream()
                .noneMatch(existingBooking->
                        bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                            || bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
                            || (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
                            || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                            && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
                            || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                            && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))

                            || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                            &&  bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))

                            || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                        && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
                );

    }

    @Override
    public Response cancelBooking(Long bookingId) {
        return null;
    }
}
