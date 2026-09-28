package com.wildlife;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        InputHelper input = new InputHelper(new Scanner(System.in));
        RescueManager manager = new RescueManager();
        boolean running = true;

        while (running) {
            showMenu();
            int choice = input.readInt("Select an option: ", 1, 6);
            switch (choice) {
                case 1:
                    createCase(input, manager);
                    break;
                case 2:
                    searchCase(input, manager);
                    break;
                case 3:
                    updateStatus(input, manager);
                    break;
                case 4:
                    displayAll(manager);
                    break;
                case 5:
                    System.out.println(manager.generateReport());
                    break;
                default:
                    running = false;
                    System.out.println("Goodbye.");
            }
        }
    }

    // region Menu
    private static void showMenu() {
        System.out.println();
        System.out.println("=".repeat(38));
        System.out.println("WILDLIFE RESCUE OPERATIONS SYSTEM");
        System.out.println("=".repeat(38));
        System.out.println();
        System.out.println("1. Create Rescue Case");
        System.out.println("2. Search Rescue Case");
        System.out.println("3. Update Rescue Status");
        System.out.println("4. Display All Rescue Cases");
        System.out.println("5. Rescue Report");
        System.out.println("6. Exit");
        System.out.println();
    }
    // endregion

    // region Actions
    private static void createCase(InputHelper input, RescueManager manager) {
        RescueCase rescueCase = RescueFactory.create(input, manager);
        if (manager.addCase(rescueCase)) {
            System.out.println("Rescue case created.");
        } else {
            System.out.println("Rescue case could not be created.");
        }
    }

    private static void searchCase(InputHelper input, RescueManager manager) {
        RescueCase rescueCase = manager.findById(input.readText("Rescue Case ID: "));
        if (rescueCase == null) {
            System.out.println("Rescue case not found.");
            return;
        }
        System.out.println(rescueCase.getFullDetails());
    }

    private static void updateStatus(InputHelper input, RescueManager manager) {
        RescueCase rescueCase = manager.findById(input.readText("Rescue Case ID: "));
        if (rescueCase == null) {
            System.out.println("Rescue case not found.");
            return;
        }
        System.out.println("1. Start Rescue Operation");
        System.out.println("2. Complete Rescue Operation");
        System.out.println("3. Under Observation");
        System.out.println("4. Reported");
        int choice = input.readInt("Select new status: ", 1, 4);
        // if (choice == 1) {
        //     rescueCase.start();
        // } else if (choice == 2) {
        //     rescueCase.complete();
        // } else if (choice == 3) {
        //     manager.updateStatus(rescueCase.getCaseId(), RescueStatus.UNDER_OBSERVATION);
        // } else {
        //     manager.updateStatus(rescueCase.getCaseId(), RescueStatus.REPORTED);
        // }
        switch (choice) {
            case 1:
                rescueCase.start();
                break;
            case 2:
                rescueCase.complete();
                break;
            case 3:
                manager.updateStatus(rescueCase.getCaseId(), RescueStatus.UNDER_OBSERVATION);
                break;
            default:
                manager.updateStatus(rescueCase.getCaseId(), RescueStatus.REPORTED);
                break;
        }
        System.out.println(rescueCase.generateSummary());
    }

    private static void displayAll(RescueManager manager) {
        if (manager.getCaseCount() == 0) {
            System.out.println("No rescue cases stored.");
            return;
        }
        for (RescueCase rescueCase : manager.getAllCases()) {
            System.out.println(rescueCase.getFullDetails());
            System.out.println("-".repeat(38));
        }
    }
    // endregion
}
