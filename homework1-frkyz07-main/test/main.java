package test;

public class main {

    public static void main(String args[]){

        // in main class if we want to modify our bodiless methods we can modify it like this
        Abstract abs = new Abstract() {
            @Override
            void update() {
                System.out.println("Main updated");
            }

            @Override
            void get() {
                System.out.println("Get the main data");

            }
        };
        // or we can use the already modified methods in different class like this
        Abstract abs2 = new ClassOne();
        Abstract abs3 = new ClassTwo();
        // when we check the results for the bodied methods the results will be always the same
        abs.add();
        abs2.add();
        // but when we check the modified methods the results are not same,
        // it remains like how we changed in the class that are extended
        abs.update();
        // in these results it will write "Main updated" because we changed this method in main class like that
        abs2.update();
        // in this output it will write "Updated" because we changed this method in ClassOne class like that
        abs3.update();
        // in this output it will write "Class two updated" because we changed this method in ClassTwo class like that

        }

    }



