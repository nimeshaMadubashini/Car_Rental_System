package com.easy.car_Rent.service;

import com.easy.car_Rent.dto.CustomDTO;
import com.easy.car_Rent.dto.DriverDTO;
import com.easy.car_Rent.entity.Driver;

import java.util.ArrayList;

public interface DriverService {
    void saveDriver(DriverDTO dto);
    void updateDriver(DriverDTO dto);
    void deleteDriver(String reg_ID);
    ArrayList<DriverDTO> getAllDriver();
    ArrayList<DriverDTO> getAllAvailabilityDriver();
    CustomDTO userIdGenerate();
    CustomDTO getSumAvailableDriver();
    CustomDTO getSumUnavailableDriver();
    Driver searchDriverId(String id);
    CustomDTO getSumDriver();
}
