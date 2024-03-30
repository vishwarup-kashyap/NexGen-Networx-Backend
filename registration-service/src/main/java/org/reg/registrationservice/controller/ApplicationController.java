package org.reg.registrationservice.controller;


import org.reg.registrationservice.dto.LoginDto;
import org.reg.registrationservice.dto.UserAdminDto;
import org.reg.registrationservice.entity.Admin;
import org.reg.registrationservice.entity.User;
import org.reg.registrationservice.mapper.UserAdminMap;
import org.reg.registrationservice.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserAdminMap userAdminMap;


    @PostMapping("/addUser")
    public User addUser(@RequestBody UserAdminDto user)
    {

       User tempuser= userAdminMap.userMapping(user);
        return applicationService.addUser(tempuser);
    }

    @PostMapping("/addAdmin")
    public Admin addAdmin(@RequestBody UserAdminDto admin)
    {
        Admin tempAdmin= userAdminMap.adminMapping(admin);
        return applicationService.addAdmin(tempAdmin);
    }


    @GetMapping("/allUser")
    public List<User> getAllUser()
    {
        return applicationService.getAllUser();
    }

    @PostMapping("/existingUserLogin")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        Optional<User> userOptional = applicationService.existingUser(loginDto.getEmail());
        boolean isValidUser = false; // Declare the flag outside the if block to widen its scope

        User user=new User();
        if (userOptional.isPresent()) {
            user = userOptional.get();
            isValidUser = loginDto.getPassword().equals(user.getPassword());
        }

        if (isValidUser) {
            return ResponseEntity.ok().body(user);
        } else {
            // Sending HTTP 400 Bad Request for an invalid user
            return ResponseEntity.badRequest().body("Invalid user");
        }
    }
}





