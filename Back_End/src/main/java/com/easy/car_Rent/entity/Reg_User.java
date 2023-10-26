package com.easy.car_Rent.entity;

import com.easy.car_Rent.embeded.Name;
import com.easy.car_Rent.enums.AvailabilityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reg_User {
    @Id
    private String user_Id;
    @Embedded
    private Name name;
    private  String contact_No;
    private String address;
    private String email;
    private  String nic;
    private  String licence_No;
    private String nic_Img;
    private  String  licence_Img;


    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
