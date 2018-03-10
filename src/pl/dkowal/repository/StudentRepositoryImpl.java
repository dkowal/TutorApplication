package pl.dkowal.repository;

import pl.dkowal.model.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentRepositoryImpl implements StudentRepository {

    Statement stmt;

    public StudentRepositoryImpl() {
        stmt = ConnectionConfiguration.openConn();
    }

    @Override
    public void addStudent(Student student) throws SQLException {
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
    public void deleteStudent(int id) throws SQLException {
        try {
            stmt.execute("DELETE FROM STUDENTS WHERE ID = " + id);
        } catch (SQLException e) {
            System.out.println("SQLExpeption: " + e.getMessage());
        }
    }

    @Override
    public void editStudent(Student student) throws SQLException {

        stmt.execute("UPDATE STUDENTS SET " +
                "NAME = '" + student.getName() + "'," +
                "CITY = '" + student.getCity() + "'," +
                "LEVEL= '" + student.getLevel() + "'," +
                "LESSONDATE = '" + student.getLessonDate() + "'," +
                "PRICE = '" + student.getPrice() + "'" +
                "WHERE ID = " + student.getId());
    }

    @Override
    public ArrayList<Student> getAllStudents() throws SQLException {


        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";

        try {
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setCity(resultSet.getString("city"));
                student.setLevel(resultSet.getString("level"));
                student.setLessonDate(resultSet.getString("lessonDate"));
                student.setPrice(resultSet.getDouble("price"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return students;
    }

    @Override
    public Student searchStudent(int id) throws SQLException {

        Student student = null;
        String sql = "SELECT * FROM STUDENTS WHERE ID = " + id;
        ResultSet resultSet = stmt.executeQuery(sql);
        while(resultSet.next()) {
            String name = resultSet.getString("NAME");
            String city = resultSet.getString("CITY");
            String level = resultSet.getString("LEVEL");
            String lessonDate = resultSet.getString("LESSONDATE");
            double price = resultSet.getDouble("PRICE");
            student = new Student(name, city, level, lessonDate, price);
        }

        return student;
    }
}
