package greetPerson;

public class Teacher extends Person{
    String subject;


    public Teacher(int age, String subject) {
        super(age);
        this.subject = subject;
    }
    @Override //@Override not necessary, but good practice
    public void greet(){
        System.out.println("Hi! I teach " + subject);
    }
}
