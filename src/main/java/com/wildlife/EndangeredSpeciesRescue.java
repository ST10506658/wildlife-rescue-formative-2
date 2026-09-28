package com.wildlife;

public class EndangeredSpeciesRescue extends RescueCase {
    private static final double SPECIALIST_FEE = 8000;

    private final String conservationClassification;
    private final double securityCost;
    private final boolean specialistTeamRequired;

    public EndangeredSpeciesRescue(String caseId, String animalName, String species, String location, String ranger, int rescueDays, double dailyCareCost, String conservationClassification, double securityCost, boolean specialistTeamRequired) {
        super(caseId, animalName, species, location, ranger, rescueDays, dailyCareCost);
        this.conservationClassification = conservationClassification;
        this.securityCost = securityCost;
        this.specialistTeamRequired = specialistTeamRequired;
    }

    @Override
    public String getTypeName() {
        return "Endangered Species Rescue";
    }

    @Override
    protected double calculateExtraCost() {
        return securityCost + (specialistTeamRequired ? SPECIALIST_FEE : 0);
    }

    @Override
    public RescuePriority determinePriority() {
        
        if (conservationClassification.equalsIgnoreCase("Critically Endangered"))
            return RescuePriority.CRITICAL;

        if (specialistTeamRequired || conservationClassification.equalsIgnoreCase("Endangered"))
            return RescuePriority.HIGH;
        
        return RescuePriority.MEDIUM;
    }

    @Override
    public String getSpecificDetails() {
        return "Classification\t: " + conservationClassification + "\n"
            + "Security Cost\t: " + Format.money(securityCost) + "\n"
            + "Specialist Team\t: " + Format.yesNo(specialistTeamRequired);
    }
}
