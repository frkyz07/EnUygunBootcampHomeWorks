package org.example;

// in here i created a basic calculator function in order to write some unit tests
// we will write our units test with Junit framework
// in order to use Junit framework we can create a maven project and in that maven project's
// we should add our Junit in dependencies in the pom.xml file after we did this
// maven will handle all the framework issues for us and if we want to do some changings
// we will be able to do it with using pom.xml
public class Calculator {
    public double plus(double firstNumber, double secondNumber){

        return firstNumber+secondNumber;
    }
    public double divide(double firstNumber, double secondNumber){

        return firstNumber/secondNumber;
    }
    public double multiplication (double firstNumber, double secondNumber){

        return firstNumber*secondNumber;
    }
    public double subtraction(double firstNumber, double secondNumber){

        return firstNumber-secondNumber;

    }

    // after we write our function to test it we can create our test file with
    ///using "ctrl+shift+t" shortcut after using this our test file will be created


}
