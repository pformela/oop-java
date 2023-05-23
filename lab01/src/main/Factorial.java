package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Objects;

public class Factorial {

    public static BigInteger factorial(int n) throws NegativeNumberFactorialException {
        if (n < 0) {
            throw new NegativeNumberFactorialException("Can not count factorial out of negative number.");
        }

        BigInteger result = BigInteger.ONE;

        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }

    public static double powerOfTwo(int n) {
        if (n == 0) {
            return 1;
        } else if (n < 0) {
            double result = 1;

            for (int i = 0; i < Math.abs(n); i++) {
                result /= 2;
            }

            return result;
        } else {
            double result = 1;

            for (int i = 0; i < n; i++) {
                result *= 2;
            }

            return result;
        }
    }
    public static void main(String[] args) throws IOException, NegativeNumberFactorialException {
        System.out.println("****** Program for counting factorial of n ******");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Choose the operation: ");
            System.out.println("1. Factorial of n");
            System.out.println("2. 2 to the power of n.");

            String option = reader.readLine();

            switch (option) {
                case "1" -> {
                    System.out.print("Enter number: ");
                    String enteredNumber = reader.readLine();
                    int number;
                    try {
                        number = Integer.parseInt(enteredNumber);

                        if (number < 0) {
                            throw new NegativeNumberFactorialException("N can not be negative");
                        }
                    } catch (NegativeNumberFactorialException | NumberFormatException e) {
                        System.out.println("Entered invalid number.");
                        continue;
                    }
                    System.out.println("Factorial of " + enteredNumber + ": " + factorial(number));
                }
                case "2" -> {
                    System.out.print("Enter number: ");
                    String enteredPower = reader.readLine();
                    int power;
                    try {
                        power = Integer.parseInt(enteredPower);
                    } catch (NumberFormatException e) {
                        System.out.println("Entered invalid number.");
                        continue;
                    }
                    System.out.println("2 to the power of " + power + " = " + powerOfTwo(power));
                }
                default -> System.out.println("Picked invalid option.");
            }

            System.out.print("Would you like to continue the program? (y/n) ");
            String exit = reader.readLine();

            if (Objects.equals(exit, "n")) {
                isRunning = false;
            }
        }

        System.out.println("Finished program.");

//        System.out.println("2 to the power of 3: " + powerOfTwo(3));
//        System.out.println("2 to the power of 0: " + powerOfTwo(0));
//        System.out.println("2 to the power of -3: " + powerOfTwo(-3));
    }
}