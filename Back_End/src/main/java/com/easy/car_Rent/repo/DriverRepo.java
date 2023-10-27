package com.easy.car_Rent.repo;

import com.easy.car_Rent.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver,String> {
}
