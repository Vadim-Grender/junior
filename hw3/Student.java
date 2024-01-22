package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

class Student implements Serializable {

    private String name;
    private int age;

    @JsonIgnore
    private transient Double GPA;

    public Student() {
    }

    public Student(String name, int age, Double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @JsonIgnore
    public Double getGPA() {
        return GPA;
    }

    public void setGPA(Double GPA) {
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "Student { " +
                "name = ' " + name + '\'' +
                ", age = " + age +
                " , GPA = " + GPA +
                '}';
    }
}
