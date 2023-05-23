package main;

import java.util.Scanner;
import main.Cylinder;

public class Main {
    private static void printOptions() {
        System.out.println();
        System.out.println("Select an option (1-4):");
        System.out.println("1. Print height and radius of a cylinder.");
        System.out.println("2. Change cylinder's height.");
        System.out.println("3. Change cylinder's radius.");
        System.out.println("4. Print base, side and cylinder area with its volume.");
        System.out.println("5. Exit the program.");
    }

    private static void printAreasAndVolume(Cylinder cylinder) {
        System.out.println("Cylinder's lateral area: " + cylinder.getLateralArea());
        System.out.println("Cylinder's base area: " + cylinder.getBaseArea());
        System.out.println("Cylinder area: " + cylinder.getCylinderArea());
        System.out.println("Cylinder's volume: " + cylinder.getCylinderVolume());
    }

    private static int getOption() {
        Scanner scanner = new Scanner(System.in);
        int option = 1;

        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid option");
        }

        return option;
    }
    public static void main(String[] args) {
        System.out.println("***** Get to know your cylinder areas and volume *****");

        boolean isRunning = true;
        int option;
        Cylinder cylinder = new Cylinder();
        Scanner scanner = new Scanner(System.in);

        do {
            printOptions();
            option = getOption();

            switch (option) {
                case 1 -> {
                    System.out.println("Height of cylinder: " + cylinder.getHeight());
                    System.out.println("Radius of cylinder: " + cylinder.getRadius());
                }
                case 2 -> {
                    System.out.print("Enter new height: ");
                    double height = scanner.nextDouble();
                    cylinder.setHeight(height);
                }
                case 3 -> {
                    System.out.print("Enter new radius: ");
                    double radius = scanner.nextDouble();
                    cylinder.setRadius(radius);
                }
                case 4 -> printAreasAndVolume(cylinder);
                case 5 -> isRunning = false;
                default -> System.out.println("Entered invalid option.");
            }
        } while (isRunning);

//        Cylinder cylinder = new Cylinder();
//        cylinder.setHeight(6.0);
//        cylinder.setRadius(3.5);
//
//        System.out.println("Height of cyl1: " + cylinder.getHeight());
//        System.out.println("Radius of cyl1: " + cylinder.getRadius());
//        System.out.println("Lateral area of cyl1: " + cylinder.getLateralArea());
//        System.out.println("Base area of cyl1: " + cylinder.getBaseArea());
//        System.out.println("Cylinder area of cyl1: " + cylinder.getCylinderArea());
//        System.out.println("Cylinder volume of cyl1: " + cylinder.getCylinderVolume());
//
//        cylinder = new Cylinder(7.0, 2.0);
//        System.out.println("Height of cyl2: " + cylinder.getHeight());
//        System.out.println("Radius of cyl2: " + cylinder.getRadius());
//        System.out.println("Lateral area of cyl2: " + cylinder.getLateralArea());
//        System.out.println("Base area of cyl2: " + cylinder.getBaseArea());
//        System.out.println("Cylinder area of cyl2: " + cylinder.getCylinderArea());
//        System.out.println("Cylinder volume of cyl2: " + cylinder.getCylinderVolume());
    }
}
