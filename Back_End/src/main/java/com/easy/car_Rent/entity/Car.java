package com.easy.car_Rent.entity;

import com.easy.car_Rent.embeded.Image;
import com.easy.car_Rent.embeded.Rate;
import com.easy.car_Rent.enums.AvailabilityType;
import com.easy.car_Rent.enums.CarType;
import com.easy.car_Rent.enums.FuelType;
import com.easy.car_Rent.enums.TransmissionType;
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
public class Car {
    @Id
    private String car_Id;
    private String name;
    private String brand;
    @Enumerated(EnumType.STRING)
    private CarType type;
    @Embedded
    private Image image;
    private int number_Of_Passengers;
    @Enumerated(EnumType.STRING)
    private TransmissionType transmission_Type;
    @Enumerated(EnumType.STRING)
    private FuelType fuel_Type;
    @Embedded
    private Rate rent_Duration_Price;
    private double price_Extra_KM;
    private String registration_Number;
    private double free_Mileage;
    private String color;
    @Enumerated(EnumType.STRING)
    private AvailabilityType vehicleAvailabilityType;

}