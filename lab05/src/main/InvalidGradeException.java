package main;

public class InvalidGradeException extends Exception {
    public InvalidGradeException(String errorMessage) {
        super(errorMessage);
    }
}