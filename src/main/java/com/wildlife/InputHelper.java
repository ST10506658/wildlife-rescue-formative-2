package com.wildlife;
import java.util.Scanner;

public class InputHelper {
    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    // region Text
    public String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) 
                return value;

            System.out.println("Input cannot be blank. Try again.");
        }
    }

    public String readUniqueId(String prompt, RescueManager manager) {
        while (true) {
            String id = readText(prompt);

            if (!manager.idExists(id))
                return id;

            System.out.println("That Rescue Case ID already exists. Try again.");
        }
    }
    // endregion

    // region Numbers
    public int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            try {
                int number = Integer.parseInt(value);

                if (number >= min && number <= max)
                    return number;
            } catch (NumberFormatException e) {

            }

            System.out.println("Invalid input. Enter a whole number.");
        }
    }

    public int readPositiveInt(String prompt) {
        return readInt(prompt, 1, Integer.MAX_VALUE);
    }

    public double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            try {
                double number = Double.parseDouble(value);
                if (number > 0 && !Double.isInfinite(number))
                    return number;

            } catch (NumberFormatException e) {

            }

            System.out.println("Invalid input. Enter a number greater than zero.");
        }
    }
    // endregion

    // region Yes No
    public boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim().toLowerCase();

            if (value.equals("y") || value.equals("yes")) 
                return true;
            
            if (value.equals("n") || value.equals("no")) 
                return false;
            
            System.out.println("Enter y or n.");
        }
    }
    // endregion
}

