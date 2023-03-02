package com.example.smartcontroller.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_appliances",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "appliance_id", referencedColumnName = "id"))
    private Set<Appliance> appliances;


    public void setAppliances(Set<Appliance> appliance) {
        this.appliances = appliance;
    }
    public void addAppliance(Appliance appliance){
        this.appliances.add(appliance);
    }
}
