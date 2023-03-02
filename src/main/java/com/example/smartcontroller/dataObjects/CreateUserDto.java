package com.example.smartcontroller.dataObjects;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateUserDto {

    private String name;
    //require having an appliance to crete a user
    private long id;

}
