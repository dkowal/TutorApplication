package pl.dkowal.repository;

import pl.dkowal.model.Student;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentRepositoryImpl implements StudentRepository {

    private Statement stmt;
    @Override
    public void addStudent(Student student) {
        try {
            stmt.execute("INSERT INTO Students(name, city, level, lessonDate, price) VALUES ('" +
                    student.getName() + "'," +
                    "'" + student.getCity() + "'," +
                    "'" + student.getLevel() + "'," +
                    "'" + student.getLessonDate() + "'," +
                    "" + student.getPrice() + "" +
                    ")");
            System.out.println("New student added successfully");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    @Override
    public void deleteStudent(int id) {

    }

    @Override
    public void editStudent(Student student) {

    }

    @Override
    public ArrayList<Student> getAllStudents() {
        return null;
    }

    @Override
    public Student searchStudent(int id) {
        return null;
    }
}
