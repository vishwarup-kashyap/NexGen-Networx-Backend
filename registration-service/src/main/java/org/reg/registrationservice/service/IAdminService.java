package org.reg.registrationservice.service;

import org.reg.registrationservice.entity.Admin;
import org.reg.registrationservice.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IAdminService {

    public Admin addAdmin(Admin admin);
    public List<User> getAllUser();




}
