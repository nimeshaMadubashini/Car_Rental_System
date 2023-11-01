package com.easy.car_Rent.service.impl;

import com.easy.car_Rent.dto.CustomDTO;
import com.easy.car_Rent.dto.PaymentDTO;
import com.easy.car_Rent.entity.Car;
import com.easy.car_Rent.entity.Driver;
import com.easy.car_Rent.entity.Payment;
import com.easy.car_Rent.entity.Rent;
import com.easy.car_Rent.repo.CarRepo;
import com.easy.car_Rent.repo.DriverRepo;
import com.easy.car_Rent.repo.PaymentRepo;
import com.easy.car_Rent.repo.RentRepo;
import com.easy.car_Rent.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.ArrayList;

import static com.easy.car_Rent.enums.AvailabilityType.AVAILABLE;
import static com.easy.car_Rent.enums.AvailabilityType.UNDER_MAINTAIN;
import static com.easy.car_Rent.enums.RentRequest.PAY;
@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private RentRepo rentRepo;
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private ModelMapper mapper;
    @Override
    public CustomDTO paymentIdGenerate() {
        return new CustomDTO(paymentRepo.getLastIndex());
    }

    @Override
    public void savePayment(PaymentDTO dto, String rentID) {
        Payment payment = mapper.map(dto, Payment.class);
        Rent rent = rentRepo.findById(rentID).get();
        if (rent.getRentDetails().get(0).getDriverID() != null) {

            Driver drivers = driverRepo.findById(rent.getRentDetails().get(0).getDriverID()).get();
            drivers.setDriverAvailability(AVAILABLE);
            driverRepo.save(drivers);

            Car car = carRepo.findById(rent.getRentDetails().get(0).getCarID()).get();
            car.setVehicleAvailabilityType(UNDER_MAINTAIN);
            carRepo.save(car);

            rent.setRentType(PAY);
            rentRepo.save(rent);
        }
        if (rent.getRentDetails().get(0).getDriverID() == null) {
            Car car = carRepo.findById(rent.getRentDetails().get(0).getCarID()).get();
            car.setVehicleAvailabilityType(UNDER_MAINTAIN);
            carRepo.save(car);

            rent.setRentType(PAY);
            rentRepo.save(rent);
        }
        paymentRepo.save(payment);
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayment() {
        return mapper.map(paymentRepo.findAll(), new TypeToken<ArrayList<PaymentDTO>>() {
        }.getType());
    }
}

