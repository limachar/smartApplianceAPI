package com.example.smartcontroller.model;

import com.example.smartcontroller.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "appliance")
public abstract class Appliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name="name")
    private String name;
    @Column(name="model")
    private String model;
    @Column(name="status")
    private Status status;

}
