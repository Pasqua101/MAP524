package greetPerson;

public class Student extends Person{

    String major;
    public Student(int age, String major) {
        super(age);
        this.major = major;
    }

    @Override
    public void greet(){
        System.out.println("Hello I majored in " + major);
    }
}
