package org.reg.registrationservice.service;

import org.reg.registrationservice.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IUserService {

    User addUser(User user);
    Optional<User> existingUser(String email);

}
