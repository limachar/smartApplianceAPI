package com.example.smartcontroller.controller;

import com.example.smartcontroller.dataObjects.CreateUserDto;
import com.example.smartcontroller.enums.Programs;
import com.example.smartcontroller.enums.Status;
import com.example.smartcontroller.model.Dishwasher;
import com.example.smartcontroller.repository.ApplianceRepo;
import com.example.smartcontroller.repository.UserRepo;
import com.example.smartcontroller.service.ApplianceService;
import com.example.smartcontroller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApplianceService applianceService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ApplianceRepo applianceRepo;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto createUserDto) {

        userService.addUser(createUserDto);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

    @PutMapping("{id}/addAppliance/{applianceId}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @PathVariable long applianceId){

        userService.addApplianceToUser(id, applianceId);

        return new ResponseEntity<>("Appliance added. User updated successfully", HttpStatus.OK);
    }

    @GetMapping("/{applianceId}/status")
    public ResponseEntity<?> getApplianceStatus(@PathVariable long applianceId){

        Status status = applianceService.getApplianceStatus(applianceId);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }
    @PutMapping("/{applianceId}/OnOff")
    public ResponseEntity<?> turnOnOff(@PathVariable long applianceId){
        if(applianceService.getApplianceStatus(applianceId).equals(Status.OFF)){
            applianceService.setApplianceStatus(applianceId, Status.ON);
            return new ResponseEntity<>("Appliance is turned on", HttpStatus.OK);
        }
        else{
            applianceService.setApplianceStatus(applianceId, Status.OFF);
            return new ResponseEntity<>("Appliance is turned off", HttpStatus.OK);
        }
    }

    @PutMapping("/{applianceId}/{job}")
    public ResponseEntity<?> startJob(@PathVariable long applianceId, @PathVariable Programs job){
        if(applianceService.getApplianceStatus(applianceId).equals(Status.OFF)){
            return new ResponseEntity<>("Appliance is turned off", HttpStatus.OK);
        }
        else{
            applianceService.startJobOnAppliance(applianceId, job);
            return new ResponseEntity<>("Appliance running selected job", HttpStatus.OK);
        }
    }

    @GetMapping("/{applianceId}/job")
    public ResponseEntity<?> getApplianceJob(@PathVariable long applianceId){
        Programs runningJob = applianceService.getApplianceRunningJob(applianceId);
        return new ResponseEntity<>(runningJob, HttpStatus.OK);
    }

    @PutMapping("/{applianceId}/terminateJob")
    public ResponseEntity<?> terminateJob(@PathVariable long applianceId){
        applianceService.terminateJob(applianceId);
        return new ResponseEntity<>("Job terminated", HttpStatus.OK);
    }

}
