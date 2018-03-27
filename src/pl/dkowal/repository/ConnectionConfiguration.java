package pl.dkowal.repository;

import java.sql.*;

public class ConnectionConfiguration{
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:file:C:/data/tutorDB";
    private static final String DB_USERNAME = "test";
    private static final String DB_PASSWORD = "test";
    private static final String CREATE_TABLE = "CREATE TABLE Students" +
            "(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR(20)," +
            "city VARCHAR(30)," +
            "level VARCHAR(5)," +
            "lessonDate DATETIME," +
            "price DOUBLE)";
    private static final String CREATE_TABLE_LESSONS = "CREATE TABLE Lessons" +
            "(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "date DATETIME," +
            "topic VARCHAR(100)," +
            "content VARCHAR(1000)," +
            "stud_id INT," +
            "lesson_name VARCHAR(15)," +
            "exam BOOLEAN)";

    public static void createTables() throws ClassNotFoundException, SQLException {
        Connection connection;
        Statement stmt = null;
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        stmt = connection.createStatement();
        if(stmt.execute(CREATE_TABLE))
            System.out.println("Created table Students");
        if(stmt.execute(CREATE_TABLE_LESSONS))
            System.out.println("Created table Lessons");

    }

    public static Statement openConn() {
        Connection connection;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundEX: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQLException while opening connection: " + e.getMessage());
        }
        return stmt;
    }
}
