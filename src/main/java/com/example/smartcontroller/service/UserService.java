package com.example.smartcontroller.service;

import com.example.smartcontroller.dataObjects.CreateUserDto;
import com.example.smartcontroller.model.Appliance;
import com.example.smartcontroller.model.User;
import com.example.smartcontroller.repository.ApplianceRepo;
import com.example.smartcontroller.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
    private final static Logger log = LoggerFactory.getLogger(UserService.class);

    UserRepo userRepo;
    ApplianceRepo applianceRepo;
    ApplianceService applianceService;

    @Autowired
    public UserService(UserRepo userRepo, ApplianceRepo applianceRepo, ApplianceService applianceService) {
        this.userRepo = userRepo;
        this.applianceRepo = applianceRepo;
        this.applianceService = applianceService;
    }

    public void addUser(CreateUserDto createUserDto){
        User user = new User();
        user.setName(createUserDto.getName());
        userRepo.save(user);
        log.info("User {} Successfully added", user.getId());
    }

    public void addApplianceToUser(Long userId, Long applianceId){
        User updateUser = userRepo.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User do not exist with id: " + userId));
        Appliance appliance = applianceRepo.findById(applianceId)
                .orElseThrow(() -> new NoSuchElementException("Appliance do not exist with id: " + applianceId));
        updateUser.addAppliance(appliance);
        userRepo.save(updateUser);
    }
}
