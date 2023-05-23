package test;

import main.Cylinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CylinderTest {

    Cylinder emptyCylinder;
    Cylinder nonEmptyCylinder;
    @BeforeEach
    void setUp() {
        emptyCylinder = new Cylinder();
        nonEmptyCylinder = new Cylinder(3.0, 5.0);
    }

    @Test
    @DisplayName("Test empty Cylinder height initialization")
    void testEmptyCylinderInitializationHeight() {
        assertEquals(0.0, emptyCylinder.getHeight(), "Failed empty cylinder height initialization");
    }

    @Test
    @DisplayName("Test empty Cylinder radius initialization")
    void testEmptyCylinderInitializationRadius() {
        assertEquals(0.0, emptyCylinder.getRadius(), "Failed empty cylinder radius initialization");
    }

    @Test
    @DisplayName("Test empty Cylinder getSideArea")
    void testEmptyCylinderGetSideArea() {
        assertEquals(0.0, emptyCylinder.getLateralArea(), "Failed empty cylinder getSideArea");
    }

    @Test
    @DisplayName("Test empty Cylinder getBaseArea")
    void testEmptyCylinderGetBaseArea() {
        assertEquals(0.0, emptyCylinder.getBaseArea(), "Failed empty cylinder getBaseArea");
    }

    @Test
    @DisplayName("Test empty Cylinder getCylinderArea")
    void testEmptyCylinderGetCylinderArea() {
        assertEquals(0.0, emptyCylinder.getCylinderArea(), "Failed empty cylinder getCylinderArea");
    }

    @Test
    @DisplayName("Test empty Cylinder getCylinderVolume")
    void testEmptyCylinderGetCylinderVolume() {
        assertEquals(0.0, emptyCylinder.getCylinderVolume(), "Failed empty cylinder getCylinderVolume");
    }

    @Test
    @DisplayName("Test empty Cylinder setHeight")
    void testEmptyCylinderSetHeight() {
        double height = 4.0;

        emptyCylinder.setHeight(height);
        assertEquals(height, emptyCylinder.getHeight(), "Failed empty cylinder setHeight");
    }

    @Test
    @DisplayName("Test empty Cylinder setRadius")
    void testEmptyCylinderSetRadius() {
        double radius = 4.0;

        emptyCylinder.setRadius(radius);
        assertEquals(radius, emptyCylinder.getRadius(), "Failed empty cylinder setRadius");
    }

    @Test
    @DisplayName("Test non empty Cylinder height initialization")
    void testNonEmptyCylinderInitializationHeight() {
        assertEquals(5.0, nonEmptyCylinder.getHeight(), "Failed non empty cylinder height initialization");
    }

    @Test
    @DisplayName("Test non empty Cylinder radius initialization")
    void testNonEmptyCylinderInitializationRadius() {
        assertEquals(3.0, nonEmptyCylinder.getRadius(), "Failed non empty cylinder radius initialization");
    }

    @Test
    @DisplayName("Test non empty Cylinder getSideArea")
    void testNonEmptyCylinderGetSideArea() {
        double sideArea = 2 * Math.PI * nonEmptyCylinder.getRadius() * nonEmptyCylinder.getHeight();

        assertEquals(sideArea, nonEmptyCylinder.getLateralArea(), "Failed non empty cylinder getSideArea");
    }

    @Test
    @DisplayName("Test non empty Cylinder getBaseArea")
    void testNonEmptyCylinderGetBaseArea() {
        double baseArea = Math.PI * Math.pow(nonEmptyCylinder.getRadius(), 2);

        assertEquals(baseArea, nonEmptyCylinder.getBaseArea(), "Failed non empty cylinder getBaseArea");
    }

    @Test
    @DisplayName("Test non empty Cylinder getCylinderArea")
    void testNonEmptyCylinderGetCylinderArea() {
        double baseArea = 2 * nonEmptyCylinder.getBaseArea() + nonEmptyCylinder.getLateralArea();

        assertEquals(baseArea, nonEmptyCylinder.getCylinderArea(), "Failed non empty cylinder getCylinderArea");
    }

    @Test
    @DisplayName("Test non empty Cylinder getCylinderVolume")
    void testNonEmptyCylinderGetCylinderVolume() {
        double volume = nonEmptyCylinder.getBaseArea() * nonEmptyCylinder.getHeight();

        assertEquals(volume, nonEmptyCylinder.getCylinderVolume(), "Failed non empty cylinder getCylinderVolume");
    }

    @Test
    @DisplayName("Test non empty Cylinder setHeight")
    void testNonEmptyCylinderSetHeight() {
        double height = 4.0;

        nonEmptyCylinder.setHeight(height);
        assertEquals(height, nonEmptyCylinder.getHeight(), "Failed non empty cylinder setHeight");
    }

    @Test
    @DisplayName("Test non empty Cylinder setRadius")
    void testNonEmptyCylinderSetRadius() {
        double radius = 4.0;

        nonEmptyCylinder.setRadius(radius);
        assertEquals(radius, nonEmptyCylinder.getRadius(), "Failed non empty cylinder setRadius");
    }
}
