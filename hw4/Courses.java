package org.example;

/*Создайте базу данных (например, SchoolDB).
 * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
 * Настройте Hibernate для работы с вашей базой данных.
 * Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
 * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
 * Убедитесь, что каждая операция выполняется в отдельной транзакции.*/

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "Courses")
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double duration;

    private static final Random random = new Random();
    private static final String[] titles = new String[]{"Высшая математика", "Схемотехника",
            "Философия ", "Физика", "Радиоэлектроника", "История", "Микропроцессоры"};

    public Courses(int id, String title, double duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Courses(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    public Courses() {
    }

    public static Courses create() { return new Courses (titles[random.nextInt(titles.length)], random.nextDouble(30,
            60));}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void updateTitle() { title = titles[random.nextInt(titles.length)]; }
    public void updateDuration() {duration = random.nextDouble(30, 60); }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
