package pl.dkowal.repository;

import pl.dkowal.model.FilesPath;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FilesPathRepositoryImpl implements FilesPathRepository {

    Statement stmt;

    public FilesPathRepositoryImpl() {
        stmt = ConnectionConfiguration.openConn();
    }

    @Override
    public void addFilePath(FilesPath filesPath) throws SQLException {
        stmt.execute("INSERT  INTO FILEPATHS (STUD_ID, LESSON_ID, PATH) VALUES (" +
                    filesPath.getStud_id() + "," +
                    filesPath.getLesson_id() + "," +
                "'" + filesPath.getPath() + "')"
        );
    }

    @Override
    public List<FilesPath> getAllFilePaths() throws SQLException {
        List<FilesPath> filesPaths = new ArrayList<FilesPath>();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM FILEPATHS");
        while(resultSet.next()) {
            FilesPath filesPath = new FilesPath();
            filesPath.setId(resultSet.getInt("id"));
            filesPath.setLesson_id(resultSet.getInt("lesson_id"));
            filesPath.setStud_id(resultSet.getInt("stud_id"));
            filesPath.setPath(resultSet.getString("path"));
            filesPaths.add(filesPath);
        }
        return filesPaths;
    }

    @Override
    public List<FilesPath> getFilePathOfLesson(int lessonId, int studentId) throws SQLException {
        List<FilesPath> filesPaths = new ArrayList<FilesPath>();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM FILEPATHS WHERE LESSON_ID=" + lessonId + " AND STUD_ID=" + studentId);
        while(resultSet.next()) {
            FilesPath filesPath = new FilesPath();
            filesPath.setId(resultSet.getInt("id"));
            filesPath.setStud_id(resultSet.getInt("stud_id"));
            filesPath.setLesson_id(resultSet.getInt("lesson_id"));
            filesPath.setPath(resultSet.getString("path"));
            filesPaths.add(filesPath);
        }
        return filesPaths;
    }
}
