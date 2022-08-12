package org.example;

//this is extra example
// we write our funtion in here and create a test file
public class Grader {
    public String letterGradeDefiner(int gradePoint) {

        if (gradePoint < 0) {return "The number is below than 0";}
        else if ( gradePoint<50 ) {return "F";}
        else if (gradePoint < 60) {return "D";}
        else if (gradePoint < 70) {return "C";}
        else if (gradePoint < 80) {return "B";}
        else if (gradePoint>80 & gradePoint<=100){return "A";}
        return "The number is above than 100";
    }
}



