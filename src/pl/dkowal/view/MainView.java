package pl.dkowal.view;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import pl.dkowal.model.Lesson;
import pl.dkowal.model.PDFManager;
import pl.dkowal.model.Student;
import pl.dkowal.repository.LessonRepository;
import pl.dkowal.repository.LessonRepositoryImpl;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {
    private JButton addExamButton;
    private JButton addFilesButton;
    private JTextArea contentTextArea;
    private JLabel dateLabel;
    private JCheckBox examCheckBox;
    private JList examFilesList;
    private JList filesList;
    private JLabel hourLabel;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JMenuBar menu;
    private JMenu menuEdit;
    private JMenuItem menuEdit_ADDLESSON;
    private JMenuItem menuEdit_ADDSTUDENT;
    private JMenuItem menuEdit_DELETELESSON;
    private JMenuItem menuEdit_EDITSTUDENT;
    private JMenuItem menuFile_ATACHFILE;
    private JPopupMenu.Separator menuEdit_SEPARATOR;
    private JMenu menuFile;
    private JMenuItem menuFile_EXIT;
    private JMenu menuHelp;
    private JMenuItem menuHelp_HELP;
    private JButton saveAllButton;
    private JTextField topicTextField;
    private Lesson lesson;
    private Student student;
    private JFileChooser fileChooser = new JFileChooser();
    private JButton selectedItemButton;
    private LessonRepository lessonRepository;


    public MainView(Lesson lesson, Student student) {
        setResizable(false);
        this.lesson = lesson;
        this.student = student;
        initComponents();
        dateLabel.getText().substring(0,10);
        setTitle(student.getName() + ": " + lesson.getLessonName());
        this.setLocationRelativeTo(null);
    }

    private void onLoad() {
        File lessonFiles = new File("C:/data/Files/" + student.getId() + "/" + lesson.getId() + "/");
        if(lessonFiles.exists())
            listFilesForFolder(lessonFiles);
    }

    private void initComponents() {

        dateLabel = new JLabel();
        topicTextField = new JTextField();
        jScrollPane1 = new JScrollPane();
        contentTextArea = new JTextArea();
        examCheckBox = new JCheckBox();
        addExamButton = new JButton();
        addFilesButton = new JButton();
        jScrollPane2 = new JScrollPane();
        filesList = new JList(listModel);
        hourLabel = new JLabel();
        jScrollPane3 = new JScrollPane();
        examFilesList = new JList<>();
        saveAllButton = new JButton();
        menu = new JMenuBar();
        menuFile = new JMenu();
        menuFile_EXIT = new JMenuItem();
        menuEdit = new JMenu();
        menuEdit_ADDSTUDENT = new JMenuItem();
        menuEdit_EDITSTUDENT = new JMenuItem();
        menuEdit_SEPARATOR = new JPopupMenu.Separator();
        menuEdit_ADDLESSON = new JMenuItem();
        menuEdit_DELETELESSON = new JMenuItem();
        menuFile_ATACHFILE = new JMenuItem();
        menuHelp = new JMenu();
        menuHelp_HELP = new JMenuItem();
        selectedItemButton = new JButton();

        menuFile_ATACHFILE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    choosePDFToCutButtonActionPerformed(actionEvent);
                } catch (IOException e) {
                    System.out.println("menuHELP Exception: " + e.getMessage());
                }
            }
        });

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        dateLabel.setText("Data lekcji: " + lesson.getDate().substring(0,10));
        hourLabel.setText("Godzina zajęć: " + lesson.getDate().substring(10, 16));
        topicTextField.setText(lesson.getTopic());
        contentTextArea.setText(lesson.getContent());
        if(lesson.isExam())
            examCheckBox.setSelected(true);

        contentTextArea.setColumns(20);
        contentTextArea.setRows(5);
        jScrollPane1.setViewportView(contentTextArea);

        examCheckBox.setText("Sprawdzian");
        examCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                examCheckBoxStateChanged(e);
            }
        });
        addExamButton.setText("Dodaj plik");
        jScrollPane3.setViewportView(examFilesList);
        addExamButton.setVisible(false);
        examFilesList.setVisible(false);
        jScrollPane3.setVisible(false);

        addFilesButton.setText("Załącz materiały");
        addFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFilesButtonActionPerformed(e);
            }
        });

        jScrollPane2.setViewportView(filesList);

        saveAllButton.setText("Zapisz wszystkie zmiany");
        saveAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    saveAllButtonActionPerformed(actionEvent);
                } catch (IOException e) {
                    System.out.println("SaveAllButton Ex: " + e.getMessage());
                } catch (SQLException e) {
                    System.out.println("SaveAllButton Ex: " + e.getMessage());
                }
            }
        });
        selectedItemButton.setText("Otwórz zaznaczony plik");
        selectedItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectedItemButtonActionPerformed(e);
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        });

        menuFile.setText("Plik");
        menuFile_ATACHFILE.setText("Wybierz książkę");
        menuFile.add(menuFile_ATACHFILE);
        menuFile_EXIT.setText("Wyjście");
        menuFile_EXIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuFile.add(menuFile_EXIT);
        menu.add(menuFile);

        menuEdit.setText("Edycja");
        menuEdit_ADDSTUDENT.setText("Dodaj ucznia");
        menuEdit.add(menuEdit_ADDSTUDENT);
        menuEdit_ADDSTUDENT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewStudentView(new WelcomeView(), true).setVisible(true);
            }
        });
        menuEdit_EDITSTUDENT.setText("Edytuj dane ucznia");
        menuEdit.add(menuEdit_EDITSTUDENT);
        menuEdit.add(menuEdit_SEPARATOR);
        menuEdit_ADDLESSON.setText("Dodaj lekcję");
        menuEdit.add(menuEdit_ADDLESSON);
        menuEdit_DELETELESSON.setText("Usuń bieżącą lekcję");
        menuEdit.add(menuEdit_DELETELESSON);
        menuEdit_DELETELESSON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Czy na pewno chcesz usunąć tę lekcję?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                    lessonRepository.deleteLesson(lesson.getId());
                }
            }
        });
        menu.add(menuEdit);

        menuHelp.setText("Pomoc");
        menuHelp_HELP.setText("Pomoc");
        menuHelp.add(menuHelp_HELP);
        menu.add(menuHelp);

        setJMenuBar(menu);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(examCheckBox)
                                                        .addComponent(addExamButton))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 100, 100)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(selectedItemButton, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(saveAllButton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(dateLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(hourLabel))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(topicTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(addFilesButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {dateLabel, jScrollPane1, topicTextField});

        layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {addExamButton, examCheckBox, jScrollPane3});

        layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {addFilesButton, hourLabel, jScrollPane2, saveAllButton, selectedItemButton});

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(dateLabel)
                                        .addComponent(hourLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(topicTextField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addFilesButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(examCheckBox)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(addExamButton))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jScrollPane3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(selectedItemButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(saveAllButton)))
                                                .addGap(10, 10, 10))))
        );

        layout.linkSize(SwingConstants.VERTICAL, new Component[] {addFilesButton, dateLabel, examCheckBox, hourLabel, topicTextField});

        layout.linkSize(SwingConstants.VERTICAL, new Component[] {jScrollPane1, jScrollPane2});

        pack();
    }

    private void saveAllButtonActionPerformed(ActionEvent actionEvent) throws IOException, SQLException {
        List<File> paths = new ArrayList<>();
        for (int i = 0; i < listModel.getSize(); i++)
            paths.add(new File(listModel.get(i).toString()));
        String path = buildPathForLesson();

        lesson.setTopic(topicTextField.getText());
        lesson.setContent(contentTextArea.getText());
        lesson.setExam(examCheckBox.isSelected());
        lessonRepository = new LessonRepositoryImpl();
        lessonRepository.editLesson(lesson);

        for(int i = 0; i < paths.size(); i++) {
            File source = new File(paths.get(i).getPath());
            File destination = new File(path + "/" + paths.get(i).getName());
            if (!destination.exists()) {
                source.renameTo(destination);
            }
        }
    }

    private void selectedItemButtonActionPerformed(ActionEvent e) throws IOException {
        File file = new File(listModel.get(filesList.getSelectedIndex()).toString());
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);

    }

    private void examCheckBoxStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            examFilesList.setVisible(true);
            addExamButton.setVisible(true);
            jScrollPane3.setVisible(true);
            pack();
        } else {
            examFilesList.setVisible(false);
            addExamButton.setVisible(false);
            jScrollPane3.setVisible(false);
            pack();
        }
    }

    private void addFilesButtonActionPerformed(ActionEvent e) {
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);


        int tmp = fileChooser.showDialog(rootPane, "Dodaj materiały");
        if (tmp == JFileChooser.APPROVE_OPTION) {
            File[] paths = fileChooser.getSelectedFiles();

            for (int i = 0; i < paths.length; i++) {
                if (!duplitaceFiles(paths[i].getPath())) {
                    listModel.addElement(paths[i]);
                }
                System.out.println("Added path: " + paths[i]);
            }

        }

    }

    private boolean duplitaceFiles(String testedFile) {
        for (int i = 0; i < listModel.getSize(); i++)
            if (((File)listModel.get(i)).getPath().equals(testedFile))
                return true;

        return false;
    }
    private DefaultListModel listModel = new DefaultListModel() {
        @Override
        public void addElement(Object obj) {
            filesList.add(obj);
            super.addElement(((File)obj).getName());
        }
        @Override
        public Object get(int index) {
            return filesList.get(index);
        }

        @Override
        public Object remove(int index) {
            filesList.remove(index);
            return super.remove(index);
        }
        ArrayList filesList = new ArrayList();
    };


    private void choosePDFToCutButtonActionPerformed(ActionEvent e) throws IOException {
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files", "pdf");
        fileChooser.setFileFilter(filter);
        fileChooser.addChoosableFileFilter(filter);

        int tmp = fileChooser.showDialog(rootPane, "Wybierz książkę PDF");
        if (tmp == JFileChooser.APPROVE_OPTION) {
            File pdfFile = fileChooser.getSelectedFile();
            new PageChooser(this, true).setVisible(true);
            File cuttedPDF = cutPDF(pdfFile, from, to, "C:\\data\\Files\\");
            listModel.addElement(cuttedPDF);
        }

    }
    private File cutPDF(File file, int from, int to, String path) throws IOException {
        PDFManager pdfManager = new PDFManager();
        return pdfManager.createSplittedPDFDOcument(file, from, to, path);
    }

    private int from, to;
    private class PageChooser extends JDialog {
        JTextField fromInner = new JTextField("from");
        JTextField toInner = new JTextField("to");
        JButton select = new JButton("Wybierz");
        public PageChooser(MainView parent, boolean modal) {
            super(parent, modal);
            this.setTitle("Strony");
            initComponents();
        }
        private void initComponents() {
            select.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    selectButtonActionPerformed(actionEvent);
                }
            });
            this.setLocationRelativeTo(null);
            fromInner.setSize(100, 50);
            toInner.setSize(100, 50);
            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setVerticalGroup(layout.createSequentialGroup().addComponent(fromInner).addComponent(toInner).addComponent(select));
            layout.setHorizontalGroup(layout.createParallelGroup().addGroup(layout.createParallelGroup().addComponent(fromInner).addComponent(toInner).addComponent(select)));
            pack();
        }
        void selectButtonActionPerformed (ActionEvent e) {
            from = Integer.parseInt(fromInner.getText());
            to = Integer.parseInt(toInner.getText());
            this.setVisible(false);
        }
    }
    private void listFilesForFolder(final File folder) {
        List<File> startFiles = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                listModel.addElement(fileEntry);
            }
        }
    }

    private String buildPathForLesson() {
        File dirStud = new File("C:/data/Files/" + student.getId());
        if(!dirStud.exists()) dirStud.mkdir();
        File dirLess;
        if(dirStud.exists())  {
            dirLess = new File(dirStud.getPath() + "/" + lesson.getId());
            dirLess.mkdir();
        }
        return "C:/data/Files/" + student.getId() + "/" + lesson.getId() + "/";
    }
}
