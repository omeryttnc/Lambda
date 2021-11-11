package Pojo;

import java.util.List;

public class Person {
    public String name;
    public String surname;
    public int age;
    public List<Integer>phoneNumber;

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Person(String name, String surname, int age, List<Integer> phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }
}
