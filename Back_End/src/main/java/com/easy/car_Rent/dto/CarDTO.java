package com.easy.car_Rent.dto;

import com.easy.car_Rent.embeded.ImageDTO;
import com.easy.car_Rent.embeded.Rate;
import com.easy.car_Rent.enums.AvailabilityType;
import com.easy.car_Rent.enums.CarType;
import com.easy.car_Rent.enums.FuelType;
import com.easy.car_Rent.enums.TransmissionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarDTO {
    private String car_Id;

    private String name;
    private String brand;
    private CarType type;
    private ImageDTO image;
    private int number_Of_Passengers;
    private TransmissionType transmission_Type;
    private FuelType fuel_Type;
    private Rate rent_Duration_Price;
    private double price_Extra_KM;
    private String registration_Number;
    private double free_Mileage;
    private String color;
    private AvailabilityType vehicleAvailabilityType;
}
