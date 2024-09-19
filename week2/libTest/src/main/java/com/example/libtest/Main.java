package com.example.libtest;
//import java.util.Scanner;

//We use camelCase for naming Java variables, functions and classes
//Variable names should start with a letter or an _

/*
Java Operands
+=, -=, *=, /= and %= (remainder and assignment)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import greetPerson.Student;
import greetPerson.Teacher;

/*
Control flow statements
==, !=, <, >, <=, >=
*/
public class Main {
    public static void main (String[] args) { // static keyword is used so you do not need to call on it anywhere (rerwatch the recording) Void is used so nothing is returned, and the paramater takes in an array of strings
        System.out.println("Hello World!");

        //Converting an int to String
        int num = 42;
        Integer num2 = 5;
        String str = String.valueOf(num);
        System.out.println(str);

        //Another way is to do
        String str2 = Integer.toString(num);
        System.out.println(str2);

        //Another way to convert is
        String str3 = "" + num;
        System.out.println(str3);

        //Another way is
        String str4 = String.format("%d", num);
        System.out.println(str4);

        System.out.println(str instanceof String); //We can use instanceof to get the typeof of the variable. This will return true or false. In this case it'll return true
        //Other ways to check for the type of the variable
        System.out.println(str.getClass().getSimpleName());
        System.out.println(str.getClass().getTypeName());
        System.out.println(num2.getClass().getSimpleName()); //This method will only work with dereferenced types like Integer. Primitive data types, like int, will not work with instanceof or getClass().getSimpleName()
        //System.out.println(str.getClass()); //Unrelated, but we can see the class of an object with getClass(). This will work on String and anything else that is a class.

        //Converting a string to a number
        String str5 = "42";
        int num3 = Integer.parseInt(str5); //double, floats also have their own parse function. And valueOf also works ie Double.valueOf("42.8)
        System.out.println(num3);

        //Error handling
        try {
            int num4 = Integer.parseInt("abc");
        }
        catch(NumberFormatException e){ // We can use NumberFormatException when a string is unable to be converted to a number
            System.out.println(e);
        }
        catch(Exception err){ //Exception will catch any error that gets thrown
            System.out.println(err);
        }

        //Conditionals
        int x = 10, y = 20;
        if(x < y && x > 5){
            System.out.println("Condition has been met");
        }
        else{
            System.out.println("Condition has not been met");
        }

        //Switches, while, do-while and for loops exists in Java. Loops can be stopped with a break statement. It does not continue the loop after the break statement has been hit
        //NOTE: Do-while loops execute at least once. It checks for the condition after the loop runs. Where as while loops checks if the condition is met before the loop runs

        for (int l = 0; l < 10; l++){
            if (l == 5){
                break;
            }
            System.out.println("l: " + l);
        }
        System.out.println();
        for (int m = 0; m < 10; m++){
            if(m == 5){
                continue; // continue keyword will cause the loop to skip the rest of the current iteration when the condition has been met, and will finish running the loop when its condition has been met.
            }
            System.out.println("m: " + m);
        }
        //Getting user input (uses the scanner class) Scanner does not work in Android studio
        /*
        System.out.println("What is your name?");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();

        System.out.println("What is your age?");
        int age = scanner.nextInt();
        System.out.println("Hello " + name + "!");
        System.out.println("You will be " + (age + 5) + " in 5 years");

        scanner.close();
        */

        //Arrays
        int[] myArray1 = {1, 2, 3, 4, 5};

        int[] myArray2 = new int[5]; //Allocated memory for 5 indexes. Each index is initalized to 0

        myArray1[0] = 1;

        int length = myArray1.length;

        for (int i = 0; i < myArray1.length; i++){
            System.out.println(myArray1[i]);
        }
        System.out.println();
        for(int element : myArray2){ //for each loop
            System.out.println(element);
            System.out.println(myArray2[element]);
        }

        //ArrayList
        ArrayList<Integer> myArrayList = new ArrayList<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);

        myArrayList.remove(0); //Removes element at index 0, which is 1

        for (int element : myArrayList){
            System.out.println(element);
        }

        ArrayList<String> list = new ArrayList<>(Arrays.asList("A", "B", "C")); //to provide an initial value we can use Arrays.asList(). Also works on normal arrays
        System.out.println(list);
        list.add("D");
        System.out.println(list);
        list.add(0, "E"); //Adds "E" to index 0
        System.out.println(list);

        list.remove("A"); //Removes first occurrence of "A'
        System.out.println(list);

        System.out.println(list.get(1));

        //We can modify an element like this
        list.set(0, "New Element");
        System.out.println(list);

        System.out.println(list.size());


        //Array list that can accept different data types (Only problem is that we will have to check what each element is, if we need to do something with it)
        ArrayList<Object> list2 = new ArrayList<>(Arrays.asList(1, "Hello", 2.4, true));
        System.out.println(list2);

        for (Object obj : list2){
            if(obj instanceof String){
                System.out.println("string value: " + obj);
            } else if (obj instanceof Integer) {
                System.out.println("Integer value: " + obj);
            } else if (obj instanceof Double) {
                System.out.println("Double value: " + obj);
            } else if (obj instanceof Boolean) {
                System.out.println("Boolean value: " + obj);
            }
        }

        //HashMaps (like Hash Tables
        HashMap<String, Integer> map = new HashMap<>();

        //Adding key-value pairs. We will use keys to find our data in the map
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);

        //Accessing a value
        int age = map.get("Alice"); //age would be 25

        // Remove a key-value pair
        map.remove("Alice");

        //Check if a key exists
        boolean hasBob = map.containsKey("Bob"); //Would return true

        // iterating over keys
        for (String key : map.keySet()){
            System.out.println(key + " " + map.get(key));
        }

        //We can update a key's value by using the put function with the key name

        Student john = new Student(20, "Computer Science");
        Teacher jane = new Teacher(35, "English");

        john.greet();
        jane.greet();
    }
}