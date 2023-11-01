package com.easy.car_Rent.service;

import com.easy.car_Rent.dto.CustomDTO;
import com.easy.car_Rent.dto.PaymentDTO;

import java.util.ArrayList;

public interface PaymentService {
    CustomDTO paymentIdGenerate();
    void savePayment(PaymentDTO dto, String rentID);
    ArrayList<PaymentDTO> getAllPayment();
}
