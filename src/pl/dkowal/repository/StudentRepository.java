package pl.dkowal.repository;

import pl.dkowal.model.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentRepository {
    void addStudent(Student student) throws SQLException;
    void deleteStudent(int id) throws SQLException;
    void editStudent(Student student) throws SQLException;
    ArrayList<Student> getAllStudents() throws SQLException;
    Student searchStudent(int id) throws SQLException;

}
