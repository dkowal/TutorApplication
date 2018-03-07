package pl.dkowal.model;

import com.sun.istack.internal.NotNull;

public class Student {

    @NotNull
    private int id;
    private String name;
    private String city;
    private String level;
    private String lessonDate;
    private double price;

    public Student(String name, String city, String level,String lessonDate, double price) {
        this.name = name;
        this.city = city;
        this.level = level;
        this.lessonDate = lessonDate;
        this.price = price;
    }
    public Student() {}
    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }
}
