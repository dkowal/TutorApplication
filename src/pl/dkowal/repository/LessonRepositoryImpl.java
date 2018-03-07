package pl.dkowal.repository;

import pl.dkowal.model.Lesson;

import java.sql.*;
import java.util.ArrayList;

public class LessonRepositoryImpl implements LessonRepository {

    private Statement stmt;
    @Override
    public ArrayList<Lesson> getLessonsOfStudent(int studentId) {
        return null;
    }

    @Override
    public Lesson getLastLessonOfStudent(int studId) {
        return null;
    }

    @Override
    public void addLesson(int studentId) {

    }

    @Override
    public void editLesson(Lesson lesson) {

    }

    @Override
    public String setNextNumberOfLesson(int studentId, int i) {
        return null;
    }
}
