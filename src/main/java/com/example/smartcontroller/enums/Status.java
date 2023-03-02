package com.example.smartcontroller.enums;

public enum Status {
    ON("1"), OFF("0"), ERROR("E"), RUNNING("R");

    private String character;

    private Status(String character){
        this.character = character;
    }

    public String getCharacter() {
        return this.character;
    }
}
