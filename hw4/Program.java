package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Program {
    public static void main(String[] args) {
        /*
         * Создание фабрики сессий
         */
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Courses.class)
                .buildSessionFactory();

        /*
        Создание сессии
         */
        try (Session session = sessionFactory.getCurrentSession()){

            //Начало транзакции
            session.beginTransaction();

            //Создание объекта (Курса)
            Courses courses = Courses.create();

            // Сохранение объекта (курса) в БД
            session.save(courses);
            System.out.println("Object courses save successfully");

            //Чтение объекта их БД
            Courses retrievedCourses = session.get(Courses.class, courses.getId());
            System.out.println("Object courses retrieved successfully");
            System.out.println("Retrieved courses object: " + retrievedCourses);

            //Обновление объекта
            retrievedCourses.updateTitle();
            retrievedCourses.updateDuration();
            session.update(retrievedCourses);
            System.out.println("Object courses update successfully");

            //Удаление объекта
            session.delete(retrievedCourses);
            System.out.println("Object courses delete successfully");

            //Коммит транзакции
            session.getTransaction().commit();
            System.out.println("Transaction commit successfully");
        }
    }
}
