package ru.geekbrains.junior.lesson2.task1;

public class Dog extends Animal{

    boolean haveFlea = true;
    public Dog(String name, int age) {
        super(name, age);
    }
    public boolean isHaveFlea() {
        return haveFlea;
    }

    public void setHaveFlea(boolean haveFlea) {
        this.haveFlea = haveFlea;
    }

    @Override
    public String toString() {
        return "Dog{ " +
                "name=' " + this.getName() + '\'' +
                ", age='" + this.getAge()+ '\'' +
                ", haveFlea='" + haveFlea + '\'' +
                '}';
    }
}
