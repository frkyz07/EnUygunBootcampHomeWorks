package test;

public class ClassTwo extends Abstract{

    // you can see in other class we need to give their bodies again
    @Override
    void update() {
        System.out.println("Class two updated");
    }

    @Override
    void get() {
        System.out.println("Get the class two data");

    }
}
