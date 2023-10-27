package com.easy.car_Rent.repo;

import com.easy.car_Rent.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car,String> {
}
