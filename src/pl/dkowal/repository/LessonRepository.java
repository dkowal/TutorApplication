package pl.dkowal.repository;

import pl.dkowal.model.Lesson;

import java.util.ArrayList;

public interface LessonRepository {
    ArrayList<Lesson> getLessonsOfStudent(int studentId);
    Lesson getLastLessonOfStudent(int studId);
    void addLesson(int studentId);
    void editLesson(Lesson lesson);
    String setNextNumberOfLesson(int studentId, int i);

}
