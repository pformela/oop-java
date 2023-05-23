package test;

import main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
public class FactorialTests {

    @Test
    @DisplayName("Test factorial of positive number 1")
    void testFactorialPositiveNumber1() throws NegativeNumberFactorialException {
        assertEquals(BigInteger.valueOf(120), Factorial.factorial(5), "Failed counting factorial.");
    }

    @Test
    @DisplayName("Test factorial of positive number 2")
    void testFactorialPositiveNumber2() throws NegativeNumberFactorialException {
        assertEquals(BigInteger.ONE, Factorial.factorial(1), "Failed counting factorial.");
    }

    @Test
    @DisplayName("Test factorial of 0")
    void testFactorialOfZero() throws NegativeNumberFactorialException {
        assertEquals(BigInteger.ONE, Factorial.factorial(1), "Failed counting factorial.");
    }

    @Test
    @DisplayName("Test factorial of negative number")
    void testFactorialOfNegativeNumber() {
        Exception exception = assertThrows(NegativeNumberFactorialException.class, () -> Factorial.factorial(-1));
        assertEquals("Can not count factorial out of negative number.", exception.getMessage());
    }

    @Test
    @DisplayName("Test two to the positive power")
    void testTwoToThePositivePower() {
        assertEquals(8, Factorial.powerOfTwo(3), "Failed to count power of two");
    }

    @Test
    @DisplayName("Test two to the power of 0")
    void testTwoToThePowerOf0() {
        assertEquals(1, Factorial.powerOfTwo(0), "Failed to count power of two");
    }

    @Test
    @DisplayName("Test two to the negative power")
    void testTwoToNegativePower() {
        assertEquals(0.125, Factorial.powerOfTwo(-3), "Failed to count power of two");
    }
}
