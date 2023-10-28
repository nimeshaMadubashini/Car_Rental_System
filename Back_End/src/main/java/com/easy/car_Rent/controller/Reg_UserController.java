package com.easy.car_Rent.controller;

import com.easy.car_Rent.dto.CustomDTO;
import com.easy.car_Rent.dto.Reg_UserDTO;
import com.easy.car_Rent.dto.UserDTO;
import com.easy.car_Rent.embeded.Name;
import com.easy.car_Rent.service.Reg_UserService;
import com.easy.car_Rent.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/reg_User")
public class Reg_UserController {


    @Autowired
    private Reg_UserService regUserService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseUtil saveUser(@ModelAttribute Reg_UserDTO reg_userDTO, @ModelAttribute UserDTO userDTO, @ModelAttribute Name name){
        reg_userDTO.setName(name);
        reg_userDTO.setUser(userDTO);
        regUserService.saveUser(reg_userDTO);
        return new ResponseUtil("OK", "Successfully Registered.!", null);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "/reg_UserIdGenerate")
    public @ResponseBody CustomDTO customerIdGenerate() {
        return regUserService.userIdGenerate();
    }
@ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "/loadAllRegUser")
    public ResponseUtil getAllUser(){
        return new ResponseUtil("ok","Successfully loaded",regUserService.getAllUser());
}
}
