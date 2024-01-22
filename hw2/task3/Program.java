package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
 * Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
 * Обеспечьте поддержку сериализации для этого класса.
 * Создайте объект класса Student и инициализируйте его данными.
 * Сериализуйте этот объект в файл.
 * Десериализуйте объект обратно в программу из файла.
 * Выведите все поля объекта, включая GPA, и ответьте на вопрос,
 * почему значение GPA не было сохранено/восстановлено.

 * 2. * Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).
 */
class Program {
    public static void main(String[] args) {
        List<Student> students;
        File f = new File(Serialization.FILE_BIN);
        //File f = new File(Serialization.FILE_JSON);
        //File f = new File(Serialization.FILE_XML);
        if (f.exists() && !f.isDirectory()) {
            students = Serialization.loadStudentsListFromFile(Serialization.FILE_BIN);
            //students = Serialization.loadStudentsListFromFile(Serialization.FILE_JSON);
            //students = Serialization.loadStudentsListFromFile(Serialization.FILE_XML);
        } else {
            students = prepareList();
            Serialization.saveStudentsListToFile(Serialization.FILE_BIN, students);
            Serialization.saveStudentsListToFile(Serialization.FILE_JSON, students);
            Serialization.saveStudentsListToFile(Serialization.FILE_XML, students);
        }

        Serialization.printStudents(students);
    }

    static List<Student> prepareList() {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("Вася", 20, 3.9));
        list.add(new Student("Петя", 30, 2.9));
        list.add(new Student("Игорь", 40, 4.3));
        return list;
    }
}
