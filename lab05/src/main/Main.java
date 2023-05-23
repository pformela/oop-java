package main;

import java.util.Scanner;

public class Main {

    private static void printOptions() {
        System.out.println();
        System.out.println("***** OPTIONS *****");
        System.out.println("1. Add a new grade.");
        System.out.println("2. Get the average grade.");
        System.out.println("3. Get the lowest grade.");
        System.out.println("4. Get the highest grade.");
        System.out.println("5. Exit the program.");
        System.out.print("Choose an option (1-5): ");
    }

    private static int getOption(Scanner scanner) {
        int option = 0;
        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid option");
        }

        return option;
    }

    public static void main(String[] args) {
        System.out.println("*** Student Grade System ***");
        GradeList gradeList = new GradeList();
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        int option;
        double newGrade;

        while(isRunning) {
            printOptions();
            option = getOption(scanner);

            try {
                System.out.println();
                switch (option) {
                    case 1 -> {
                        System.out.print("Enter new grade: ");
                        newGrade = scanner.nextDouble();
                        gradeList.addGrade(newGrade);
                    }
                    case 2 -> System.out.println("Average grade is: " + gradeList.getAverageGrade());
                    case 3 -> System.out.println("Lowest grade: " + gradeList.getLowestGrade());
                    case 4 -> System.out.println("Highest grade: " + gradeList.getHighestGrade());
                    case 5 -> isRunning = false;
                    default -> System.out.println("Entered invalid option.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}