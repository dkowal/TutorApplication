package pl.dkowal.repository;

import pl.dkowal.model.Student;

import java.util.ArrayList;

public interface StudentRepository {
    void addStudent(Student student);
    void deleteStudent(int id);
    void editStudent(Student student);
    ArrayList<Student> getAllStudents();
    Student searchStudent(int id);

}
