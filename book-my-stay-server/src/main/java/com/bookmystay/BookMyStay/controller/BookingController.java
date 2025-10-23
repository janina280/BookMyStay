package com.bookmystay.BookMyStay.controller;

import com.bookmystay.BookMyStay.dto.Response;
import com.bookmystay.BookMyStay.entity.Booking;
import com.bookmystay.BookMyStay.service.interfac.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    // Creare rezervare
    @PostMapping("/{roomId}/users/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Response> createBooking(@PathVariable Long roomId,
                                                  @PathVariable Long userId,
                                                  @RequestBody Booking bookingRequest) {
        Response response = bookingService.saveBooking(roomId, userId, bookingRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // Obținerea tuturor rezervărilor
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllBookings() {
        Response response = bookingService.getAllBookings();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // Obținerea unei rezervări după codul de confirmare
    @GetMapping("/confirmation/{code}")
    public ResponseEntity<Response> getBookingByConfirmationCode(@PathVariable String code) {
        Response response = bookingService.findBookingByConfirmationCode(code);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // Ștergerea unei rezervări (anulare)
    @DeleteMapping("/{bookingId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Response> deleteBooking(@PathVariable Long bookingId) {
        Response response = bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
