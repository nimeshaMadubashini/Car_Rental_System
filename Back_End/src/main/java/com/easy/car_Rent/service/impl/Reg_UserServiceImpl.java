package com.easy.car_Rent.service.impl;

import com.easy.car_Rent.dto.CustomDTO;
import com.easy.car_Rent.dto.Reg_UserDTO;
import com.easy.car_Rent.entity.Reg_User;
import com.easy.car_Rent.entity.User;
import com.easy.car_Rent.repo.Reg_UserRepo;
import com.easy.car_Rent.service.Reg_UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Service
@Transactional
public class Reg_UserServiceImpl implements Reg_UserService {
    @Autowired
    private Reg_UserRepo repo;
    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveUser(Reg_UserDTO dto) {

        Reg_User regUser = new Reg_User(dto.getUser_Id(), dto.getName(), dto.getContact_No(), dto.getAddress(), dto.getEmail(), dto.getNic(),
                dto.getLicense_No(), "", "", new User(dto.getUser().getUser_Id(), dto.getUser().getRole_Type(), dto.getUser().getUser_Name(), dto.getUser().getPassword()));
        if (repo.existsById(dto.getUser_Id()))
            throw new RuntimeException("User Already Exist. Please enter another id..!");

        try {

            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI())
                    .getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/uploads");
            System.out.println(projectPath);
            uploadsDir.mkdir();

            dto.getNic_Img().transferTo(new File(uploadsDir.getAbsolutePath() + "/" + dto.getNic_Img().getOriginalFilename()));
            dto.getLicense_Img().transferTo(new File(uploadsDir.getAbsolutePath() + "/" + dto.getLicense_Img().getOriginalFilename()));

            regUser.setNic_Img("uploads/" + dto.getNic_Img().getOriginalFilename());
            regUser.setLicense_Img("uploads/" + dto.getLicense_Img().getOriginalFilename());


        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        System.out.println(regUser);
        repo.save(regUser);
    }

    @Override
    public CustomDTO userIdGenerate() {
        return new CustomDTO(repo.getLastIndex());
    }

    @Override
    public ArrayList<Reg_UserDTO> getAllUser() {
        return mapper.map(repo.findAll(),new TypeToken<ArrayList<Reg_User>>(){
        }.getType());
    }
}
