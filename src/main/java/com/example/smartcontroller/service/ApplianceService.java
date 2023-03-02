package com.example.smartcontroller.service;

import com.example.smartcontroller.dataObjects.CreateApplianceDto;
import com.example.smartcontroller.enums.Programs;
import com.example.smartcontroller.enums.Status;
import com.example.smartcontroller.model.Appliance;
import com.example.smartcontroller.model.Dishwasher;
import com.example.smartcontroller.repository.ApplianceRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ApplianceService {
    private final static Logger log = LoggerFactory.getLogger(ApplianceService.class);

    ApplianceRepo applianceRepo;

    @Autowired
    public ApplianceService(ApplianceRepo applianceRepo) {
        this.applianceRepo = applianceRepo;
    }

    public void addAppliance(CreateApplianceDto createApplianceDto){
        Appliance dishwasher = new Dishwasher();
        dishwasher.setModel(createApplianceDto.getModel());
        dishwasher.setName(createApplianceDto.getName());
        dishwasher.setStatus(Status.ON);
        applianceRepo.save(dishwasher);
        log.info("Appliance {} Successfully added with id : {}", dishwasher.getModel(), dishwasher.getId());
    }

    public Status getApplianceStatus(Long applianceId){
        Dishwasher appliance = applianceRepo.getDishwasherById(applianceId)
                .orElseThrow(() -> new NoSuchElementException("Appliance do not exist with id: " + applianceId));
        log.info("Appliance status: {}", appliance.getStatus());
        return appliance.getStatus();
    }

    public void setApplianceStatus(Long applianceId, Status status){
        Dishwasher appliance = applianceRepo.getDishwasherById(applianceId)
                .orElseThrow(() -> new NoSuchElementException("Appliance do not exist with id: " + applianceId));
        appliance.setStatus(status);
        applianceRepo.save(appliance);
        log.info("Appliance status: {}", appliance.getStatus());

    }

    public void startJobOnAppliance(Long applianceId, Programs program){
        Dishwasher appliance = applianceRepo.getDishwasherById(applianceId)
                .orElseThrow(() -> new NoSuchElementException("Appliance do not exist with id: " + applianceId));
        appliance.setJob(program);
        applianceRepo.save(appliance);
        log.info("Job: {} started on Appliance with id: {}", appliance.getJob(), appliance.getId());
    }

    public Programs getApplianceRunningJob(Long applianceId){
        Dishwasher appliance = applianceRepo.getDishwasherById(applianceId)
                .orElseThrow(() -> new NoSuchElementException("Appliance do not exist with id: " + applianceId));
        log.info("Appliance running: {}", appliance.getJob());
        return appliance.getJob();
    }

    public void terminateJob(Long applianceId){
        Dishwasher appliance = applianceRepo.getDishwasherById(applianceId)
                .orElseThrow(() -> new NoSuchElementException("Appliance do not exist with id: " + applianceId));
        Programs job = appliance.getJob();
        appliance.setJob(null);
        applianceRepo.save(appliance);
        log.info("Job: {} terminated on Appliance with id: {}",job, appliance.getId());
    }

}
