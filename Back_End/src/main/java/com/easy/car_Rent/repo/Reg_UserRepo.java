package com.easy.car_Rent.repo;

import com.easy.car_Rent.entity.Reg_User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Reg_UserRepo extends JpaRepository<Reg_User,String> {
}
