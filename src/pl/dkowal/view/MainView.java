package pl.dkowal.view;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import pl.dkowal.model.Lesson;
import pl.dkowal.model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainView extends javax.swing.JFrame {
    private javax.swing.JButton addExamButton;
    private javax.swing.JButton addFilesButton;
    private javax.swing.JTextArea contentTextArea;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JCheckBox examCheckBox;
    private javax.swing.JList examFilesList;
    private javax.swing.JList filesList;
    private javax.swing.JLabel hourLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuEdit;
    private javax.swing.JMenuItem menuEdit_ADDLESSON;
    private javax.swing.JMenuItem menuEdit_ADDSTUDENT;
    private javax.swing.JMenuItem menuEdit_DELETELESSON;
    private javax.swing.JMenuItem menuEdit_EDITSTUDENT;
    private javax.swing.JPopupMenu.Separator menuEdit_SEPARATOR;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuFile_EXIT;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenuItem menuHelp_HELP;
    private javax.swing.JButton saveAllButton;
    private javax.swing.JTextField topicTextField;
    private Lesson lesson;
    private Student student;
    private JFileChooser fileChooser = new JFileChooser();
    private javax.swing.JButton selectedItemButton;


    public MainView(Lesson lesson, Student student) {
        this.lesson = lesson;
        this.student = student;
        initComponents();
        dateLabel.getText().substring(0,10);
        setTitle(student.getName() + ": " + lesson.getLessonName());
    }

    private void initComponents() {

        dateLabel = new javax.swing.JLabel();
        topicTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentTextArea = new javax.swing.JTextArea();
        examCheckBox = new javax.swing.JCheckBox();
        addExamButton = new javax.swing.JButton();
        addFilesButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        filesList = new javax.swing.JList(listModel);
        hourLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        examFilesList = new javax.swing.JList<>();
        saveAllButton = new javax.swing.JButton();
        menu = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuFile_EXIT = new javax.swing.JMenuItem();
        menuEdit = new javax.swing.JMenu();
        menuEdit_ADDSTUDENT = new javax.swing.JMenuItem();
        menuEdit_EDITSTUDENT = new javax.swing.JMenuItem();
        menuEdit_SEPARATOR = new javax.swing.JPopupMenu.Separator();
        menuEdit_ADDLESSON = new javax.swing.JMenuItem();
        menuEdit_DELETELESSON = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        menuHelp_HELP = new javax.swing.JMenuItem();
        selectedItemButton = new javax.swing.JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        dateLabel.setText("Data lekcji: " + lesson.getDate().substring(0,10));
        hourLabel.setText("Godzina zajęć: " + lesson.getDate().substring(10, 16));
        topicTextField.setText(lesson.getTopic());

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
        selectedItemButton.setText("Wybierz strony z pliku");
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

        menuFile_EXIT.setText("Wyjście");
        menuFile.add(menuFile_EXIT);

        menu.add(menuFile);

        menuEdit.setText("Edycja");

        menuEdit_ADDSTUDENT.setText("Dodaj ucznia");
        menuEdit.add(menuEdit_ADDSTUDENT);

        menuEdit_EDITSTUDENT.setText("Edytuj dane ucznia");
        menuEdit.add(menuEdit_EDITSTUDENT);
        menuEdit.add(menuEdit_SEPARATOR);

        menuEdit_ADDLESSON.setText("Dodaj lekcję");
        menuEdit.add(menuEdit_ADDLESSON);

        menuEdit_DELETELESSON.setText("Usuń bieżącą lekcję");
        menuEdit.add(menuEdit_DELETELESSON);

        menu.add(menuEdit);

        menuHelp.setText("Pomoc");

        menuHelp_HELP.setText("Pomoc");
        menuHelp.add(menuHelp_HELP);

        menu.add(menuHelp);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(examCheckBox)
                                                        .addComponent(addExamButton))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, 100)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(selectedItemButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(saveAllButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(dateLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(hourLabel))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(topicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(addFilesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dateLabel, jScrollPane1, topicTextField});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addExamButton, examCheckBox, jScrollPane3});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addFilesButton, hourLabel, jScrollPane2, saveAllButton, selectedItemButton});

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(dateLabel)
                                        .addComponent(hourLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(topicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addFilesButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(examCheckBox)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(addExamButton))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(selectedItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(saveAllButton)))
                                                .addGap(10, 10, 10))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addFilesButton, dateLabel, examCheckBox, hourLabel, topicTextField});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane2});

        pack();
    }

    private void selectedItemButtonActionPerformed(ActionEvent e) throws IOException {
        File file = new File(listModel.get(filesList.getSelectedIndex()).toString());
//        Desktop desktop = Desktop.getDesktop();
//        if(file.exists()) desktop.open(file);
        PDDocument doc = PDDocument.load(file);
        int count = doc.getNumberOfPages();
        System.out.println(count);

        Splitter splitter = new Splitter();
        List<PDDocument> pages = splitter.split(doc);

//        File file2 = new File("C:/Users/Dawid/Downloads/sample2.pdf");
//        PDFMergerUtility ut = new PDFMergerUtility();
//        ut.addSource(file2);
//        ut.addSource(file);
//        ut.setDestinationFileName("C:/Users/Dawid/Downloads/sample_merged.pdf");
//        ut.mergeDocuments();

        doc.close();
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

        if (tmp == JFileChooser.APPROVE_OPTION)
        {
            File[] paths = fileChooser.getSelectedFiles();

            for (int i = 0; i < paths.length; i++) {
                if (!duplitaceFiles(paths[i].getPath()))
                    listModel.addElement(paths[i]);
                System.out.println(paths[i]);
            }

        }

    }

    private boolean duplitaceFiles(String testedFile)
    {
        for (int i = 0; i < listModel.getSize(); i++)
            if (((File)listModel.get(i)).getPath().equals(testedFile))
                return true;

        return false;
    }
    private DefaultListModel listModel = new DefaultListModel()
    {
        @Override
        public void addElement(Object obj)
        {
            filesList.add(obj);
            super.addElement(((File)obj).getName());
        }
        @Override
        public Object get(int index)
        {
            return filesList.get(index);
        }

        @Override
        public Object remove(int index)
        {
            filesList.remove(index);
            return super.remove(index);
        }
        ArrayList filesList = new ArrayList();
    };




}
