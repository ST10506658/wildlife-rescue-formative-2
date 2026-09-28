package com.wildlife;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RescueTests {
    private RescueManager manager;

    @BeforeEach
    void setUp() {
        manager = new RescueManager();
    }

    // region Helpers
    private InjuredAnimalRescue injured(String id, double vetCost, boolean surgery) {
        return new InjuredAnimalRescue(id, "Ellie", "African Elephant", "Kruger", "Thabo",
                2, 100, "Snare wound", vetCost, surgery);
    }

    private OrphanedAnimalRescue orphaned(String id, int age, boolean foster) {
        return new OrphanedAnimalRescue(id, "Rhi", "White Rhino", "Hluhluwe", "Sipho",
                1, 100, age, 500, foster);
    }

    private EndangeredSpeciesRescue endangered(String id, String classification, boolean team) {
        return new EndangeredSpeciesRescue(id, "Pango", "Pangolin", "Limpopo", "Lerato",
                1, 100, classification, 1000, team);
    }
    // endregion

    // region Cost Tests
    @Test
    void injuredCostWithSurgery() {
        assertEquals(6200, injured("WR1", 1000, true).calculateTotalCost(), 0.001);
    }

    @Test
    void injuredCostWithoutSurgery() {
        assertEquals(1200, injured("WR1", 1000, false).calculateTotalCost(), 0.001);
    }

    @Test
    void orphanedCostWithFoster() {
        assertEquals(3100, orphaned("WR2", 6, true).calculateTotalCost(), 0.001);
    }

    @Test
    void orphanedCostWithoutFoster() {
        assertEquals(600, orphaned("WR2", 6, false).calculateTotalCost(), 0.001);
    }

    @Test
    void endangeredCostWithTeam() {
        assertEquals(9100, endangered("WR3", "Endangered", true).calculateTotalCost(), 0.001);
    }

    @Test
    void endangeredCostWithoutTeam() {
        assertEquals(1100, endangered("WR3", "Endangered", false).calculateTotalCost(), 0.001);
    }
    // endregion

    // region Priority Tests
    @Test
    void injuredPriority() {
        assertEquals(RescuePriority.CRITICAL, injured("WR1", 1000, true).determinePriority());
        assertEquals(RescuePriority.HIGH, injured("WR1", 12000, false).determinePriority());
        assertEquals(RescuePriority.MEDIUM, injured("WR1", 1000, false).determinePriority());
    }

    @Test
    void orphanedPriority() {
        assertEquals(RescuePriority.CRITICAL, orphaned("WR2", 2, false).determinePriority());
        assertEquals(RescuePriority.HIGH, orphaned("WR2", 8, false).determinePriority());
        assertEquals(RescuePriority.MEDIUM, orphaned("WR2", 24, false).determinePriority());
    }

    @Test
    void endangeredPriority() {
        assertEquals(RescuePriority.CRITICAL, endangered("WR3", "Critically Endangered", false).determinePriority());
        assertEquals(RescuePriority.HIGH, endangered("WR3", "Vulnerable", true).determinePriority());
        assertEquals(RescuePriority.MEDIUM, endangered("WR3", "Vulnerable", false).determinePriority());
    }
    // endregion

    // region Status Tests
    @Test
    void newCaseIsReported() {
        assertEquals(RescueStatus.REPORTED, injured("WR1", 1000, false).getStatus());
    }

    @Test
    void startAndCompleteUpdateStatus() {
        InjuredAnimalRescue rescueCase = injured("WR1", 1000, false);
        rescueCase.start();
        assertEquals(RescueStatus.RESCUE_IN_PROGRESS, rescueCase.getStatus());
        rescueCase.complete();
        assertEquals(RescueStatus.COMPLETED, rescueCase.getStatus());
    }

    @Test
    void managerUpdatesStatus() {
        manager.addCase(injured("WR1", 1000, false));
        assertTrue(manager.updateStatus("WR1", RescueStatus.UNDER_OBSERVATION));
        assertEquals(RescueStatus.UNDER_OBSERVATION, manager.findById("WR1").getStatus());
        assertFalse(manager.updateStatus("MISSING", RescueStatus.COMPLETED));
    }
    // endregion

    // region Search Tests
    @Test
    void searchFindsExistingCase() {
        manager.addCase(injured("WR1", 1000, false));
        assertNotNull(manager.findById("WR1"));
        assertNotNull(manager.findById("wr1"));
    }

    @Test
    void searchReturnsNullForMissingCase() {
        assertNull(manager.findById("WR999"));
    }
    // endregion

    // region Duplicate Tests
    @Test
    void duplicateIdIsRejected() {
        assertTrue(manager.addCase(injured("WR1", 1000, false)));
        assertFalse(manager.addCase(orphaned("WR1", 6, false)));
        assertEquals(1, manager.getCaseCount());
    }

    @Test
    void blankIdIsRejected() {
        assertFalse(manager.addCase(injured("   ", 1000, false)));
        assertEquals(0, manager.getCaseCount());
    }
    // endregion
}
