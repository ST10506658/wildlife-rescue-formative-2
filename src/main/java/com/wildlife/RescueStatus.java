package com.wildlife;

public enum RescueStatus {
    REPORTED("Reported"),
    RESCUE_IN_PROGRESS("Rescue in Progress"),
    UNDER_OBSERVATION("Under Observation"),
    COMPLETED("Completed");

    private final String label;

    RescueStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
