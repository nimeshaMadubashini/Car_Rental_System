package com.easy.car_Rent.repo;

import com.easy.car_Rent.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,String> {
}
