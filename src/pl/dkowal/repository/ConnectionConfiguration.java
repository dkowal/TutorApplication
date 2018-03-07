package pl.dkowal.repository;

import pl.dkowal.model.Lesson;
import pl.dkowal.model.Student;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionConfiguration{
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:file:C:/data/tutorDB";
    private static final String DB_USERNAME = "test";
    private static final String DB_PASSWORD = "test";
    private Connection connection;
    private Statement stmt;
    private static final String CREATE_TABLE = "CREATE TABLE Students" +
            "(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR(20)," +
            "city VARCHAR(30)," +
            "level VARCHAR(5)," +
            "lessonDate date," +
            "price DOUBLE)";
    private static final String CREATE_TABLE_LESSONS = "CREATE TABLE Lessons" +
            "(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT)," +
            "date DATETIME," +
            "topic VARCHAR(100)," +
            "content VARCHAR(1000)," +
            "stud_id INT," +
            "lesson_name VARCHAR(15))";
    private String lessonName;

    public ConnectionConfiguration() {
        openConnection();
    }

    public void openConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = connection.createStatement();
            stmt.execute(CREATE_TABLE);
            stmt.execute(CREATE_TABLE_LESSONS);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

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

    public void deleteStudent(int id) {
        try {
            stmt.execute("DELETE FROM STUDENTS WHERE ID = " + id);
        } catch (SQLException e) {
            System.out.println("SQLExpeption: " + e.getMessage());
        }
    }

    public void editStudent(Student student) throws SQLException {
        stmt.execute("UPDATE STUDENTS SET " +
                "NAME = '" + student.getName() + "'," +
                "CITY = '" + student.getCity() + "'," +
                "LEVEL= '" + student.getLevel() + "'," +
                "LESSONDATE = '" + student.getLessonDate() + "'," +
                "PRICE = '" + student.getPrice() + "'" +
                "WHERE ID = " + student.getId());
    }

    public ArrayList<Student> getAllStudents() {
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
                Student student = searchStudent(studentId);
                sql = "UPDATE LESSONS SET DATE= DATEADD('DAY', 0, '" + student.getLessonDate().substring(0, 19) + "') WHERE ID = " + lastLesson2.getId();
            }

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
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
    private String setNextNumberOfLesson(int studentId, int i) throws SQLException {
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
    public Lesson getLastLessonOfStudent(int studId) throws SQLException  {
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
}
