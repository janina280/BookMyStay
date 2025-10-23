package com.bookmystay.BookMyStay.controller;

import com.bookmystay.BookMyStay.dto.LoginRequest;
import com.bookmystay.BookMyStay.dto.Response;
import com.bookmystay.BookMyStay.entity.User;
import com.bookmystay.BookMyStay.service.interfac.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    // Înregistrare utilizator
    @PostMapping("/signup")
    public ResponseEntity<Response> signup(@RequestBody User user) {
        Response response = userService.register(user);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // Autentificare utilizator
    @PostMapping("/signin")
    public ResponseEntity<Response> signin(@RequestBody LoginRequest loginRequest) {
        Response response = userService.login(loginRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
