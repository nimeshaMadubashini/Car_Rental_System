package com.easy.car_Rent.repo;

import com.easy.car_Rent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
}
