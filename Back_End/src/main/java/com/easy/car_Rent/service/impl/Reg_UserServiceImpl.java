package com.easy.car_Rent.service.impl;

import com.easy.car_Rent.dto.Reg_UserDTO;
import com.easy.car_Rent.entity.Reg_User;
import com.easy.car_Rent.entity.User;
import com.easy.car_Rent.repo.Reg_UserRepo;
import com.easy.car_Rent.service.Reg_UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@Service
@Transactional
public class Reg_UserServiceImpl implements Reg_UserService {
    @Autowired
   private Reg_UserRepo repo;
    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveUser(Reg_UserDTO dto) {
        Reg_User reg_user = new Reg_User(dto.getUser_Id(),dto.getName(), dto.getContact_No(), dto.getAddress(),
                dto.getEmail(), dto.getNic(), dto.getLicense_No(), "","",new User(dto.getUser().getUser_Id(),
                dto.getUser().getRole_Type(),dto.getUser().getUser_Name(),dto.getUser().getPassword()
        ));
        if (repo.existsById(dto.getUser_Id())){
            throw new RuntimeException("UserID AllReady exists Please Use new ID");
        }
        try {
            String projectPath=new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().
                    getParentFile().getAbsolutePath();
            File uploadsDir=new File(projectPath+"/uploads");
            uploadsDir.mkdir();

            dto.getNic_Img().transferTo(new File(uploadsDir.getAbsolutePath()+"/"+dto.getNic_Img().getOriginalFilename()));
            dto.getLicense_Img().transferTo(new File(uploadsDir.getAbsolutePath()+"/"+dto.getLicense_Img().getOriginalFilename()));

            reg_user.setNic("uploads/"+dto.getNic_Img().getOriginalFilename());
            reg_user.setLicense_Img("uploads/"+dto.getLicense_Img().getOriginalFilename());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        repo.save(reg_user);
    }
}
