package test;

public abstract class Abstract {

    // First we need to create a Abstract class and give some method to that class
    // After the we can use our abstract class in different folders


    // in here we create 2 methods and give the body for those methods
    public void add(){
        System.out.println("Added");
    }
    public void delete(){
        System.out.println("Deleted");
    }

    // in here we create 2 more methods but we didnt give the body to them
    abstract void update();
    abstract void get();
}
