package main;

public class Cylinder {
    private double radius;
    private double height;

    public Cylinder() {}

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    public double getBaseArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    public double getLateralArea() {
        return 2 * Math.PI * this.radius * this.height;
    }

    public double getCylinderArea() {
        return 2 * this.getBaseArea() + this.getLateralArea();
    }

    public double getCylinderVolume() {
        return this.getBaseArea() * this.height;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRadius() {
        return this.radius;
    }

    public double getHeight() {
        return this.height;
    }
}
