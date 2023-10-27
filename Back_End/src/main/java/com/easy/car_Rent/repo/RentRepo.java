package com.easy.car_Rent.repo;

import com.easy.car_Rent.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepo extends JpaRepository<Rent,String> {
}
