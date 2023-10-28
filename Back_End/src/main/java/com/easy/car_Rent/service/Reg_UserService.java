package com.easy.car_Rent.service;

import com.easy.car_Rent.dto.CustomDTO;
import com.easy.car_Rent.dto.Reg_UserDTO;

import java.util.ArrayList;

public interface Reg_UserService {
    void saveUser(Reg_UserDTO dto);

    CustomDTO userIdGenerate();

    ArrayList<Reg_UserDTO> getAllUser();
}

