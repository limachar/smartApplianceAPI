package com.example.smartcontroller.dataObjects;

import com.example.smartcontroller.enums.Status;
import lombok.Data;

@Data
public class CreateApplianceDto {

        private String name;
        private String model;
        private Status status;

        public CreateApplianceDto(String name, String model, Status status) {
                this.name = name;
                this.model = model;
                this.status = status;
        }
}
