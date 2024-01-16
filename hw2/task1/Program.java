package ru.geekbrains.junior.lesson2.task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

/*
Создайте абстрактный класс "Animal" с полями "name" и "age".
Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
Выведите на экран информацию о каждом объекте.
Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
 */
public class Program {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] names = {"Бося" , "Дося", "Вася", "Джага", "Кукис", "Джордж", "Лиза"};
        Random random = new Random();
        Animal[] animals = new Animal[7];
        for (int i = 0; i < animals.length; i++) {
            if (i%2 == 0) {
                animals[i] = new Dog(names[random.nextInt(7)], i * 2 + 2);
            }
            else {
                animals[i] = new Cat(names[random.nextInt(7)], i * 2 + 1);
            }
        }

        for (Animal animal: animals) {
            Class<?> animalClass = Class.forName(animal.getClass().getName());
            Method[] methods = animalClass.getDeclaredMethods();
            Method printMethod = animalClass.getDeclaredMethod("toString");
            System.out.println(printMethod.invoke(animal));
            for (Method method: methods){
                if (method.getName().equals("makeSound")) {
                    method.invoke(animal);
                }
            }

        }
    }
}