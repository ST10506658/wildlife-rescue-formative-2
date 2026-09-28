package com.wildlife;

public abstract class RescueCase implements RescueOperations {
    // region Fields
    private final String caseId;
    private final String animalName;
    private final String species;
    private final String location;
    private final String ranger;
    private final int rescueDays;
    private final double dailyCareCost;
    private RescueStatus status;
    // endregion

    // region Constructor
    public RescueCase(String caseId, String animalName, String species, String location, String ranger, int rescueDays, double dailyCareCost) {
        this.caseId = caseId.trim();
        this.animalName = animalName;
        this.species = species;
        this.location = location;
        this.ranger = ranger;
        this.rescueDays = rescueDays;
        this.dailyCareCost = dailyCareCost;
        this.status = RescueStatus.REPORTED;
    }
    // endregion

    // region Getters
    public String getCaseId() {
        return caseId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getSpecies() {
        return species;
    }

    public String getLocation() {
        return location;
    }

    public String getRanger() {
        return ranger;
    }

    public int getRescueDays() {
        return rescueDays;
    }

    public double getDailyCareCost() {
        return dailyCareCost;
    }

    public RescueStatus getStatus() {
        return status;
    }

    public void setStatus(RescueStatus status) {
        this.status = status;
    }
    // endregion

    // region Abstract Methods
    public abstract String getTypeName();

    protected abstract double calculateExtraCost();

    public abstract RescuePriority determinePriority();

    public abstract String getSpecificDetails();
    // endregion

    // region Shared Logic
    public double calculateTotalCost() {
        return (rescueDays * dailyCareCost) + calculateExtraCost();
    }

    @Override
    public void start() {
        status = RescueStatus.RESCUE_IN_PROGRESS;
    }

    @Override
    public void complete() {
        status = RescueStatus.COMPLETED;
    }

    @Override
    public String generateSummary() {
        return "Rescue Case ID\t:" + caseId + "\n"
            + "Rescue Type\t:" + getTypeName() + "\n"
            + "Species\t:" + species + "\n"
            + "Assigned Ranger\t:" + ranger + "\n"
            + "Rescue Priority\t:" + determinePriority().getLabel() + "\n"
            + "Current Status\t:" + status.getLabel() + "\n"
            + "Total Rescue Cost\t:" + Format.money(calculateTotalCost());
    }

    public String getFullDetails() {
        return generateSummary() + "\n"
            + "Animal Name\t: " + animalName + "\n"
            + "Rescue Location\t: " + location + "\n"
            + "Rescue Days\t: " + rescueDays + "\n"
            + "Daily Care Cost\t: " + Format.money(dailyCareCost) + "\n"
            + getSpecificDetails();
    }

    public String getReportEntry() {
        return "Case ID\t\t: " + caseId + "\n"
            + "Type\t: " + getTypeName() + "\n"
            + "Species\t: " + species + "\n"
            + "Location\t: " + location + "\n"
            + "Ranger\t: " + ranger + "\n"
            + "Priority\t: " + determinePriority().getLabel() + "\n"
            + "Status\t: " + status.getLabel() + "\n"
            + "Total Cost\t: " + Format.money(calculateTotalCost());
    }
    // endregion
}
