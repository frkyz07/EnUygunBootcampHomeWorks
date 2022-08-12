package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


// in this test we try to cover all the possible test scenarios
// they all passed
class GraderTest {


    @Test
    void fortyOneShouldReturnF() {
        var grader = new Grader();
        String operation_fortyOneShouldReturnF = grader.letterGradeDefiner(41);
        assertEquals("F", operation_fortyOneShouldReturnF);
        System.out.println(operation_fortyOneShouldReturnF);
    }

    @Test
    void fiftyNineShouldReturnD() {
        var grader = new Grader();
        String operation_fiftyNineShouldReturnD = grader.letterGradeDefiner(59);
        assertEquals("D", operation_fiftyNineShouldReturnD);
        System.out.println(operation_fiftyNineShouldReturnD);

    }

    @Test
    void sixtyOneShouldReturnC() {
        var grader = new Grader();
        String operation_sixtyOneShouldReturnC = grader.letterGradeDefiner(61);
        assertEquals("C",operation_sixtyOneShouldReturnC );
        System.out.println(operation_sixtyOneShouldReturnC);
    }

    @Test
    void seventyOneShouldReturnB() {
        var grader = new Grader();
        String operation_seventyOneShouldReturnB = grader.letterGradeDefiner(71);
        assertEquals("B", operation_seventyOneShouldReturnB);
        System.out.println(operation_seventyOneShouldReturnB);
    }

    @Test
    void eightyOneShouldReturnA() {
        var grader = new Grader();
        String operation_eightyOneShouldReturnA = grader.letterGradeDefiner(81);
        assertEquals("A", operation_eightyOneShouldReturnA);
        System.out.println(operation_eightyOneShouldReturnA);
    }

    @Test
    void belowThanZeroShouldReturnError() {
        var grader = new Grader();
        String operation_belowThanZeroShouldReturnError = grader.letterGradeDefiner(-12);
        String expected = "The number is below than 0";
        System.out.println(operation_belowThanZeroShouldReturnError);
        assertEquals(expected,operation_belowThanZeroShouldReturnError);
    }




    @Test
    void higherThanHundredShouldReturnError() {
        var grader = new Grader();
        String operation_higherThanHundredShouldReturnError = grader.letterGradeDefiner(120);
        String expected = "The number is above than 100";
        assertEquals(expected,operation_higherThanHundredShouldReturnError);
        System.out.println(operation_higherThanHundredShouldReturnError);
    }
}