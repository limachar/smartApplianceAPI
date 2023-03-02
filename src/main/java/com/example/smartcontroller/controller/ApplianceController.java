package com.example.smartcontroller.controller;

import com.example.smartcontroller.dataObjects.CreateApplianceDto;
import com.example.smartcontroller.enums.Status;
import com.example.smartcontroller.model.Appliance;
import com.example.smartcontroller.model.Dishwasher;
import com.example.smartcontroller.repository.ApplianceRepo;
import com.example.smartcontroller.service.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appliance")
public class ApplianceController {

    @Autowired
    private ApplianceRepo applianceRepo;
    @Autowired
    ApplianceService applianceService;

    @PostMapping("/add/dishwasher")
    public ResponseEntity<?> addAppliance(@RequestBody CreateApplianceDto createApplianceDto) {

        applianceService.addAppliance(createApplianceDto);

        return new ResponseEntity<>("Appliance registered successfully", HttpStatus.OK);

    }

}
