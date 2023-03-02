package com.example.smartcontroller.enums;

public enum Programs {
    AUTO_45("45"), AUTO_65("65"), AUTO_70("70"), ECO_55("E55"), QUICK_65("Q65");
    private String shorts;
    Programs(String shorts) {
        this.shorts = shorts;
    }

    public String getProgram() {
    switch (this) {
        case AUTO_45 -> {
            return "Auto 45";
        }
        case AUTO_65 -> {
            return "Auto 65";
        }
        case AUTO_70 -> {
            return "Auto 70";
        }
        case ECO_55 -> {
            return "ECO 55";
        }
        case QUICK_65 -> {
            return "QUICK 65";
        }
        default -> {
            return "PROGRAM";
        }

    }
}
}
