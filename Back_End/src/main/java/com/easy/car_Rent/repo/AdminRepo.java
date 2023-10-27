package com.easy.car_Rent.repo;

import com.easy.car_Rent.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,String> {
}
