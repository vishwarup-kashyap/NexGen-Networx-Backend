package org.reg.registrationservice.mapper;


import org.reg.registrationservice.dto.UserAdminDto;
import org.reg.registrationservice.entity.Admin;
import org.reg.registrationservice.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserAdminMap {

    public User userMapping(UserAdminDto userDto)
    {
        User user=new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNo(userDto.getPhoneNo());
        return user;

    }

    public Admin adminMapping(UserAdminDto adminDto)
    {
        Admin admin=new Admin();
        admin.setName(adminDto.getName());
        admin.setEmail(adminDto.getEmail());
        admin.setPassword(adminDto.getPassword());
        admin.setPhoneNo(adminDto.getPhoneNo());
        adminDto.setIsAdmin(true);
        return admin;


    }
}
