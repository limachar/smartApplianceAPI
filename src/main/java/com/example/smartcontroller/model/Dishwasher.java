package com.example.smartcontroller.model;

import com.example.smartcontroller.enums.Programs;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@DiscriminatorValue("dishwasher")
public class Dishwasher extends Appliance{
    @Column(name="running_job")
    @Enumerated(EnumType.STRING)
    private Programs job;
}
