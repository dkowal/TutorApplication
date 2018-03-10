package pl.dkowal.repository;

import pl.dkowal.model.Lesson;
import pl.dkowal.model.Student;

import java.sql.*;
import java.util.ArrayList;

public class LessonRepositoryImpl implements LessonRepository {

    private Statement stmt;
    public LessonRepositoryImpl() {
        stmt = ConnectionConfiguration.openConn();
    }
    @Override
    public ArrayList<Lesson> getLessonsOfStudent(int studentId) throws SQLException {
        ArrayList<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT * FROM LESSONS WHERE stud_id = " + studentId + " ORDER BY id";
        ResultSet resultSet = stmt.executeQuery(sql);
        while (resultSet.next()) {
            Lesson lesson;
            int id = resultSet.getInt("id");
            String date = resultSet.getString("DATE");
            String topic = resultSet.getString("TOPIC");
            String content = resultSet.getString("CONTENT");
            int studId = resultSet.getInt("STUD_ID");
            String lessonName = resultSet.getString("LESSON_NAME");
            lesson = new Lesson(date, topic, content, studId);
            lesson.setId(id);
            lesson.setLessonName(lessonName);
            lessons.add(lesson);
        }
        return lessons;
    }

    @Override
    public Lesson getLastLessonOfStudent(int studId) throws SQLException {
        String lessonName = setNextNumberOfLesson(studId, 0);
        String sql = "SELECT TOP 1 * FROM LESSONS WHERE STUD_ID=" + studId + " ORDER BY id DESC";
        ResultSet rs = stmt.executeQuery(sql);
        Lesson lesson = null;
        while(rs.next()) {
            String date = rs.getString("DATE");
            String topic = rs.getString("TOPIC");
            String content = rs.getString("CONTENT");
            int id = rs.getInt("id");
            lesson = new Lesson(date, topic, content, studId);
            lesson.setId(id);
            lesson.setLessonName(lessonName);
        }
        return lesson;
    }

    @Override
    public void addLesson(int studentId) throws SQLException {
        String lessonNumber;
        Lesson lastLesson = getLastLessonOfStudent(studentId);
        lessonNumber = setNextNumberOfLesson(studentId, 1);

        try {
            stmt.execute("INSERT INTO LESSONS(STUD_ID, LESSON_NAME) VALUES ('" + studentId +
                    "','" + lessonNumber +
                    "')");
            System.out.println("New lesson added successfully");
            Lesson lastLesson2 = getLastLessonOfStudent(studentId);

            String sql = null;
            if(lastLesson != null) {
                if (!(lastLesson.getDate() == null)) {
                    sql = "UPDATE LESSONS SET DATE= DATEADD('DAY', 7, '" + lastLesson.getDate().substring(0, 19) + "') WHERE ID = " + lastLesson2.getId();
                }
            }
            else {
                Student student = new StudentRepositoryImpl().searchStudent(studentId);
                sql = "UPDATE LESSONS SET DATE= DATEADD('DAY', 0, '" + student.getLessonDate().substring(0, 19) + "') WHERE ID = " + lastLesson2.getId();
            }

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    @Override
    public void editLesson(Lesson lesson) throws SQLException {
        try {
            stmt.execute("UPDATE LESSONS SET " +
                    "date = '" + lesson.getDate() + "'," +
                    "topic = '" + lesson.getTopic() + "'," +
                    "content= '" + lesson.getContent() + "'," +
                    "stud_id = " + lesson.getStudId() +
                    "WHERE ID = " + lesson.getId());
            System.err.println("Lesson saved successfully");
            System.err.println("lessonId: " + lesson.getId());
        } catch (SQLException e) {
            System.out.println("editLesson ex: " + e.getMessage());
        }
    }

    @Override
    public String setNextNumberOfLesson(int studentId, int i) throws SQLException {

        ArrayList<Lesson> lessons = getLessonsOfStudent(studentId);
        if(lessons.isEmpty())
            return "Lekcja 1";
        Lesson lesson = lessons.get(lessons.size() - 1);
        String lessonNumber = lesson.getLessonName();
        lessonNumber = lessonNumber.substring(lessonNumber.lastIndexOf(" ")+1);
        int nr = Integer.parseInt(lessonNumber)+i;
        lessonNumber = "Lekcja " + Integer.toString(nr);
        return lessonNumber;
    }
}
