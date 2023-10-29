package com.easy.car_Rent.service;

import com.easy.car_Rent.dto.UserDTO;

import java.util.ArrayList;

public interface UserService {
    ArrayList<UserDTO> getAllRegUsers();

    UserDTO getRegUsers(String username,String password);

}
