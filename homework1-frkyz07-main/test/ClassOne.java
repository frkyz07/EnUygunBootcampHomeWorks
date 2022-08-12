package test;

public class ClassOne extends Abstract{

    // when we extend our abstract class in different class,
    // java want us to give body 2 methods that we didn't give in the abstract class
    @Override
    void update() {
        System.out.println("Updated");
    }

    @Override
    void get() {
        System.out.println("Get the data");

    }
    // in order to use this methods we need to give their body in every class that we extended
    // the reason that we are doing this beacuse first two methods should be same in every class
    // but for the other two we want to modify in every class or some class we are using,
    // that's why we dont define their body in the abstract class
}
