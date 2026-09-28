package com.wildlife;

public class RescueFactory {
    public static RescueCase create(InputHelper input, RescueManager manager) {
        System.out.println("1. Injured Animal Rescue");
        
        System.out.println("2. Orphaned Animal Rescue");
        
        System.out.println("3. Endangered Species Rescue");
        
        int type = input.readInt("Select rescue type: ", 1, 3);

        // region Common Details
        String id = input.readUniqueId("Rescue Case ID: ", manager);
        
        String name = input.readText("Animal Name: ");
        
        String species = input.readText("Species: ");
        
        String location = input.readText("Rescue Location: ");
        
        String ranger = input.readText("Assigned Ranger: ");
        
        int days = input.readPositiveInt("Number of Rescue Days: ");
        
        double dailyCost = input.readPositiveDouble("Daily Care Cost: R");
        // endregion

        // region Type Details
        if (type == 1) {
            String injury = input.readText("Injury Description: ");
            double vetCost = input.readPositiveDouble("Veterinary Treatment Cost: R");
            boolean surgery = input.readYesNo("Surgery Required (y/n): ");
            
            return new InjuredAnimalRescue(id, name, species, location, ranger, days, dailyCost,injury, vetCost, surgery);
        }
        
        if (type == 2) {
            int age = input.readPositiveInt("Estimated Age (Months): ");
            double feedingCost = input.readPositiveDouble("Feeding Cost: R");
            boolean foster = input.readYesNo("Foster Care Required (y/n): ");
            
            return new OrphanedAnimalRescue(id, name, species, location, ranger, days, dailyCost, age, feedingCost, foster);
        }
        
        String[] classes = {"Vulnerable", "Endangered", "Critically Endangered"};
        int choice = input.readInt("Classification (1=Vulnerable, 2=Endangered, 3=Critically Endangered): ", 1, 3);
        double securityCost = input.readPositiveDouble("Security Cost: R");
        boolean team = input.readYesNo("Specialist Team Required (y/n): ");
        
        return new EndangeredSpeciesRescue(id, name, species, location, ranger, days, dailyCost, classes[choice - 1], securityCost, team);
        // endregion
    }
}
