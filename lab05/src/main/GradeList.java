package main;

import java.util.ArrayList;
import java.util.Arrays;

public class GradeList {
    private ArrayList<Double> grades;
    private double highestGrade;
    private double lowestGrade;
    private final ArrayList<Double> allowedGrades = new ArrayList<>(Arrays.asList(2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0));

    public GradeList() {
        this.grades = new ArrayList<>();
    }

    public void addGrade(double grade) throws InvalidGradeException {
        if (!allowedGrades.contains(grade)) {
            throw new InvalidGradeException("Entered invalid grade");
        }
        this.grades.add(grade);

        if (grade > this.highestGrade) {
            this.highestGrade = grade;
        }

        if (grade < this.lowestGrade) {
            this.lowestGrade = grade;
        } else if (this.lowestGrade == 0.0) {
            this.lowestGrade = grade;
        }
    }

    public double getHighestGrade() throws NoGradesException {
        if (this.highestGrade == 0) {
            throw new NoGradesException("Student has no grades");
        }

        return this.highestGrade;
    }

    public double getLowestGrade() throws NoGradesException {
        if (this.lowestGrade == 0) {
            throw new NoGradesException("Student has no grades");
        }

        return this.lowestGrade;
    }

    public double getAverageGrade() throws NoGradesException {
        if (this.grades.size() == 0) {
            throw new NoGradesException("Student has no grades");
        }

        double sum = this.grades.stream().mapToDouble(Double::doubleValue).sum();

        return sum / this.grades.size();
    }
}
