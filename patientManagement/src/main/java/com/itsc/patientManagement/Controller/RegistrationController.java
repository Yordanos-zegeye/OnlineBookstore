package com.itsc.patientManagement.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.itsc.patientManagement.Model.AppUser;
import com.itsc.patientManagement.Model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/req/signup", consumes = "application/json")
    public AppUser createUser(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
