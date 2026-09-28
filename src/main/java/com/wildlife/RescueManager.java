package com.wildlife;

import java.util.ArrayList;

public class RescueManager {
    private final ArrayList<RescueCase> cases = new ArrayList<>();

    // region Add And Search
    public boolean addCase(RescueCase rescueCase) {
        if (rescueCase == null || rescueCase.getCaseId().isEmpty() || idExists(rescueCase.getCaseId())) 
            return false;
        
        cases.add(rescueCase);
        return true;
    }

    public RescueCase findById(String id) {
        if (id == null) 
            return null;
        
        for (RescueCase rescueCase : cases) 
            if (rescueCase.getCaseId().equalsIgnoreCase(id.trim())) 
                return rescueCase;

        return null;
    }

    public boolean idExists(String id) {
        return findById(id) != null;
    }
    // endregion

    // region Update
    public boolean updateStatus(String id, RescueStatus status) {
        RescueCase rescueCase = findById(id);
        if (rescueCase == null) 
            return false;
        
        rescueCase.setStatus(status);
        return true;
    }
    // endregion

    // region Totals
    public ArrayList<RescueCase> getAllCases() {
        return new ArrayList<>(cases);
    }

    public int getCaseCount() {
        return cases.size();
    }

    public double getTotalCost() {
        double total = 0;
        for (RescueCase rescueCase : cases) 
            total += rescueCase.calculateTotalCost();
        
        return total;
    }
    // endregion

    // region Report
    public String generateReport() {
        String equalsLine = "=".repeat(48);
        String dashLine = "-".repeat(48);
        StringBuilder report = new StringBuilder();
        report.append(equalsLine).append("\nWILDLIFE RESCUE REPORT\n").append(equalsLine).append("\n\n");

        if (cases.isEmpty()) 
            report.append("No rescue cases stored.\n\n");
        
        for (int i = 0; i < cases.size(); i++) {
            report.append(cases.get(i).getReportEntry()).append("\n\n");
            
            if (i < cases.size() - 1) 
                report.append(dashLine).append("\n\n");
            
        }
        report.append(equalsLine).append("\n");
        report.append("Total Rescue Cases : ").append(getCaseCount()).append("\n");
        report.append("Total Rescue Cost  : ").append(Format.money(getTotalCost()));
        return report.toString();
    }
    // endregion
}
