package pl.dkowal.model;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PDFManager {

    private static int counter = 1;

    public File createSplittedPDFDOcument(File file, int from, int to, String path) throws IOException {
        String fileName = getFileName(file);
        String fileTargetPath = path + fileName + "_str" + from + "-" + to + ".pdf";

        List<PDDocument> pages = splitDocument(file, from, to);
        List<File> splittedFiles = saveSplittedFiles(pages, fileName);
        mergeSplittedFiles(splittedFiles, fileTargetPath);

        deleteUnusedSplittedFiles(splittedFiles);
        return new File(fileTargetPath);
    }

    private void deleteUnusedSplittedFiles(List<File> splittedFiles) throws IOException {
        for (File splittedFile : splittedFiles) {
            Files.deleteIfExists(splittedFile.toPath());
        }
    }

    private List<PDDocument> splitDocument(File fileToSplit, int startPage, int endPage) throws IOException {
        PDDocument doc = PDDocument.load(fileToSplit);
        Splitter splitter = new Splitter();
        splitter.setStartPage(startPage);
        splitter.setEndPage(endPage);
        return splitter.split(doc);
    }

    private List<File> saveSplittedFiles(List<PDDocument> pages, String path) throws IOException {
        List<File> splittedFiles = new ArrayList<>();
        for(PDDocument fileInPages : pages) {
            fileInPages.save(path + "sample" + counter + ".pdf");
            File pliczek = new File(path + "sample" + counter + ".pdf");
            splittedFiles.add(pliczek);
            counter++;
        }
        return splittedFiles;
    }

    private void mergeSplittedFiles(List<File> splittedFiles, String fileTargetPath) throws IOException {
        PDFMergerUtility ut = new PDFMergerUtility();
        for (File splittedFile : splittedFiles) {
            ut.addSource(splittedFile);
        }
        ut.setDestinationFileName(fileTargetPath);
        ut.mergeDocuments(null);
    }

    private String getFileName(File file) {
        String fileName = file.getName();
        int pos = fileName.lastIndexOf(".");
        if (pos > 0 && pos < (fileName.length() - 1)) {
            fileName = fileName.substring(0, pos);
        }
        return fileName;
    }

}
