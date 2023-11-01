package com.easy.car_Rent.service.impl;

import com.easy.car_Rent.dto.CarDTO;
import com.easy.car_Rent.dto.CustomDTO;
import com.easy.car_Rent.embeded.Image;
import com.easy.car_Rent.entity.Car;
import com.easy.car_Rent.repo.CarRepo;
import com.easy.car_Rent.service.CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


@Service
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepo repo;
    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveCar(CarDTO dto) {
        Car car = new Car(dto.getCar_Id(), dto.getName(), dto.getBrand(), dto.getType(), new Image(), dto.getNumber_Of_Passengers(), dto.getTransmission_Type(), dto.getFuel_Type(), dto.getRent_Duration_Price(), dto.getPrice_Extra_KM(), dto.getRegistration_Number(), dto.getFree_Mileage(), dto.getColor(), dto.getVehicleAvailabilityType());
        if (repo.existsById(dto.getCar_Id())) {
            throw new RuntimeException("Car Already Exist. Please enter another id..!");
        }

        try {

            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/uploads");
            System.out.println(projectPath);
            uploadsDir.mkdir();

            dto.getImage().getFront_View().transferTo(new File(uploadsDir.getAbsolutePath() + "/" + dto.getImage().getFront_View().getOriginalFilename()));
            dto.getImage().getBack_View().transferTo(new File(uploadsDir.getAbsolutePath() + "/" + dto.getImage().getBack_View().getOriginalFilename()));
            dto.getImage().getSide_View().transferTo(new File(uploadsDir.getAbsolutePath() + "/" + dto.getImage().getSide_View().getOriginalFilename()));
            dto.getImage().getInterior().transferTo(new File(uploadsDir.getAbsolutePath() + "/" + dto.getImage().getInterior().getOriginalFilename()));

            car.getImage().setFront_View("uploads/"+dto.getImage().getFront_View().getOriginalFilename());
            car.getImage().setBack_View("uploads/"+dto.getImage().getBack_View().getOriginalFilename());
            car.getImage().setSide_View("uploads/"+dto.getImage().getSide_View().getOriginalFilename());
            car.getImage().setInterior("uploads/"+dto.getImage().getInterior().getOriginalFilename());

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        System.out.println(car);
        repo.save(car);
    }

    @Override
/*    public void updateCar(CarDTO dto) {
        Car car = new Car(dto.getCar_Id(), dto.getName(), dto.getBrand(), dto.getType(), new Image(), dto.getNumber_Of_Passengers(), dto.getTransmission_Type(), dto.getFuel_Type(), dto.getRent_Duration_Price(), dto.getPrice_Extra_KM(), dto.getRegistration_Number(), dto.getFree_Mileage(), dto.getColor(), dto.getVehicleAvailabilityType());
        if (!repo.existsById(dto.getCar_Id())) {
            throw new RuntimeException("Car Not Exist. Please enter Valid id..!");
        }
        try {

            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/uploads");
            System.out.println(projectPath);
            uploadsDir.mkdir();

            dto.getImage().getFront_View().transferTo(new File(uploadsDir.getAbsolutePath() + "/" + dto.getImage().getFront_View().getOriginalFilename()));
            dto.getImage().getBack_View().transferTo(new File(uploadsDir.getAbsolutePath() + "/" + dto.getImage().getBack_View().getOriginalFilename()));
            dto.getImage().getSide_View().transferTo(new File(uploadsDir.getAbsolutePath() + "/" + dto.getImage().getSide_View().getOriginalFilename()));
            dto.getImage().getInterior().transferTo(new File(uploadsDir.getAbsolutePath() + "/" + dto.getImage().getInterior().getOriginalFilename()));

            car.getImage().setFront_View("uploads/"+dto.getImage().getFront_View().getOriginalFilename());
            car.getImage().setBack_View("uploads/"+dto.getImage().getBack_View().getOriginalFilename());
            car.getImage().setSide_View("uploads/"+dto.getImage().getSide_View().getOriginalFilename());
            car.getImage().setInterior("uploads/"+dto.getImage().getInterior().getOriginalFilename());

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        System.out.println(car);
        repo.save(car);
    }*/
    public void updateCar(CarDTO dto) {
        if (!repo.existsById(dto.getCar_Id())) {
            throw new RuntimeException("Car Not Exist. Please enter a valid id..!");
        }

        try {
            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/uploads");
            uploadsDir.mkdirs(); // Create the directory if it doesn't exist (use mkdirs, not mkdir)

            Car existingCar = repo.findById(dto.getCar_Id()).orElseThrow(() -> new RuntimeException("Car with ID " + dto.getCar_Id() + " not found."));

            // Update the car properties
            existingCar.setName(dto.getName());
            existingCar.setBrand(dto.getBrand());
            existingCar.setType(dto.getType());
            existingCar.setNumber_Of_Passengers(dto.getNumber_Of_Passengers());
            existingCar.setTransmission_Type(dto.getTransmission_Type());
            existingCar.setFuel_Type(dto.getFuel_Type());
            existingCar.setRent_Duration_Price(dto.getRent_Duration_Price());
            existingCar.setPrice_Extra_KM(dto.getPrice_Extra_KM());
            existingCar.setRegistration_Number(dto.getRegistration_Number());
            existingCar.setFree_Mileage(dto.getFree_Mileage());
            existingCar.setColor(dto.getColor());
            existingCar.setVehicleAvailabilityType(dto.getVehicleAvailabilityType());

            // Update the image files
            String frontViewFileName = updateImage(dto.getImage().getFront_View(), uploadsDir, existingCar.getImage().getFront_View());
            String backViewFileName = updateImage(dto.getImage().getBack_View(), uploadsDir, existingCar.getImage().getBack_View());
            String sideViewFileName = updateImage(dto.getImage().getSide_View(), uploadsDir, existingCar.getImage().getSide_View());
            String interiorFileName = updateImage(dto.getImage().getInterior(), uploadsDir, existingCar.getImage().getInterior());

            existingCar.getImage().setFront_View(frontViewFileName);
            existingCar.getImage().setBack_View(backViewFileName);
            existingCar.getImage().setSide_View(sideViewFileName);
            existingCar.getImage().setInterior(interiorFileName);

            repo.save(existingCar);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String updateImage(MultipartFile newImage, File uploadsDir, String existingImagePath) throws IOException {
        if (newImage != null && !newImage.isEmpty()) {
            // Generate a unique filename based on the original filename
            String originalFilename = newImage.getOriginalFilename();
            String newFileName = System.currentTimeMillis() + "_" + originalFilename;
            File newImageFile = new File(uploadsDir, newFileName);

            // Transfer the new image to the updated location
            newImage.transferTo(newImageFile);

            // Check if the existing image exists and delete it if necessary
            File existingImageFile = new File(uploadsDir, existingImagePath);
            if (existingImageFile.exists() && !existingImageFile.isDirectory()) {
                if (!existingImageFile.delete()) {
                    throw new RuntimeException("Unable to delete the existing file: " + existingImagePath);
                }
            }

            return newFileName;
        } else {
            // If no new image provided, keep the existing path
            return existingImagePath;
        }
    }


    @Override
    public void deleteCar(String car_Id) {
        if (!repo.existsById(car_Id)) {
            throw new RuntimeException("Wrong ID..Please enter valid id..!");
        }
        repo.deleteById(car_Id);
    }

    @Override
    public ArrayList<CarDTO> getAllCar() {
        return mapper.map(repo.findAll(), new TypeToken<ArrayList<Car>>() {
        }.getType());
    }

    @Override
    public CustomDTO carIdGenerate() {
        return new CustomDTO(repo.getLastIndex());
    }

    @Override
    public Car searchCarId(String id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Wrong ID. Please enter Valid id..!");
        }
        return mapper.map(repo.findById(id).get(), Car.class);
    }

    @Override
    public CustomDTO getSumCar() {
        return new CustomDTO(repo.getSumCar());
    }

    @Override
    public CustomDTO getSumAvailableCar() {
        return new CustomDTO(repo.getSumAvailableCar());
    }

    @Override
    public CustomDTO getSumReservedCar() {
        return new CustomDTO(repo.getSumReservedCar());
    }

    @Override
    public CustomDTO getSumMaintainCar() {
        return new CustomDTO(repo.getSumMaintainCar());
    }

    @Override
    public CustomDTO getSumUnderMaintainCar() {
        return new CustomDTO(repo.getSumUnderMaintainCar());
    }

    @Override
    public ArrayList<CarDTO> getFilerData(String type,String fuelType) {
        return mapper.map(repo.filterCar(type,fuelType), new TypeToken<ArrayList<Car>>() {
        }.getType());
    }

    @Override
    public ArrayList<CarDTO> filterCarDetails(String name, String fuel_Type, String type, String transmission_Type) {
        return mapper.map(repo.filterCarDetails(name,fuel_Type,type,transmission_Type), new TypeToken<ArrayList<Car>>() {
        }.getType());
    }

}
