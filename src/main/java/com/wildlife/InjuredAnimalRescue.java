package com.wildlife;

public class InjuredAnimalRescue extends RescueCase {
    private static final double SURGERY_FEE = 5000;
    private static final double HIGH_VET_COST = 10000;

    private final String injuryDescription;
    private final double veterinaryCost;
    private final boolean surgeryRequired;

    public InjuredAnimalRescue(String caseId, String animalName, String species, String location, String ranger, int rescueDays, double dailyCareCost, String injuryDescription, double veterinaryCost, boolean surgeryRequired) {
        super(caseId, animalName, species, location, ranger, rescueDays, dailyCareCost);
        this.injuryDescription = injuryDescription;
        this.veterinaryCost = veterinaryCost;
        this.surgeryRequired = surgeryRequired;
    }

    @Override
    public String getTypeName() {
        return "Injured Animal Rescue";
    }

    @Override
    protected double calculateExtraCost() {
        return veterinaryCost + (surgeryRequired ? SURGERY_FEE : 0);
    }

    @Override
    public RescuePriority determinePriority() {
        if (surgeryRequired) 
            return RescuePriority.CRITICAL;

        if (veterinaryCost >= HIGH_VET_COST) 
            return RescuePriority.HIGH;

        return RescuePriority.MEDIUM;
    }

    @Override
    public String getSpecificDetails() {
        return "Injury Description:\t" + injuryDescription + "\n"
            + "Veterinary Cost\t: " + Format.money(veterinaryCost) + "\n"
            + "Surgery Required\t: " + Format.yesNo(surgeryRequired);
    }
}
