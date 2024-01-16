package ru.geekbrains.junior.lesson2.task1;

public class Cat extends Animal{
    public Cat(String name, int age) {
        super(name, age);
    }

    public void makeSound(){
        System.out.println("Meow");
    }

    @Override
    public String toString() {
        return "Cat{ " +
                "name='" + this.getName() + '\'' +
                ", age='" + this.getAge()+ '\'' +
                '}';
    }
}
