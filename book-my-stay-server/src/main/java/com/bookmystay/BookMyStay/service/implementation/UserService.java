package com.bookmystay.BookMyStay.service.implementation;

import com.bookmystay.BookMyStay.dto.LoginRequest;
import com.bookmystay.BookMyStay.dto.Response;
import com.bookmystay.BookMyStay.entity.User;
import com.bookmystay.BookMyStay.service.interfac.IUserService;

public class UserService implements IUserService {
    @Override
    public Response register(User loginRequest) {
        return null;
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public Response getAllUsers() {
        return null;
    }

    @Override
    public Response getUserBookingHistory(String userId) {
        return null;
    }

    @Override
    public Response deleteUser(String userId) {
        return null;
    }

    @Override
    public Response getUserById(String userId) {
        return null;
    }

    @Override
    public Response getMyInfo(String userId) {
        return null;
    }
}
