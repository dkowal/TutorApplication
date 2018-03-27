package pl.dkowal.repository;

import pl.dkowal.model.Lesson;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LessonRepository {
    void addLesson(int studentId) throws SQLException;
    void editLesson(Lesson lesson) throws SQLException;
    void deleteLesson(int lessonId);
    String setNextNumberOfLesson(int studentId, int i) throws SQLException;
    ArrayList<Lesson> getLessonsOfStudent(int studentId) throws SQLException;
    Lesson getLastLessonOfStudent(int studId) throws SQLException;
}
