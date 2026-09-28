package com.wildlife;

public class OrphanedAnimalRescue extends RescueCase {
    private static final double FOSTER_FEE = 2500;

    private final int estimatedAgeMonths;
    private final double feedingCost;
    private final boolean fosterCareRequired;

    public OrphanedAnimalRescue(String caseId, String animalName, String species, String location, String ranger, int rescueDays, double dailyCareCost, int estimatedAgeMonths, double feedingCost, boolean fosterCareRequired) {
        super(caseId, animalName, species, location, ranger, rescueDays, dailyCareCost);
        this.estimatedAgeMonths = estimatedAgeMonths;
        this.feedingCost = feedingCost;
        this.fosterCareRequired = fosterCareRequired;
    }

    @Override
    public String getTypeName() {
        return "Orphaned Animal Rescue";
    }

    @Override
    protected double calculateExtraCost() {
        return feedingCost + (fosterCareRequired ? FOSTER_FEE : 0);
    }

    @Override
    public RescuePriority determinePriority() {
        if (estimatedAgeMonths <= 3) 
            return RescuePriority.CRITICAL;
        
        if (estimatedAgeMonths <= 12) 
            return RescuePriority.HIGH;
        
        return RescuePriority.MEDIUM;
    }

    @Override
    public String getSpecificDetails() {
        return "Estimated Age\t: " + estimatedAgeMonths + " months\n"
            + "Feeding Cost\t: " + Format.money(feedingCost) + "\n"
            + "Foster Care\t: " + Format.yesNo(fosterCareRequired);
    }
}
