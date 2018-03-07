package pl.dkowal.view;

import pl.dkowal.database.DBManager;
import pl.dkowal.model.Lesson;
import pl.dkowal.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class WelcomeView extends javax.swing.JFrame {

    private javax.swing.JButton addLessonButton;
    private javax.swing.JButton addStudentButton;
    private javax.swing.JButton runButton;
    private javax.swing.JComboBox lessonsComboBox;
    private javax.swing.JButton selectStudentButton;
    private javax.swing.JComboBox studentsComboBox;
    private Student student;
    private DBManager dbm;
    private Student fakeStudent = new Student("-- Wybierz ucznia --");


    public SelectView() {
        initComponents();
    }
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 4);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 4);
        this.setLocation(x, y);

        addStudentButton = new javax.swing.JButton();
        selectStudentButton = new javax.swing.JButton();
        addLessonButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();
        runButton.setVisible(false);
        runButton.setText("Otwórz");
        studentsComboBox = new javax.swing.JComboBox();
        studentsComboBox.setVisible(false);
        lessonsComboBox = new javax.swing.JComboBox();
        lessonsComboBox.setVisible(false);

        addStudentButton.setText("Dodaj ucznia");
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudentButtonActionPerformed(e);
            }
        });
        addLessonButton.setText("Dodaj lekcję");
        addLessonButton.setEnabled(false);
        addLessonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addLessonButtonActionPerformed(e);
                } catch (SQLException e1) {
                    System.out.println("addLessonButton: " + e1.getMessage());
                }
            }
        });
        selectStudentButton.setText("Wybierz ucznia");
        selectStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectStudentButtonActionPerformed(e);
            }
        });

        runButton.setText("Otwórz");
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runButtonActionPerformed(e);
            }
        });

        studentsComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                studentsComboBoxItemListener(e);
            }});

        lessonsComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                lessonsComboBoxItemListener(e);
            }
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(selectStudentButton)
                                        .addComponent(addLessonButton)
                                        .addComponent(studentsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lessonsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addStudentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(runButton))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addLessonButton, addStudentButton, lessonsComboBox, runButton, selectStudentButton, studentsComboBox});

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(addStudentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectStudentButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addLessonButton)
                                .addGap(20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lessonsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGap(15)
                                .addComponent(runButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addLessonButton, addStudentButton, lessonsComboBox, runButton, selectStudentButton, studentsComboBox});

        pack();
    }

    private void runButtonActionPerformed(ActionEvent e) {
        Lesson lesson = (Lesson) lessonsComboBox.getSelectedItem();
        System.out.println("Selected lesson id: " + lesson.getId() + ". Number: " + lesson.getLessonName());
        new MainView(lesson, student).setVisible(true);
    }

    private void addLessonButtonActionPerformed(ActionEvent e) throws SQLException {
        if(studentsComboBox.getSelectedItem().equals(fakeStudent)) {
            System.out.println("Selected fakeStudent");
            JOptionPane.showMessageDialog(null, "Wybierz studenta!");
        } else {
            System.out.println(student.getName());
            int studentId = student.getId();
            dbm = new DBManager();
            dbm.openConnection();
            dbm.addLesson(student.getId());
            Lesson newLesson = dbm.getLastLessonOfStudent(studentId);
            new MainView(newLesson, student);
            System.out.println("Lesson added");
            selectStudentButtonActionPerformed(e);
        }
    }

    private void addStudentButtonActionPerformed(ActionEvent e) {
        new NewStudentView(this, true).setVisible(true);
    }

    private void studentsComboBoxItemListener(ItemEvent e) {
        dbm = new DBManager();
        dbm.openConnection();

        Student selectedStudent = (Student) studentsComboBox.getSelectedItem();
        try {
            ArrayList<Lesson> lessons = dbm.getLessonsOfStudent(selectedStudent.getId());
            if(!lessons.isEmpty())
                lessonsComboBox.setModel(new DefaultComboBoxModel<>(lessons.toArray()));
            else
                lessonsComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"-- Brak lekcji --"}));
        } catch (SQLException e1) {
            System.out.println("StudentStateChanged: " + e1.getMessage());
        }
        this.student = selectedStudent;
        runButton.setVisible(true);
        pack();
    }

    private void lessonsComboBoxItemListener(ItemEvent e) {

    }

    private void selectStudentButtonActionPerformed(ActionEvent e) {
        addLessonButton.setEnabled(true);
        dbm = new DBManager();
        dbm.openConnection();
        ArrayList<Student> students;
        students = dbm.getAllStudents();
        students.add(0, fakeStudent);
        studentsComboBox.setModel(new DefaultComboBoxModel<>(students.toArray()));
        lessonsComboBox.setVisible(true);
        studentsComboBox.setVisible(true);

        this.pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SelectView().setVisible(true);
            }
        });
    }

}
