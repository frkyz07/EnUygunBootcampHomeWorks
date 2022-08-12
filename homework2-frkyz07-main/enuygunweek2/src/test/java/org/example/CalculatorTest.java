package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// our test file is craeted

class CalculatorTest {
//this is our test class
    @Test
    // we are using this test annotations for declaring this function will be test
    void plus() {
        var operationPlus = new Calculator();
        // in here we are creating a method from our main function to test it
        double operationPlusResults = operationPlus.plus(-5,-2);
        // in here we are using the method
        // i create a different variable to see the results but you can choose not to do so
        System.out.println(operationPlusResults);
        // in here we are printing the result
       assertEquals(-7,operationPlusResults);
       // and in here we are using the "assertEquals" method to compare the expected result and
        // the result we got from our test are some or not
    }
    // All the other tests are done by same logic

    @Test
    void divide() {
        var operationDivide = new Calculator();
        double operationDivideResults = operationDivide.divide(25,4);
        System.out.println(operationDivideResults);
        assertEquals(6.25,operationDivideResults);
    }

    @Test
    void multiplication() {
        var operationMultiplication = new Calculator();
        double operationMultiplicationResults = operationMultiplication.multiplication(25.5,5);
        System.out.println(operationMultiplicationResults);
        assertEquals(127.5,operationMultiplicationResults);
    }

    @Test
    void subtraction() {
        var operationSubtraction = new Calculator();
        double operationSubtractionResults = operationSubtraction.subtraction(14.33,3.11);
        System.out.println(operationSubtractionResults);
        assertEquals(11.22,operationSubtractionResults);
    }
}