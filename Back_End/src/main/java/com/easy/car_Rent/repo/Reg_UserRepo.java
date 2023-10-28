package com.easy.car_Rent.repo;

import com.easy.car_Rent.entity.Reg_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Reg_UserRepo extends JpaRepository<Reg_User,String> {
    @Query(value = "SELECT user_Id FROM Reg_User ORDER BY user_Id DESC LIMIT 1", nativeQuery = true)
    String getLastIndex();
}
