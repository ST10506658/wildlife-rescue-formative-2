package com.wildlife;

public enum RescuePriority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    CRITICAL("Critical");

    private final String label;

    RescuePriority(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
