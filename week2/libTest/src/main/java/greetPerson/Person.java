package greetPerson;

public class Person implements Greetable{

    int age;

    public Person(int age){
        this.age = age;
    }
    @Override
    public void greet() {
        System.out.println("Hellow");
    }
}
