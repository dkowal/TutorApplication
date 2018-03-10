package pl.dkowal.repository;

import pl.dkowal.model.FilesPath;

import java.sql.SQLException;
import java.util.List;

public interface FilesPathRepository {
    void addFilePath(FilesPath filePath) throws SQLException;
    List<FilesPath> getAllFilePaths() throws SQLException;
    List<FilesPath> getFilePathOfLesson(int lessonId, int studentId) throws SQLException;
}
