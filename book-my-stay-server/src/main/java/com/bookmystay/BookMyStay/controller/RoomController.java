package com.bookmystay.BookMyStay.controller;

import com.bookmystay.BookMyStay.dto.Response;
import com.bookmystay.BookMyStay.service.interfac.IBookingService;
import com.bookmystay.BookMyStay.service.interfac.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.midi.VoiceStatus;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IBookingService bookingService;

    @GetMapping("/all")
    public ResponseEntity<Response> getAllRooms() {
        Response response = roomService.getAllRooms();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/types")
    public List<String> getAllRoomTypes() {
        return roomService.getAllRoomTypes();
    }
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Response> addNewRoom(@RequestParam(value = "photo", required = false) MultipartFile photo,
                                               @RequestParam(value = "rommType", required = false) String roomType,
                                               @RequestParam(value = "roomPrice", required = false) BigDecimal roomPrice,
                                               @RequestParam(value = "roomDescription", required = false) String description) {

        if(photo == null || photo.isEmpty() || roomType==null || roomType.isBlank() || roomPrice== null){
            Response response=new Response();
            response.setStatusCode(400);
            response.setMessage("Please provide values for all fields(photo, roomType, roomPrice");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
        Response response = roomService.addNewRoom(photo, roomType, roomPrice, description);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }


}
